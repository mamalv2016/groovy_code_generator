import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
import java.text.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;   
import org.apache.poi.hssf.usermodel.*;
import groovy.sql.Sql

class ReadKq{
    def sql = Sql.newInstance("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", 
          "987425", "org.gjt.mm.mysql.Driver") 
    def YYYY_MM_DD = "yyyy-MM-dd" 
    def YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss" 
    def YYYY_MM_DD_HH_MM_SS2 = "yyyy/MM/dd HH:mm:ss"   
    def YYYY_MM_DD_HH_MM_SS3 = "yyyy-MM-dd" 
    def fileName = "e:\\1234.xls"
    def ahour = 60*60*1000
    def file ,     fis,     workbook,     sheet,     data  ,     prop  ,kq_name,kq_firsttime,kq_lasttime,nowday
    int currentSheetNum;
    int currentRow;
    int currentCell;
    static String allChar = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args){
        ReadKq kq = new ReadKq();
        kq.readSheet(); 
        kq.destory();
        println 'ok'
    }
    
    ReadKq(){
          file = new File(fileName) 
          fis = new FileInputStream(file)  
          workbook = new HSSFWorkbook(fis) 
          data = new Hashtable();
          data.put("workbook", workbook);
          prop = new HashMap();
    }
    
    def praseDate(str,format){
        SimpleDateFormat ft = new SimpleDateFormat(format);
        try {
            Date date1 = ft.parse(str);  
            return date1
        } catch (ParseException e) { 
            println "debug:转换日期出现异常：${str}"
        }
        return null
    }
    
    def dateStr(dt,format){
        SimpleDateFormat ft = new SimpleDateFormat(format);
        return ft.format(dt);  
    }
    
    def transferStrToDate(str){
        def nowtime = praseDate(str,YYYY_MM_DD_HH_MM_SS)
        if(nowtime==null){
            nowtime = praseDate(str,YYYY_MM_DD_HH_MM_SS2)
        }
        if(nowtime==null){
            nowtime = praseDate(str,YYYY_MM_DD_HH_MM_SS3)
        }
        return nowtime
    }
    
    def timeSpan(dt1,dt2){ 
        try {
            Date date1 = transferStrToDate(dt1);  
            Date date2 = transferStrToDate(dt2); 
            double hs = ((date2.getTime()-date1.getTime())*1.0/ahour).setScale(2,BigDecimal.ROUND_HALF_UP) 
            if(hs>4)
             hs = hs-1
            DecimalFormat a = new DecimalFormat(".00");  
            return a.format(hs/8.0) 
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    def readSheet(){  
        int[] ii = new int[1];
        ii[0] = 1;
        List<String[][]> result =  readAll(ii , true, true);
        if(result!=null)
        printStrArr(result.get(0));
    }
    
    def printStrArr(String[][] result) {
        def isFirst = true
        def lastName = null
        def l_firsttime = null,l_lasttime=null,last_day = null
        result.each{
            it->
            if(isFirst){
                isFirst  = false;
            }else{ 
                def nowtime = transferStrToDate(it[2])
                nowday = dateStr(nowtime,YYYY_MM_DD)
                kq_name = it[1]
                kq_firsttime =  it[2]
                kq_lasttime =  it[2]
                                    
                if(lastName==null){
                    lastName = it[1] 
                    l_firsttime = it[2]
                    l_lasttime =  it[2]
                    last_day = nowday
                }else{
                    if(lastName.equals(kq_name)){
                        if(last_day.equals(nowday)){
                            l_lasttime =  it[2]
                        }
                        //名字没有变，天数变化了。                        
                        else{
                            insertToDb(lastName,last_day,l_firsttime,l_lasttime)
                            lastName = kq_name
                            l_firsttime = kq_firsttime
                            l_lasttime = kq_firsttime
                        }
                    }
                    //名字变化了 就插入数据
                    else{
                        insertToDb(lastName,last_day,l_firsttime,l_lasttime)
                        lastName = kq_name
                        l_firsttime = kq_firsttime
                        l_lasttime = kq_firsttime
                    }
                }   
            }       
        } 
        insertToDb(lastName,last_day,l_firsttime,l_lasttime)
    }
    
    def insertToDb(name,day,first,last){
         def ts = timeSpan(first,last);
        sql.execute("insert into t_kq (name, day, first_time,last_time,time_span)  values (${name},${day},${first},${last},${ts})")
    }  
    public static String getCellInfo(int hNum, int vNum) {
        char[] cs = allChar.toCharArray();
        String hStr = "";
        if (vNum > 25) {
            hStr = String.valueOf(cs[vNum / 26 - 1])
                    + String.valueOf(cs[vNum % 26 - 1]);
        } else {
            hStr = String.valueOf(cs[vNum]);
        }
        return (hStr + Integer.toString((hNum + 1))).toUpperCase();
    }
    
    public HSSFCell getCellBySheetRowCol(int sheetId, int rowId, int colId) {
        HSSFRow row = getRow(rowId);
        String sheetCellIndex = getDataIndexBySheetRowCell(sheetId, rowId, colId);
        HSSFCell cell = (HSSFCell) data.get(sheetCellIndex);
        if (cell == null) {
            cell = row.getCell((short) colId);
            if (cell != null)
                data.put(sheetCellIndex, cell);
        }
        return cell;
    }
    
    private String getDataIndexBySheet(int sheetNum) {
        return "sheet_" + sheetNum;
    }

    private String getDataIndexBySheetRow(int sheetNum, int rownum) {
        return "sheet_" + sheetNum + "_row_" + rownum;
    }

    private String getDataIndexBySheetRowCell(int sheetNum, int rownum, int cellnum) {
        return "sheet_" + sheetNum + "_row_" + rownum + "_cell_" + cellnum;
    }

    private HSSFRow getRow(int rowId) {
        return getHssfRow(currentSheetNum, rowId);
    }
    
    public HSSFRow getHssfRow(int sheetId, int rowId) {
        String sheetRowIndex = getDataIndexBySheetRow(sheetId, rowId);
        HSSFRow row = (HSSFRow) data.get(sheetRowIndex);
        if (row == null) {
            row = sheet.getRow(rowId);
            if (row != null)
                data.put(sheetRowIndex, row);
        }
        return row;
    }
    
    public boolean setCurrentSheet(int num) {
        if (workbook != null && num < workbook.getNumberOfFonts()) {
            try {
                String index = getDataIndexBySheet(num);
                sheet = (HSSFSheet) data.get(index);
                if (sheet == null) {
                    sheet = workbook.getSheetAt(num);
                    if (sheet != null)
                        data.put(index, sheet);
                }
                currentSheetNum = num;
                return true;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public String[][] getSheetAsTable(int sheetNum, int firstRowNum,
            int lastRowNum, int firstColIndex, int lastColIndex) {
        String[][] cells = null;
        if (setCurrentSheet(sheetNum)) {
            cells = new String[lastRowNum - firstRowNum + 1][lastColIndex
                    - firstColIndex + 1];
            for (int c1 = firstRowNum; c1 <= lastRowNum; c1++) {
                for (int c2 = firstColIndex; c2 <= lastColIndex; c2++) {
                    try {
                        cells[c1][c2] = getCellAsStringByIndex(c1, c2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        cells[c1][c2] = "";
                    }
                }
            }
        }
        return cells;
    }
    
    
    public String getRowNum(int sheetNum) {
        try {
            setCurrentSheet(sheetNum - 1);
            HSSFSheet sheet = getSheet();
            int rowNum = sheet.getLastRowNum() + 1;
            return new Integer(rowNum).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "检查输入的文件是否存在或者页面不存在！";
        }
    }

    /**
     * 读取excel中某一个表单的某一行的最大列数.
     * 
     * @param fileName
     *            文件名
     * @param sheetNum
     *            表单的数目
     * @param row
     *            行数
     * @return
     */
    public String getColNum(int sheetNum, int rowId) {
        HSSFRow row;
        int colNum;
        try {
            setCurrentSheet(sheetNum - 1);
            row = getRow(rowId);
            colNum = row.getLastCellNum();
            return new Integer(colNum).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "检查输入的文件是否存在！";
        }
    }

    
    public String getSheetNum() {
        try {
            String result = (String) prop.get("sheetNum");
            if (result == null) {
                result = new Integer(workbook.getNumberOfSheets()).toString();
                prop.put("sheetNum", result);
            }
            return result;
        } catch (Exception e) {
            error(e);
            return "";
        }
    }

    /**
     * 返回全部的sheet的名称.是集合类型.
     * 
     * @return
     */
    public List getSheetNames() {
        int num = Integer.parseInt(getSheetNum());
        List result = (List) prop.get("sheetNames");
        if (result == null) {
            result= new ArrayList();
            for (int i = 0; i < num; i++) {
                result.add(workbook.getSheetName(i));
            }
            prop.put("sheetNames", result);
        }
        return result;
    }
 
    /**
     * 是否自动关闭excel.
     * 
     * @param sheetNums
     * @param returnMeetFirstNullRow
     * @param autoClose
     * @return
     */
    private List<String[][]> readAll(int[] sheetNums,
            boolean returnMeetFirstNullRow, boolean autoClose) {
        // 如果遇到第一个空行自动返回,调用下面的方法.
        List<String[][]> ans = new ArrayList<String[][]>();
        int sheetId;
        HSSFSheet sheet;
        int maxRowNum;
        HSSFRow row;
        int maxColNum;
        String[][] result;
        try { 
            for (int i = sheetNums.length - 1; i >= 0; i--) {
                result = [[]] as  String[][] ;
                sheetId = sheetNums[i] - 1;
                setCurrentSheet(sheetId);
                sheet = getSheet();
                
                // 如果设置了要遇到第一个空行就自动返回,就计算maxRowNum!
                if (returnMeetFirstNullRow) {
                    maxRowNum = 0;
                    row = null;
                    // 得到从0行开始的第一个非空行数.
                    for (;; maxRowNum++) {
                        row = getRow(maxRowNum); 
                        if (row == null||row.getCell(0)==null||getCellContent(row.getCell(0))=='') {
                            if (maxRowNum != 0)
                                maxRowNum--;
                            break;
                        }
                    }
                }
                // 否则直接取全部的excel的行数
                else {
                    maxRowNum = sheet.getLastRowNum();
                } 
                if (maxRowNum != 0) {
                    // 得到第一行的列数.
                    row = getRow(0);
                    maxColNum = row.getLastCellNum();
                    result = getSheetAsTable(sheetId, 0, maxRowNum, 0,
                            maxColNum - 1);
                }
                ans.add(result);
            }
            if (autoClose)
                destory();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getCellAsStringByIndex(int rowId, int colId) {
        String cellStr = "";
        if (sheet != null && rowId < sheet.getLastRowNum() + 1) {
            try {
                HSSFRow row = getRow(rowId);
                if (row != null) {
                    if (colId < row.getLastCellNum()) {
                        HSSFCell cell = getCellByRowCol(rowId, colId);
                        currentRow = rowId;
                        currentCell = colId;
                        cellStr = getCellContent(cell);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                cellStr = "";
            }
        } 
        return cellStr;
    }
    
    private HSSFCell getCellByRowCol(int rowId, int colId) {
        return getCellBySheetRowCol(currentSheetNum, rowId, colId);
    }
    
    public String getSheetName(int num) {
        setCurrentSheet(num);
        String result = (String) prop.get("sheetName_" + num);
        if (result == null) {
            result = sheet.getSheetName();
            prop.put("sheetName_" + num, result);
        }
        return result;
    } 
    
    private String getCellContent(Cell cell) {
        String cellStr = "";
        if (cell != null) {
               try{
                int cellType = cell.getCellType(); 
                   if(cellType==0) { 
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
                            SimpleDateFormat sdf = null;  
                            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat  
                                    .getBuiltinFormat("h:mm")) {  
                                sdf = new SimpleDateFormat("HH:mm");  
                            } else {// 日期  
                                sdf = new SimpleDateFormat("yyyy-MM-dd");  
                            }  
                            Date date = cell.getDateCellValue();  
                            cellStr = sdf.format(date);  
                        } else if (cell.getCellStyle().getDataFormat() == 58||cell.getCellStyle().getDataFormat() == 165||cell.getCellStyle().getDataFormat() == 166) {  
                            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");  
                            double value = cell.getNumericCellValue();  
                            Date date = org.apache.poi.ss.usermodel.DateUtil  
                                    .getJavaDate(value);  
                            cellStr = sdf.format(date);  
                        }   else {  
                            double value = cell.getNumericCellValue();  
                            CellStyle style = cell.getCellStyle();  
                            DecimalFormat format = new DecimalFormat();  
                            String temp = style.getDataFormatString();  
                            // 单元格设置成常规  
                            if (temp.equals("General")) {  
                                format.applyPattern("#");  
                            }  
                            cellStr = format.format(value);  
                        }   
                    }
                   else if(cellType==1) {
                        cellStr = cell.getStringCellValue(); 
                    }
                   else if(cellType==2) {
                        String formula = cell.getCellFormula();
                        if (formula.indexOf("DATE(") >= 0) {
                            cellStr = getDate(cell);
                        } else if (formula.indexOf("SUM(") >= 0) {
                            cellStr = Double.toString(cell.getNumericCellValue());
                        } else if (formula.indexOf("SIN(") >= 0) {
                            cellStr = Double.toString(cell.getNumericCellValue());
                        } else {
                            //是数字的日期格式,就读取日期形式.
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                cellStr = getDate(cell);
                            } else {
                                try {
                                    cellStr = cell.getStringCellValue();
                                } catch (Exception eee) {
                                    try {
                                        cellStr = getNumber(cell);
                                    } catch (Exception f) {
                                        error(f);
                                    }
                                }
                            }
                        } 
                    }
                   else if(cellType==3) {
                        cellStr = cell.getStringCellValue(); 
                    }
                   else if(cellType==4) {
                        cellStr = Boolean.toString(cell.getBooleanCellValue()); 
                    }
                   else   {
                        cellStr = new String("");
                    }  
                if (cellStr == null) {
                        cellStr = "";
                    }
            } catch (Exception e) {
                error(e);
                cellStr = "";
            }
        }
        return cellStr;
    }
    
    def destory() {  
        try {                  
                if(fis!=null)  
                    fis.close();  
        } catch (Exception ex) {  
                log.error("ExcelReader-destory", ex);   
        }  
    }   
}
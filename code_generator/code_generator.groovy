package com.codegenerate
import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.*
import groovy.sql.Sql
import com.codegenerate.DBTool
import com.codegenerate.StripeRenderer

class CodeGenerateSwing{
    static void main(String[] a){
        def window = new CodeGenerateSwing()
        window.show()
    }
    
    void show(){
        def sb = new SwingBuilder()
        
        def meneBar ={//菜单栏
            sb.menuBar{
                menu(text:'菜单',mnemonic:'F'){
                    menuItem(text:"退出程序",mnemonic:"X",actionPerformed:{dispose()})
                }
            }
        }
        def listans = []
        
        def searchPanel = {
            sb.panel(constraints:BorderLayout.NORTH){
                label("选择表名")
                searchFiled = textField(columns:15)
                button(text:"生成",actionPerformed:{/*生成代码*/
                    //edt闭包：表示在EDT上面同步执行
                    //doLater闭包：在EDT上面异步执行
                    //doOutSide闭包：启用新的线程执行
                   def db = new DBTool()
                   db.url = 'jdbc:mysql://10.200.2.11:3306/lvmama_traffic'
                   db.user = 'lvmama_traffic'
                   db.pass = 'lvmama_traffic'
                   db.init()
                   db.allTables()
                   edt{
                        for(i in 0 ..6){
                            listans<< '测试数据'+i
                        } 
                        resultList.listData =  listans.toArray()
                    }
                })
            }
        }
        
        def resultPanel ={
            sb.scrollPane(constraints:BorderLayout.CENTER){
                resultList = list(fixedCellWidth: 380, fixedCellHeight:15, cellRenderer:new StripeRenderer())
            }
        }
        
        sb.frame(title:'代码生成器',
                   defaultCloseOperation:JFrame.EXIT_ON_CLOSE,
                   size:[400,500],
                   show:true){
            meneBar()  
            searchPanel()    
            resultPanel()     
        }
    }
}
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
        
        def meneBar ={//�˵���
            sb.menuBar{
                menu(text:'�˵�',mnemonic:'F'){
                    menuItem(text:"�˳�����",mnemonic:"X",actionPerformed:{dispose()})
                }
            }
        }
        def listans = []
        
        def searchPanel = {
            sb.panel(constraints:BorderLayout.NORTH){
                label("ѡ�����")
                searchFiled = textField(columns:15)
                button(text:"����",actionPerformed:{/*���ɴ���*/
                    //edt�հ�����ʾ��EDT����ͬ��ִ��
                    //doLater�հ�����EDT�����첽ִ��
                    //doOutSide�հ��������µ��߳�ִ��
                   def db = new DBTool()
                   db.url = 'jdbc:mysql://10.200.2.11:3306/lvmama_traffic'
                   db.user = 'lvmama_traffic'
                   db.pass = 'lvmama_traffic'
                   db.init()
                   db.allTables()
                   edt{
                        for(i in 0 ..6){
                            listans<< '��������'+i
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
        
        sb.frame(title:'����������',
                   defaultCloseOperation:JFrame.EXIT_ON_CLOSE,
                   size:[400,500],
                   show:true){
            meneBar()  
            searchPanel()    
            resultPanel()     
        }
    }
}
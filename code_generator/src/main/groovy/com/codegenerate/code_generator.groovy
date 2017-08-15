package com.codegenerate
import groovy.swing.SwingBuilder
import javax.swing.*
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import java.awt.*
import groovy.sql.Sql

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
					def http = new HTTPBuilder()  
					println http

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
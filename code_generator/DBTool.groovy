package com.codegenerate
import groovy.sql.Sql
class DBTool{
     String url
     String user
     String pass
     String driver = "org.gjt.mm.mysql.Driver" 
     Sql sql 

     def init(){ 
        println url
         println user
       // sql = Sql.newInstance(url,user,pass,driver)
     } 
     
     List allTables(){
       /*  sql.eachRow('''SELECT t.table_name AS name,t.TABLE_COMMENT AS comments FROM information_schema.`TABLES` t 
         WHERE t.TABLE_SCHEMA = (select database()) ORDER BY t.TABLE_NAME''')*/
     }
}
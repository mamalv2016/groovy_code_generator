package com.codegenerate 
import groovy.sql.Sql
import com.mysql.jdbc.Driver

class ConsoleProperties{ 
	def  static sql = Sql.newInstance("jdbc:mysql://127.0.0.1:3306/jeesite?useUnicode=true&characterEncoding=UTF-8", "root", 
          "987425", "com.mysql.jdbc.Driver") 
	static void main(String[] a){
		//全部的属性文件所在的目录
		def dirname = "D:\\lvtraffic"
		def dir =new File(dirname)
		sql.execute('delete from lvmama_config_t')
		if (dir.isDirectory()) {  
			dir.eachFileRecurse { file ->  
				if(!file.isDirectory()){ 
					if( file.name =~ /\.properties$/)
						savePropertiesToDb(file)
				}
			}  
		}
		println 'ok'
    }  
		  
	//处理属性文件，将里面的key保存到数据库中
	def static savePropertiesToDb(file){
		def filename=file.name
		def props = [] 
		def desc = ''
		file.eachLine() {  
			if(it=~/^#/)
				desc = it[1..-1]
			else if(it.indexOf("=")!=-1){
				def _index = it.indexOf("=")
				def len = it.length()  
				def key = it[0..(_index-1)]
				def val = ''
				if(_index<len-1)
					val = it[(_index+1) .. -1]
				MyProperties myProp = [key,val,desc]
				props<<myProp
				desc = ''
			} 
		} 
		
		props.each { it ->
			def k = it.key
			def v = it.value
			def d = it.desc
			sql.execute("insert into lvmama_config_t (config_name, config_value, config_desc,config_file)  values (${k}, ${v},${d},${file.name})") 
		}
	}
}


package com.codegenerate
import groovyx.net.http.HTTPBuilder 
import  groovyx.net.http.URIBuilder 
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*

class urltest{
	static void main(String[] a){ 
		def http = new HTTPBuilder('http://fm.lvmama.com/traffic-vas-service')
	  
		//第一种好用：
		/*
			def param = [:];
			http.get(param) ;
		*/
		//第二种:get好用
		/*
		http.get( path : '/activity/toActivityList',      
			contentType : TEXT,            
			query : [:] ) 
		{
			resp, reader ->  
			println "response status: ${resp.statusLine}" 
			println 'Response data: -----'  
			System.out << reader
			println '\n--------------------'   
		} 
		*/
		
		//第三种：不好用
		 /*http.request(GET, TEXT) { 
			http.path = "/activity/toActivityList"
		    http.query = [q:'groovy']  
			response.success ={resp,reader->  
				println resp.statusLine.statusCode  
				println resp.headers.'content-length'  
				System.out << reader  
			}  
			response.failure={resp-> println resp.statusLine.statusCode }  
		}  */ 
		 
		//第四种：post好用,requestContentType:可选：JSON，TEXT，HTML等
		/*
		http.post(  path: 'http://fm.lvmama.com/traffic-vas-service/vas/inter/checkTransferCity',
		  body : [ cityCode : 'PEK'  ],
		 requestContentType:JSON ){ resp,json->  
			println resp.statusLine.statusCode  
			json.each{
				println it 
			} 
		} 
		*/
   
		//第5种：get好用，来自官方示例
		//http://javadox.com/org.codehaus.groovy.modules.http-builder/http-builder/0.6/groovyx/net/http/HTTPBuilder.html
       /*http = new HTTPBuilder('http://fm.lvmama.com/traffic-vas-service/vas/inter/checkTransferCity')  
	   http.request( GET, TEXT ) { req ->    
			 // executed for all successful responses:   
			 response.success =
			 { resp, reader ->   
				println 'my response handler!'       
				assert resp.statusLine.statusCode == 200   
				println resp.statusLine      
				System.out << reader // print response stream   
			 }   
			 response.failure={resp-> println resp.statusLine.statusCode }  
			 // executed only if the response status code is 401:   
			 response.'404' = { resp ->       println 'not found!'     
			 }   
		 } */
	 
		http.request( POST, JSON ) { 
			it.path='http://fm.lvmama.com/traffic-vas-service/vas/inter/checkTransferCity'
			body = [  
			  cityCode : 'PEK'   
			]  
			  
			response.success = { resp, json ->  
				println resp.statusLine.statusCode   
			}  
		}  
    }
}
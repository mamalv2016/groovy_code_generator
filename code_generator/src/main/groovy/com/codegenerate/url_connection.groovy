package com.codegenerate
import groovyx.net.http.HTTPBuilder  
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*
import org.apache.http.util.EntityUtils; 
import org.apache.http.*; 

class UrlConnection{
	def static String url = 'http://fm.lvmama.com/traffic-promotion-service/promotion/activity/noticeDecuctedPromAmount'
	def static String host = 'http://fm.lvmama.com/traffic-promotion-service' 
	static void main(String[] a){ 
		/*//多线程调用接口
		def range = 1
		range.each{it -> 
			def index = it
			Thread.start {
				def http = new HTTPBuilder(host)
				http.post(  path: url,
					body : [ categoryType : 'LVFLIGHT' , activityId : 1, discountAmount : 1 ],
					requestContentType:JSON 
					){ reader,json->   
						 System.out <<reader
						//println resp
						//println "${index} 结果：${json.status}  错误原因:${json.message}"   
				}
			} 
			println '当前时间：'+System.currentTimeMillis()
		} */
		def instance =new  UrlConnection();
		instance.postUrl(host,url,[ categoryType : 'LVFLIGHT' , activityId : 1, discountAmount : 1 ],{ resp->    
			println EntityUtils.toString(resp.getEntity(),"utf-8")		 
		});
    }
	
	def postUrl(h,p,arg,Closure c){
		def http = new HTTPBuilder(h,"application/json;charset=UTF-8")
		http.post(  path: p,
			body : arg,
			requestContentType:JSON ,c
			) 
	}
}
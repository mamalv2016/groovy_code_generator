package com.codegenerate
import groovyx.net.http.HTTPBuilder  
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*

class urltest{
	def static String url = 'http://mefit.lvmama.com:8005/promotion/activity/noticeDecuctedPromAmount'
	def static String host = 'http://mefit.lvmama.com:8005/promotion' 
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
					){ reader->   
						 System.out <<reader
						//println resp
						//println "${index} 结果：${json.status}  错误原因:${json.message}"   
				}
			} 
			println '当前时间：'+System.currentTimeMillis()
		} */
		def instance =new  urltest();
		instance.post(host,url,[ categoryType : 'LVFLIGHT' , activityId : 1, discountAmount : 1 ]){ reader->   
				 System.out <<reader 
		};
    }
	
	def postUrl(h,p,arg,c){
		def http = new HTTPBuilder(h)
		http.post(  path: p,
			body : arg,
			requestContentType:JSON ,c
			) 
	}
}
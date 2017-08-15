package com.codegenerate
import groovyx.net.http.HTTPBuilder  
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*
import groovy.util.GroovyTestCase
import com.vanward.sedona.frmwrk.filter.impl.SimplePackageFilter

class InsuranceTest extends GroovyTestCase { 
 def static String host = 'http://10.200.2.29:8980/insurance-service'
 def static String url = 'http://mefit.lvmama.com:8005/promotion/activity/noticeDecuctedPromAmount'
 void testSimpleJavaPackage() {
     def http = new HTTPBuilder(host)
	 http.post(  path: 'http://10.200.2.29:8980/insurance-service/adapter/queryInsurances',
		body : [ categoryType : 'LVFLIGHT' , activityId : 1, discountAmount : 1 ],
		requestContentType:JSON 
		){ reader->   
			 System.out <<reader
			//println resp
			//println "${index} 结果：${json.status}  错误原因:${json.message}"   
	}
  } 
}
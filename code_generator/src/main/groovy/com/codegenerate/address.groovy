package com.codegenerate 

class Addr{  
    String homeAddr  
    String workAddr  
      
    Addr(String home,String office)  
    {  
        this.homeAddr=home  
        this.workAddr=office  
    }  
	
	def printlnMyHome(){
		return "我的老家在${homeAddr}"
	}
	
	def invokeMethod(String methodName, Object params) {  
	  // def method = Addr.metaClass.getMetaMethod(methodName,params)
	   println methodName
	   return "找不到方法"+methodName
	  /* long s = System.currentTimeMillis()
	   def result = method.invoke(delegate, params)
	   long e = System.currentTimeMillis()
	   long duration = e - s;
       println("MOP耗费时间：" + duration);
	   println "Addr was asked to invoke ${methodName}"
	   if(params != null){ 
		params.each{ println "\twith parameter ${it}" } 
	   }  */
	 } 
	  
	 def getProperty(String property){ 
		//println "Addr was asked for property ${property}"
	 }  
}  
 
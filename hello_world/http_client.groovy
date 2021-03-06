import static groovyx.net.http.Method.GET  
import static groovyx.net.http.ContentType.TEXT  

def http = new HTTPBuilder( 'http://ajax.googleapis.com' )    
    
// perform a GET request, expecting JSON response data    
http.request( GET, JSON ) {    
  url.path = '/ajax/services/search/web'    
  url.query = [ v:'1.0', q: 'Calvin and Hobbes' ]    
    
  headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'    
    
  // response handler for a success response code:    
  response.success = { resp, json ->    
    println resp.statusLine    
    
    // parse the JSON response object:    
    json.responseData.results.each {     
      println "  ${it.titleNoFormatting} : ${it.visibleUrl}"    
    }    
  }    
    
  // handler for any failure status code:    
  response.failure = { resp ->    
    println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"     
  }    
}   
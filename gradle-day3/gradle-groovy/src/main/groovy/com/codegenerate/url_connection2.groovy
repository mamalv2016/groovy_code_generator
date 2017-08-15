package com.codegenerate
import groovyx.net.http.HTTPBuilder
import spock.lang.Specification;
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*

class HttpbuildLabSpec extends Specification{
	HTTPBuilder http = new HTTPBuilder( 'http://m.weather.com.cn' )
	public void testRequestWeather(){
		when:
		def info =""
		http.request( GET, JSON ) {
			url.path = 'http://fm.lvmama.com/traffic-vas-service/vas/inter/checkTransferCity'
			headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
			response.success = { resp, json ->
				info = json.weatherinfo.city
			}
			response.failure = { resp -> println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}" }
		}
		then:  "曲靖"==info
	}
}
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def source = new File("d:\\flight.txt").text

def slurper = new JsonSlurper()

def printAllCarrieName = {
    it-> 
    def allCarrieName = []
    def allFlight = it.results
    allFlight.each{
        a ->  allCarrieName<<a.carrierName
    }
    
    println allCarrieName
}

def jsonObject = slurper.parseText(source)

printAllCarrieName(jsonObject)

def map = [:] 
map = ['name':'Bruce', 'age':27]  
def json = JsonOutput.toJson(map)
println json
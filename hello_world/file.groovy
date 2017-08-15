//打印一个文件夹下面的全部目录，全部文件
def dir =new File("D:\\回退0627") 
def newDir = "d:\\新code"
if (dir.isDirectory()) {  
    dir.eachFileRecurse { file ->  
        if(!file.isDirectory()){ 
            printlnFileName(file)
            assembleFiles(file,newDir);
            //if( file.name =~ /\.groovy$/)
            //    writeUtf8(file.absolutePath,file.text)
        }
    }  
} 
println 'ok'

def printlnFileName(file){
   println file.absolutePath
}

def assembleFiles(file,dirname){
   new File(dirname+"\\"+file.name).withWriter('utf-8') { writer ->
        writer.write file.text
   }
}

def writeUtf8(filename,str){
    new File(filename).withWriter('utf-8') { writer ->
        writer.write str
    }
}

/*new File(fileName).withPrintWriter { printWriter ->  
     printWriter.println('The first content of file')  
}  
*/
//println new File("d:\\cpdetail.txt").text  
//dir.eachFileMatch(~/.*\.xml/) {File it-> println it.name  } //使正则表达式匹配文件名  

//dir.eachFileMatch(FILES, ~/.*\.txt/) { File it-> println it.name  }   

/*
def name='test'         //单引号
//双引号中可以有变量引用
println "$name"=='test'   
*/ 
/*println '''       //三引号里面的变量引用使用了${},注意!三引号里面可以换行..
        ${name},
        保持格式，空格字符串
' ''
*/
/*
def name2 = 'Groovy'
def template = """
    Dear Mr ${name2},

    You're the winner of the lottery!

    Yours sincerly,

    Dave
"""
println template
*/
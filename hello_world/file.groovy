//��ӡһ���ļ��������ȫ��Ŀ¼��ȫ���ļ�
def dir =new File("D:\\����0627") 
def newDir = "d:\\��code"
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
//dir.eachFileMatch(~/.*\.xml/) {File it-> println it.name  } //ʹ������ʽƥ���ļ���  

//dir.eachFileMatch(FILES, ~/.*\.txt/) { File it-> println it.name  }   

/*
def name='test'         //������
//˫�����п����б�������
println "$name"=='test'   
*/ 
/*println '''       //����������ı�������ʹ����${},ע��!������������Ի���..
        ${name},
        ���ָ�ʽ���ո��ַ���
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
//��ӡһ���ļ��������ȫ��Ŀ¼��ȫ���ļ�
def dir =new File("C:\\Users\\Administrator\\Desktop\\��Ӧ��ͬ�����д���") 
if (dir.isDirectory()) {  
    dir.eachFileRecurse { file ->  
        if(!file.isDirectory())
            println file
    }  
} 

/*new File(fileName).withPrintWriter { printWriter ->  
     printWriter.println('The first content of file')  
}  
*/
//println new File("d:\\cpdetail.txt").text  
//dir.eachFileMatch(~/.*\.xml/) {File it-> println it.name  } //ʹ������ʽƥ���ļ���  

//dir.eachFileMatch(FILES, ~/.*\.txt/) { File it-> println it.name  }   

def name='test'         //������
//˫�����п����б�������
println "$name"=='test'    
println '''       //����������ı�������ʹ����${},ע��!������������Ի���..
        ${name},
        ���ָ�ʽ���ո��ַ���
'''

def name2 = 'Groovy'
def template = """
    Dear Mr ${name2},

    You're the winner of the lottery!

    Yours sincerly,

    Dave
"""
println template
def name='test'         //单引号
assert "$name"=='test'     //双引号中可以有变量引用
println '''       //三引号里面的变量引用使用了${},注意!三引号里面可以换行..
        ${name},
        保持格式，空格字符串
'''
def firstname='Kate'
def surname='Bush'
//打印字符串的连接
assert firstname*2=='KateKate'
def fullname="$firstname $surname"
assert fullname=='Kate Bush'
assert fullname-firstname==' Bush'

//凑足15个字符,不够就在左边补充空格
assert fullname.padLeft(15)=='      Kate Bush'
//关于字符串的截取
assert fullname[0..3]=='Kate'
assert fullname[-4..-1]=='Bush'
//下面的方式比较特别,取第5个字符,以及3,2,1位置字符连接起来
assert fullname[5,3..1]=='Beta'
assert'apple'.toList() == ['a', 'p', 'p', 'l', 'e']
//下面把字符串里面去掉重复数字,并排序.
string = "an apple a day"
assert string.toList().unique().sort().join() == ' adelnpy'

//下面方法取出得到字符串里面的各个单词,并进行反转
string = 'Yoda said, "can you see this?"'
revwords= string.split(' ').toList().reverse().join(' ')
assert revwords== 'this?" see you "can said, Yoda'
 

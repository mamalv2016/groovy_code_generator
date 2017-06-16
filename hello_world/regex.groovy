def str='12345'
//判断正则表达式匹配
if( str =~ /\d+/)
    println '全数字';
else
    println '不全是数字'    
   
//正则表达式替换 
def abc = str.replaceAll(/\d/,'x')       //和js里面的正则表达式一样？
assert abc == 'xxxxx' 
    
println str.replace(/\d/,'x') 
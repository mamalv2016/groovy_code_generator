def x=3
def y=4
assert x+y==7
assert x.plus(y)==7
assert x instanceof Integer==true //输出true

//进行小数的取三位小数运算.
def a=2/3
def b = a.setScale(3,BigDecimal.ROUND_HALF_UP)
assert b.toString() == '0.667'

//进行不等于的比较
assert 4<=>3 ==1  //输出1
assert 4**3 == 64 //4的3次方,等于4.power(3)
println 4/3     //输出1.3333333333,等于4.div(3)
assert 8.intdiv(3) ==2 //输出1
assert 8%3 ==2 //取余,等于8.mod(3)
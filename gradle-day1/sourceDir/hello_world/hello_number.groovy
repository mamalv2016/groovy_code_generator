def x=3
def y=4
assert x+y==7
assert x.plus(y)==7
assert x instanceof Integer==true //���true

//����С����ȡ��λС������.
def a=2/3
def b = a.setScale(3,BigDecimal.ROUND_HALF_UP)
assert b.toString() == '0.667'

//���в����ڵıȽ�
assert 4<=>3 ==1  //���1
assert 4**3 == 64 //4��3�η�,����4.power(3)
println 4/3     //���1.3333333333,����4.div(3)
assert 8.intdiv(3) ==2 //���1
assert 8%3 ==2 //ȡ��,����8.mod(3)
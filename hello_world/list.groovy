def list = [-1, 2, 3, 4]
//ѭ��list
list.each{
   println it
}

//������list���д���
def result = list.collect{it*2}
assert result,[-2, 4, 6, 8]


//�ж�����list�Ƿ�����������>0
def isMoreThanZero = list.every{ it>0 }
assert !isMoreThanZero , true

//�ж��Ƿ��д���0��
def anyThanZero = list.any{ it<0 }
assert anyThanZero,true

//���ӷ��ţ�join
assert list.join(','), '-1,2,3,4'

//ѭ��map
def map = ['name':'��ˮ��','score':123]
map.each{it->
    println it.key+","+it.value
} 
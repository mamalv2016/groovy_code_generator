def list = [-1, 2, 3, 4]
//循环list
list.each{
   println it
}

//对整个list进行处理
def result = list.collect{it*2}
assert result,[-2, 4, 6, 8]


//判断整个list是否都满足条件：>0
def isMoreThanZero = list.every{ it>0 }
assert !isMoreThanZero , true

//判断是否有大于0的
def anyThanZero = list.any{ it<0 }
assert anyThanZero,true

//连接符号：join
assert list.join(','), '-1,2,3,4'

//循环map
def map = ['name':'李水清','score':123]
map.each{it->
    println it.key+","+it.value
} 
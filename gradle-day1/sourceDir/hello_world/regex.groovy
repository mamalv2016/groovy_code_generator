def str='12345'
//�ж�������ʽƥ��
if( str =~ /\d+/)
    println 'ȫ����';
else
    println '��ȫ������'    
   
//������ʽ�滻 
def abc = str.replaceAll(/\d/,'x')       //��js�����������ʽһ����
assert abc == 'xxxxx' 
    
println str.replace(/\d/,'x') 
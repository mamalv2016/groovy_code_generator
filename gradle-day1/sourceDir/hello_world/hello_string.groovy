def name='test'         //������
assert "$name"=='test'     //˫�����п����б�������
println '''       //����������ı�������ʹ����${},ע��!������������Ի���..
        ${name},
        ���ָ�ʽ���ո��ַ���
'''
def firstname='Kate'
def surname='Bush'
//��ӡ�ַ���������
assert firstname*2=='KateKate'
def fullname="$firstname $surname"
assert fullname=='Kate Bush'
assert fullname-firstname==' Bush'

//����15���ַ�,����������߲���ո�
assert fullname.padLeft(15)=='      Kate Bush'
//�����ַ����Ľ�ȡ
assert fullname[0..3]=='Kate'
assert fullname[-4..-1]=='Bush'
//����ķ�ʽ�Ƚ��ر�,ȡ��5���ַ�,�Լ�3,2,1λ���ַ���������
assert fullname[5,3..1]=='Beta'
assert'apple'.toList() == ['a', 'p', 'p', 'l', 'e']
//������ַ�������ȥ���ظ�����,������.
string = "an apple a day"
assert string.toList().unique().sort().join() == ' adelnpy'

//���淽��ȡ���õ��ַ�������ĸ�������,�����з�ת
string = 'Yoda said, "can you see this?"'
revwords= string.split(' ').toList().reverse().join(' ')
assert revwords== 'this?" see you "can said, Yoda'
 

package Lec09;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		//applicationContext.xml�� ���� �޸𸮿� load�� bean�� �����ϱ� ���� ������ �����̳� ��ü ����
		
		Person p = ctx.getBean("person", Person.class);
		//������ Bean���� id �� person�� ��ü�� �����´�.
		
		p.pTalk();
		//pTalk�� Person�� �Ӽ���  brain�� ����  talk()�� ȣ���ϴ� �Լ�
		//�ش� ������Ʈ������ Person���� @Autowired�� ���� brain ��ü�� injection ���ֱ� ������ �������� �Լ��� ȣ��ȴ�.
		
		//Person p = new Person();
		//p.pTalk();
		
		//���� ���� p�� �����ϰ� pTalk�� ȣ���ߴٸ� brain�� p�� injection�Ǿ� ���� �����Ƿ� nullpointer ������ �߻��Ѵ�.
		
		Person p_self = new Person(new Brain());
		p_self.pTalk();
		//Brain�� ���� injection ���־�� ������ �߻����� �ʴ´�. 
		
		ctx.close();
	}
}

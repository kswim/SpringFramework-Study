package Lec10;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Person p = ctx.getBean("person", Person.class);
		//xml ���������� ���� ������ Bean�� ������. �� �� @Autowired �� ���� ������ü�� ���Եȴ�.
		
		p.pTalk();
		//xml�� 2���� brain bean�� �����Ǿ� �����Ƿ� ���� pTalk ���� qualifier�� ���� ������ bean�� ���ԵǾ� ���� �ʾҴٸ� ������ ����.
		
		ctx.close();
	
	}

}

package Lec11;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		Person p = ctx.getBean("person", Person.class);
		//Person�� ������ �Ҹ������ interface�� ���ؼ� Ư���Լ��� ����ǵ��� ������.
		
		Brain b = ctx.getBean("brain", Brain.class);
		//Brain�� ������ �Ҹ������ bean�� �Ӽ��� init-method�� destroy-method�� ���ؼ� Ư���Լ��� ����ǵ��� ������.
		
		ctx.close();
	}
}

package Lec11;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		Person p = ctx.getBean("person", Person.class);
		//Person의 생성과 소멸시점에 interface를 통해서 특정함수가 수행되도록 설정함.
		
		Brain b = ctx.getBean("brain", Brain.class);
		//Brain의 생성과 소멸시점에 bean의 속성의 init-method와 destroy-method를 통해서 특정함수가 수행되도록 설정함.
		
		ctx.close();
	}
}

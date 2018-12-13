package Lec12;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx 
			= new AnnotationConfigApplicationContext(Config.class);
		//xml 파일이 아닌 자바파일을 통해서 생성한 bean을 가져올 수 있도록 스프링 컨테이너를 생성.
		
		Person p = ctx.getBean("person", Person.class);
		p.ptalk();
		
		Brain b = ctx.getBean("brain", Brain.class);
		b.talk();
	
		ctx.close();
		
	}
}

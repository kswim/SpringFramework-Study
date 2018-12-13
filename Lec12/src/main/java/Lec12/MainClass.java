package Lec12;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx 
			= new AnnotationConfigApplicationContext(Config.class);
		//xml ������ �ƴ� �ڹ������� ���ؼ� ������ bean�� ������ �� �ֵ��� ������ �����̳ʸ� ����.
		
		Person p = ctx.getBean("person", Person.class);
		p.ptalk();
		
		Brain b = ctx.getBean("brain", Brain.class);
		b.talk();
	
		ctx.close();
		
	}
}

package Lec09;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		//applicationContext.xml을 통해 메모리에 load된 bean에 접근하기 위해 스프링 컨테이너 객체 생성
		
		Person p = ctx.getBean("person", Person.class);
		//생성된 Bean에서 id 가 person인 객체를 가져온다.
		
		p.pTalk();
		//pTalk는 Person의 속성인  brain을 통해  talk()를 호출하는 함수
		//해당 프로젝트에서는 Person에서 @Autowired을 통해 brain 객체를 injection 해주기 때문에 문제없이 함수가 호출된다.
		
		//Person p = new Person();
		//p.pTalk();
		
		//위와 같이 p를 생성하고 pTalk를 호출했다면 brain이 p에 injection되어 있지 않으므로 nullpointer 에러가 발생한다.
		
		Person p_self = new Person(new Brain());
		p_self.pTalk();
		//Brain을 직접 injection 해주어야 에러가 발생하지 않는다. 
		
		ctx.close();
	}
}

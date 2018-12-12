package Lec10;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Person p = ctx.getBean("person", Person.class);
		//xml 설정파일을 통해 생성된 Bean을 가져옴. 이 때 @Autowired 를 통해 의존객체가 주입된다.
		
		p.pTalk();
		//xml에 2개의 brain bean이 생성되어 있으므로 만약 pTalk 전에 qualifier을 통해 적절한 bean이 주입되어 있지 않았다면 에러가 난다.
		
		ctx.close();
	
	}

}

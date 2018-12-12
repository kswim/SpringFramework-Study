package Lec10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Person {

	@Autowired
	@Qualifier("brain1") //applicationContext.xml에 2개의 brain bean이 존재하므로 명시해주지 않으면 에러가 난다.
	private Brain brain;
	
	public Person () {
		
	}
	
	public void pTalk() {
		brain.talk();
	}
}

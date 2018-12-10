package Lec09;

import javax.annotation.Resource; //@Resource 로 사용할 때 필요한 import
import org.springframework.beans.factory.annotation.Autowired; //@Autowired 를 사용할 때 필요한 import

public class Person {
	
	//constructor, property, method에  @Autowired 사용할 수 있다. (@Resource는 property, method에만 사용가능)
	@Autowired
	private Brain brain;
	
	public Person() {
		
	}
	
	public Person(Brain brain) {
		this.brain = brain;
	}
	
	
	public void pTalk() {
		brain.talk();
	}


}
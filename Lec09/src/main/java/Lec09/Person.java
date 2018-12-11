package Lec09;

import javax.annotation.Resource; //@Resource �� ����� �� �ʿ��� import
import org.springframework.beans.factory.annotation.Autowired; //@Autowired �� ����� �� �ʿ��� import

public class Person {
	
	//constructor, property, method��  @Autowired ����� �� �ִ�. (@Resource�� property, method���� ��밡��)
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
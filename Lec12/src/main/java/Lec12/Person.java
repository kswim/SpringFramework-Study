package Lec12;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {

	@Autowired
	private Brain brain;
	
	public Person() {
		
	}
	
	public Person(Brain brain) {
		this.brain = brain;
	}
	
	public void ptalk() {
		System.out.println("Person!!");
	}
	
}

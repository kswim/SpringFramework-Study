package Lec10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Person {

	@Autowired
	@Qualifier("brain1") //applicationContext.xml�� 2���� brain bean�� �����ϹǷ� ��������� ������ ������ ����.
	private Brain brain;
	
	public Person () {
		
	}
	
	public void pTalk() {
		brain.talk();
	}
}

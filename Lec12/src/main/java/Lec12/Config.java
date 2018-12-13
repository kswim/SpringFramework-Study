package Lec12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //������ �����̳ʸ� ������ �� �ֵ��� �˷��ִ� ������̼�
public class Config {
	
	@Bean
	public Brain brain() {
		//<Bean id="brain" class="Lec12.Brain" /> 
		return new Brain();
	}

	@Bean
	public Person person() {
		//<bean id="person" class="Lec12.Person" >
		//	<constructor-arg ref="brain" />
		//</bean>
		return new Person(brain());
	}

}

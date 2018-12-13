package Lec12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 컨테이너를 생성할 수 있도록 알려주는 어노테이션
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

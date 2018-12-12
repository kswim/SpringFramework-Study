package Lec11;

import javax.security.auth.Destroyable;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {
	
	public Person() {
		
	}
	
	public void afterPropertiesSet() throws Exception {
		System.out.println("[interface] create a bean.");
	}

	public void destroy() throws Exception {
		System.out.println("[interface] destroy a bean.");
		
	}



}

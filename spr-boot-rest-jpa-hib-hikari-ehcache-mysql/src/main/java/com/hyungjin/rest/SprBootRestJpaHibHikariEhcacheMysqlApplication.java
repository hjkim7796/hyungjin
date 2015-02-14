package com.hyungjin.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hyungjin.rest.model.User;
import com.hyungjin.rest.repository.UserRepository;

@SpringBootApplication
public class SprBootRestJpaHibHikariEhcacheMysqlApplication {

    public static void main(String[] args) {
    	ApplicationContext ctx = SpringApplication.run(SprBootRestJpaHibHikariEhcacheMysqlApplication.class, args);
    	UserRepository bean = ctx.getBean( UserRepository.class );
    	//bean.deleteAll();
    	int id=1;
    	for(int i=1;i <=1000;i++) {
    		try {
    			bean.save(new User("userId"+id, "User"+id,"passwd"+id,"user"+id + "@email.com"));
    		} catch(Exception e){}
    		id++;
    	}
    }
}

package com.example.hibernate2;

import com.example.hibernate2.dao.SchoolClassDAO;
import com.example.hibernate2.dao.StudentDAO;
import com.example.hibernate2.model.SchoolClass;
import com.example.hibernate2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ApplicationContext applicationContext;

	// REFLECTION

	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setApplicationContext(applicationContext);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	StudentDAO studentDAO() {
		return new StudentDAO(Student.class);
	}

	@Bean
	SchoolClassDAO schoolClassDAO() {
		return new SchoolClassDAO(SchoolClass.class);
	}
}

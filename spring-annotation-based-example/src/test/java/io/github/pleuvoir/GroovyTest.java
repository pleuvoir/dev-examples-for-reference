package io.github.pleuvoir;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.github.pleuvoir.groovy.TimeService;

@SuppressWarnings("resource")
public class GroovyTest {


	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		TimeService timeService = context.getBean(TimeService.class);

		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("输入任意键");
			in.next();
			System.out.println(timeService.now());
		}
	}


}

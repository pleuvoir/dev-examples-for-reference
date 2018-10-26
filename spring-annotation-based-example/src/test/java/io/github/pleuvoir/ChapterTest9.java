package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.chapter9.Config9;

public class ChapterTest9 {

	@Test
	public void context() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config9.class);

	}

}

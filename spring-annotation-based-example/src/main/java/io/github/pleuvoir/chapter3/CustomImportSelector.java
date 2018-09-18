package io.github.pleuvoir.chapter3;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CustomImportSelector implements ImportSelector {
	
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// 全限定类名
		return new String[] { "io.github.pleuvoir.base.Cat", "io.github.pleuvoir.base.Monkey" };
	}
	
}

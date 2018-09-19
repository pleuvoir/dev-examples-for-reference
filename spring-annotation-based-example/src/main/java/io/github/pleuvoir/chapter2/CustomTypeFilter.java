package io.github.pleuvoir.chapter2;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 自定义的扫描规则过滤器
 * @author pleuvoir
 *
 */
public class CustomTypeFilter implements TypeFilter {

	/**
	 * MetadataReader：读取到当前正在扫描类的信息 
	 * MetadataReaderFactory：可以获取到其他任何类信息
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {

		// 如果扫描的类包含字符串 user 则注册
		boolean contains = metadataReader.getClassMetadata().getClassName().contains("User");
		System.out.println(metadataReader.getClassMetadata().getClassName()  + " 类名字符串包含 User? 答：" + contains);
		return contains;
	}

}

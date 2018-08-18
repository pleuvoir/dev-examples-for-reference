package io.github.pleuvoir.xml.dom4j;

import java.io.InputStream;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import lombok.SneakyThrows;

public class Client {

	@SneakyThrows
	public static void main(String[] args) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		// 获得根节点元素，这是所有操作的第一步  相当于取得了<config> ... </config>
		Element config = doc.getRootElement();
		// 取得 <mode>debug</mode>
		String mode = config.elementTextTrim("mode");
		System.out.println(mode);
		// 取得 <cache> ... </cache>
		Element cache = config.element("cache");
		String persist = cache.elementTextTrim("persist");
		String location = cache.elementTextTrim("location");
		System.out.println(persist);
		System.out.println(location);
		// 取得<datasources> ... </<datasources>
		Element datasources = config.element("datasources");
		Iterator<Element> datasourceIterator = datasources.elementIterator();
		// 遍历每个元素
		while (datasourceIterator.hasNext()) {
			Element datasource = (Element) datasourceIterator.next();
			System.out.println("[name]：" + datasource.elementText("name") + "，[driverclass]：" + datasource.elementText("driverclass"));
		}
	}
}

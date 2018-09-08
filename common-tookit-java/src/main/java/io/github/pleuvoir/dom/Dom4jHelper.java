package io.github.pleuvoir.dom;

import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * DOM4J操作工具类，考虑到不经常使用，故将方法在此抽象方便使用
 * @author pleuvoir
 *
 */
public abstract class Dom4jHelper {
	
	/**
	 * 获取文档的根节点
	 * @param document
	 * @return 
	 */
	public static Element getRootElement(Document doc){
		return doc.getRootElement();
	}
	
	/**
	 * 从根节点下获取指定节点中文本 
	 * @param rootElem 根节点
	 * @param label    标记
	 * @return
	 */
	public static String getElementTextTrim(Element rootElem, String label) {
		return rootElem.elementTextTrim(label);
	}
	
	/**
	 * 从某一节点下获取它的子节点
	 * @param document
	 * @return 
	 */
	public static Element getElement(Element rootElement, String label) {
		Element element = rootElement.element(label);
		if (element == null)
			throw new IllegalStateException();
		return element;
	}
	
	/**
	 * 从某个节点下获得所有相同标识子节点元素的集合，配合while使用
	 * @param elem
	 * @param label
	 * @return
	 */
	public static Iterator<Element> getElementIterator(Element elem, String label) {
		if (elem == null)
			throw new IllegalStateException();
		return elem.elementIterator(label);
	}
	
	/**
	 * 从元素中获取值
	 * @param elem
	 * @param name
	 * @return
	 */
	public static String getStrValue(Element elem, String name) {
		return elem.attributeValue(name);
	}
	
}
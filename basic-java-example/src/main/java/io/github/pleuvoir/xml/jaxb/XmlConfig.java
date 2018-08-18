package io.github.pleuvoir.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * 对标：src/main/resources/config.xml
 * @author pleuvoir
 *
 */
@XmlRootElement(name = "config") 	// 根节点元素名 必须有
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlConfig {

	@XmlElement(name = "mode")
	@Getter
	private String mode;		

	@XmlElement(name = "cache") 
	@Getter 
	private CacheConfig cache;

	@XmlElementWrapper(name = "datasources")
	@XmlElement(name = "datasource")
	@Getter
	private List<Datasource> datasources;    

}
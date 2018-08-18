package io.github.pleuvoir.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Getter;

@XmlAccessorType(XmlAccessType.FIELD)
public class CacheConfig {

	@XmlElement(name = "persist")  //一样的情况可忽略name
	@Getter
	private boolean persist;
	
	@XmlElement
	@Getter
	private String location;
}

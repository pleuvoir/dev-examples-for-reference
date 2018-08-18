package io.github.pleuvoir.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Datasource {

	@XmlElement
	@Getter
	private String name;
	
	@XmlElement
	@Getter
	private String driverclass;
}

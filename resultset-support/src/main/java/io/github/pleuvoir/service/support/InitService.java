package io.github.pleuvoir.service.support;

import io.github.pleuvoir.bean.MetaData;
import io.github.pleuvoir.bean.TemplateFactory;

public interface InitService {

	MetaData initialize();
	
	TemplateFactory initTemplate();
}

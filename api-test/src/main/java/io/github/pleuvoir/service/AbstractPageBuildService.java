package io.github.pleuvoir.service;

import java.util.List;

import io.github.pleuvoir.model.Column;
import io.github.pleuvoir.model.Page;
import io.github.pleuvoir.utils.ServletUtil;


public abstract class AbstractPageBuildService implements PageBuildService<Page> {
	
	public abstract List<Column> init() ;

	@Override
	public Page build() {
		return Page.create()
				.title(getTitle())
				.description(getDescription())
				.requestURL(ServletUtil.getSessionAttr("endPoint") + getPath())
				.columns(init())
				.build();
	};
	
	@Override
	public String getDescription() {
		return getAnnotationOfInfo().desc();
	}

	@Override
	public String getTitle() {
		return getAnnotationOfInfo().title();
	}

	@Override
	public String getPath() {
		return getAnnotationOfInfo().path();
	}

	private Info getAnnotationOfInfo(){
		return this.getClass().getAnnotation(Info.class);
	}
	
}

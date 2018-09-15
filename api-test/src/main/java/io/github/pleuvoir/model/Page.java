package io.github.pleuvoir.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderMethodName = "create")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {

	private String title;

	private String description;
	
	private String requestURL;
	
	private List<Column> columns;

}

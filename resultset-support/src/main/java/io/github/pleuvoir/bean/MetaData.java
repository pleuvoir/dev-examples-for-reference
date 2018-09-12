package io.github.pleuvoir.bean;

import java.util.List;
import lombok.Data;

@Data
public class MetaData {

	private List<ColumnExtend> columnExtendList;

	public MetaData(List<ColumnExtend> columnExtendList) {
		super();
		this.columnExtendList = columnExtendList;
	}
	
}

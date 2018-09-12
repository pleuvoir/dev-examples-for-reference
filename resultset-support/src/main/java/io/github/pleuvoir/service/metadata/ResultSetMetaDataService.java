package io.github.pleuvoir.service.metadata;

import java.util.List;

import io.github.pleuvoir.bean.ColumnExtend;
import io.github.pleuvoir.service.convert.ConverterService;

public interface ResultSetMetaDataService {

	List<ColumnExtend> query(String sql, ConverterService convertTypeService);
}

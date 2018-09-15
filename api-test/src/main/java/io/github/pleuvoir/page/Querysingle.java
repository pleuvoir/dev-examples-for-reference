package io.github.pleuvoir.page;

import java.util.Arrays;
import java.util.List;
import io.github.pleuvoir.model.Column;
import io.github.pleuvoir.service.AbstractPageBuildService;
import io.github.pleuvoir.service.PageService.Info;

@Info(desc = "接口说明：订单单笔查询", title = "订单单笔查询", path = "/query/single")
public class Querysingle extends AbstractPageBuildService{
	
	@Override
	public List<Column> init() {
		return Arrays.asList(
				Column.of("mid").value("100000510983456").placeholder("商户号").remark("由平台分配的商户号"),
				Column.of("orderNo").placeholder("商户订单号").remark("商户系统中的订单号"),
				Column.of("noise").value("6666").placeholder("随机字符串").remark("随机字符串，不长于32位")
		);
	}
	
}

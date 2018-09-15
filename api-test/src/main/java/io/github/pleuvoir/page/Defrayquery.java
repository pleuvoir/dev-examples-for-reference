package io.github.pleuvoir.page;

import java.util.Arrays;
import java.util.List;
import io.github.pleuvoir.model.Column;
import io.github.pleuvoir.service.AbstractPageBuildService;
import io.github.pleuvoir.service.PageService.Info;

@Info(desc = "接口说明：代付结果查询", title = "代付查询", path = "/defray/query")
public class Defrayquery extends AbstractPageBuildService {

	@Override
	public List<Column> init() {

		return Arrays.asList(
				Column.of("mid").placeholder("商户号").remark("由平台分配的商户号"),
				Column.of("orderNo").value("812017081524279").placeholder("商户订单号").remark("商户系统中的订单号"),
				Column.of("noise").value("6666").placeholder("随机字符串").remark("随机字符串，不长于32位"));
	}

}

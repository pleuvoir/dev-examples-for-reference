package io.github.pleuvoir.page;

import java.util.Arrays;
import java.util.List;
import io.github.pleuvoir.model.Column;
import io.github.pleuvoir.model.Option;
import io.github.pleuvoir.model.Select;
import io.github.pleuvoir.service.AbstractPageBuildService;
import io.github.pleuvoir.service.BuildService;
import io.github.pleuvoir.service.PageService.Info;


@Info(desc = "接口说明：获取二维码", title = "获取二维码", path = "/paycode")
public class QrCode extends AbstractPageBuildService{
	

	@Override
	public List<Column> init() {
		
		return Arrays.asList(
				Column.of("mid").value("100000510983456").placeholder("商户号").remark("由平台分配的商户号"),
				Column.of("orderNo").placeholder("商户订单号").remark("商户系统中的订单号"),
				Column.of("type").setSelect(new BuildService<Select>() {
					@Override
					public Select build() {
						Option wechat = new Option("03", "微信");
						Option alipay = new Option("04", "支付宝");
						Option jd     = new Option("03", "京东钱包");
						return new Select(Arrays.asList(wechat,alipay,jd));
					}
				}),
				Column.of("remark").value("我是文本域").placeholder("备注").setTextarea()
		);
	}

}

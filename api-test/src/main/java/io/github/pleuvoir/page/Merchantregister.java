package io.github.pleuvoir.page;

import java.util.Arrays;
import java.util.List;
import io.github.pleuvoir.model.Column;
import io.github.pleuvoir.service.AbstractPageBuildService;
import io.github.pleuvoir.service.PageService.Info;

@Info(path = "/merchant/register",desc = "接口说明：为商户提供商户注册接口。", title = "商户注册")
public class Merchantregister extends AbstractPageBuildService{


	@Override
	public List<Column> init() {
		
		return Arrays.asList(  
				Column.of("mid").value("812017081524279").placeholder("商户号").remark("由平台分配的商户号"),
				Column.of("merchantName").value("张三").placeholder("商户名称"),
				Column.of("merchantAlias").value("张三").placeholder("商户简称"),
				Column.of("weChatCategory").value("297").placeholder("微信经营类目"),
				Column.of("alipayCategory").value("2016062900190320").placeholder("支付宝经营类目"),
				Column.of("idCard").value("610321199203020082").placeholder("身份证号码"),
				Column.of("realName").value("张三").placeholder("真实姓名"),
				Column.of("phoneNumber").value("18628271614").placeholder("手机号码"),
				Column.of("bankCard").value("1234569871258453526").placeholder("借记卡号"),
				Column.of("bankLinked").value("102405041035").placeholder("借记卡联行号"),
				Column.of("creditBankCard").placeholder("信用卡号").remark("由平台分配的商户号").required(false),
				Column.of("creditBankLinked").placeholder("借记卡联行号").remark("由平台分配的商户号").required(false),
				Column.of("cardAccountType").value("01").placeholder("卡帐户类型").remark("01 个人 02 企业 目前只支持01个人"),
				Column.of("noise").value("5b923105cd0644a9b195663d13d673a4").placeholder("随机字符串").remark("随机字符串，不长于32位"),
				Column.of("extendParams").value("{'03':[{'01':'10.0'}],'04':[{'01':'20.0'}],'00':[{'D0':'0.1'},{'T1':'0.2'}]}")
								.placeholder("扩展参数").remark("存放支付种类费率及代付费率").setTextarea()
		);
	}

	
	
}

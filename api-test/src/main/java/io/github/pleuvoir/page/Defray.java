package io.github.pleuvoir.page;

import java.util.Arrays;
import java.util.List;
import io.github.pleuvoir.model.Column;
import io.github.pleuvoir.service.AbstractPageBuildService;
import io.github.pleuvoir.service.PageService.Info;

@Info(path = "/defray", desc = "接口说明：发起代付申请", title = "代付申请")
public class Defray extends AbstractPageBuildService {

	@Override
	public List<Column> init() {

		return Arrays.asList(
				Column.of("mid").placeholder("商户号").remark("由平台分配的商户号"),
				Column.of("orderNo").value("812017081524279").placeholder("商户订单号").remark("商户系统中的订单号"),
				Column.of("amount").value("10.01").placeholder("代付金额"),
				Column.of("receiveName").value("张三").placeholder("收款人姓名"),
				Column.of("openProvince").value("河北省").placeholder("开户省"),
				Column.of("openCity").value("石家庄").placeholder("开户市"),
				Column.of("bankCode").value("105").placeholder("收款银行编码").remark("见银行简码"),
				Column.of("bankBranchName").placeholder("渤海支行").remark("支行名称"), Column.of("cardNo").placeholder("卡号"),
				Column.of("bankLinked").placeholder("联行号").remark("对私账户不传,对公账户必传").required(false),
				Column.of("type").value("02").placeholder("代付类型").remark("01 普通 02 额度"),
				Column.of("cardAccountType").value("01").placeholder("卡帐户类型").remark("01个人 02 企业"),
				Column.of("extendParams").placeholder("扩展字段").remark("商户系统中的订单号").required(false),
				Column.of("noise").value("6666").placeholder("随机字符串").remark("随机字符串，不长于32位"));
	}

}

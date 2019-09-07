package io.github.pleuvoir.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentOrderPO {
	
	private String payId;
	
	private String payConfigId;
	
	private String appSourceId;
	
	private String busiUniqueId;
	
	private String outerTradeNo;
	
	private String payPassage;
	
	private String payChannel;
	
	private BigDecimal payAmount;
	
	private String payFeeType;
	
	private BigDecimal payFeeRate;
	
	private BigDecimal payFee;
	
	private BigDecimal refundedFee;
	
	private BigDecimal refundedAmount;
	
	private String isReturnFee;
	
	private String isLimitCreditcard;
	
	private String productName;
	
	private String buyerId;
	
	private String buyerAccount;
	
	private String receiveAccount;
	
	private String businessId;
	
	private String orderId;
	
	private String notifyUrl;
	
	private String notifyMqTopic;
	
	private String expireTime;
	
	private String payTime;
	
	private String createTime;
	
	private String updateTime;
	
	private String extendParams;
	
	private String status;
	
	private String remark;
	
	private String successUrl;
	
	private String bankId;
	
	private String payToken;
	
	private String cardType;
	
	private String divideStatus;
	
	private String cardPayType;
	
	private String productInfo;
	
	private String sceneId;
	
	private String userId;
	
	private String contractDisplayAccount;
	
	private String deductWay;
	
	private String contractCode;
	
}

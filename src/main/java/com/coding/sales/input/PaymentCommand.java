package com.coding.sales.input;

import java.math.BigDecimal;

/**
 * @desc 支付方式与金额
 * @author jianxinkuan
 * @date 2019年7月2日16点30分
 */
public class PaymentCommand {
    //支付方式名称
    String type;
    //支付金额
    BigDecimal amount;

    public PaymentCommand(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

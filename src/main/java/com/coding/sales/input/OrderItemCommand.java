package com.coding.sales.input;

import java.math.BigDecimal;

/**
 * @desc 商品名称与数量
 * @author jianxinkuan
 * @date 2019年7月2日16点28分
 */
public class OrderItemCommand {
    //商品名称
    String product;
    //购买数量
    BigDecimal amount;

    public OrderItemCommand(String product, BigDecimal amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

package com.coding.sales.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: kata-precious_metal_sales_system-master
 * @description: 商品信息
 * @author: admin
 * @create: 2019-07-02 17:04
 **/
public class Product implements Serializable {

    private static final long serialVersionUID = -2930175935815463028L;

    //商品编号
    private String id;

    //单位
    private String unit;

    //单价
    private BigDecimal price = BigDecimal.ZERO;

    //优惠信息
    private String[] discounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String[] getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String[] discounts) {
        this.discounts = discounts;
    }
}

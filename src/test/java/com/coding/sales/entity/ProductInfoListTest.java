package com.coding.sales.entity;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProductInfoListTest {

    /**
     * 测试查询商品信息
     */
    @Test
    public void should_get_product() {
        ProductInfo productInfo = ProductInfoList.getProductInfo("001001");
        assert productInfo != null;
        System.out.println(productInfo);
        assertNotEquals(null, productInfo);
    }

    /**
     * 测试查询商品信息为空
     */
    @Test
    public void should_get_null() {
        ProductInfo productInfo = ProductInfoList.getProductInfo("123");
        assertEquals(null, productInfo);
    }

}
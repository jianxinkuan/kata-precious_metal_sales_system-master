package com.coding.sales.entity;

import cn.hutool.core.util.StrUtil;
import com.coding.sales.common.Constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: kata-precious_metal_sales_system-master
 * @description: @desc 初始化商品
 * @author: admin
 * @create: 2019-07-02 18:12
 **/
public class ProductInfoList {

    private static List<ProductInfo> productInfoList = new ArrayList<>();

    static {
        productInfoList.add(new ProductInfo("001001", "世园会五十国钱币册", "册", new BigDecimal("998.00"), ""));
        productInfoList.add(new ProductInfo("001002", "2019北京世园会纪念银章大全40g", "盒", new BigDecimal("1380.00"), Constant.NINE_POINT));
        productInfoList.add(new ProductInfo("003001", "招财进宝", "条", new BigDecimal("1580.00"), Constant.NINE_HALF_POINT));
        productInfoList.add(new ProductInfo("003002", "水晶之恋", "条", new BigDecimal("980.00"), Constant.THREE_HALF + "," + Constant.THREE_ADD_ONE));
        productInfoList.add(new ProductInfo("002002", "中国经典钱币套装", "套", new BigDecimal("998.00"), Constant.TWO_THOUSAND + "," + Constant.ONE_THOUSAND));
        productInfoList.add(new ProductInfo("002001", "守扩之羽比翼双飞4.8g", "条", new BigDecimal("1080.00"), Constant.THREE_HALF + "," + Constant.THREE_ADD_ONE + "," + Constant.NINE_HALF_POINT));
        productInfoList.add(new ProductInfo("002003", "中国银象棋12g", "套", new BigDecimal("698.00"), Constant.THREE_THOUSAND + "," + Constant.TWO_THOUSAND + "," + Constant.ONE_THOUSAND + "," + Constant.NINE_POINT));
    }

    /**
     * 获取商品信息
     * @param product
     * @return
     */
    public static ProductInfo getProductInfo(String product) {
        if (StrUtil.isBlank(product)) {
            return null;
        }
        for (ProductInfo productInfo : productInfoList) {
            if (product.equals(productInfo.getProduct())) {
                return productInfo;
            }
        }
        return null;
    }
}

package com.coding.sales;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.coding.sales.common.Constant;
import com.coding.sales.entity.ProductInfo;
import com.coding.sales.entity.ProductInfoList;
import com.coding.sales.entity.Userinfo;
import com.coding.sales.entity.UserinfoList;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];
//        String jsonFileName = "D:/workspace_jxk/temp/kata-precious_metal_sales_system-master/src/test/resources/sample_command.json";
//        String txtFileName = "xxxxx.txt";
        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        //票证信息
        OrderRepresentation orderRepresentation = null;
        //先获取购买人信息
        Userinfo userinfo = UserinfoList.getUserinfo(command.getMemberId());
        //获取到购买的商品列表
        List<OrderItemCommand> orderItemCommands = command.getItems();
        //获取到支付信息
        List<PaymentCommand> paymentsInfo = command.getPayments();
        //优惠券
        List<String> discount = command.getDiscounts();
        //订单编号
        String orderId = command.getOrderId();
        //订单创建时间
        DateTime createTime = DateUtil.parse(command.getCreateTime(), "yyyy-MM-dd hh:mm:ss");
        //会员编号
        String memberNo = userinfo.getMemberId();
        //会员姓名
        String memberName = userinfo.getName();
        //原会员等级
        String oldMemberType = userinfo.getLevel();
        //新会员等级
        String newMemberType = userinfo.getLevel();//设置积分，会自动更新
        //本次消费会员新增的积分
        int memberPointsIncreased = 0;//需要计算
        //会员最新的积分( = 老积分 + memberPointsIncreased)
        int memberPoints = userinfo.getScore();
        //订单明细
        List<OrderItemRepresentation> orderItems = new ArrayList<>();
        //订单总金额
        BigDecimal totalPrice = BigDecimal.ZERO;
        //根据用户购买的商品列表信息组装数据
        Map<String,BigDecimal> products = new HashMap<String,BigDecimal>();
        for(OrderItemCommand o : orderItemCommands){
            //商品编号
            String product = o.getProduct();
            //数量
            BigDecimal amount = o.getAmount();
            if(products.get(product) == null ){
                products.put(product,amount);
            }else{
                products.put(product,products.get(product).add(amount));
            }
        }
        Iterator<String> productsIt = products.keySet().iterator();
        while(productsIt.hasNext()){
            //商品编号
            String productNo = productsIt.next();
            //商品数量
            BigDecimal amount = products.get(productNo);
            //商品详情
            ProductInfo productInfo = ProductInfoList.getProductInfo(productNo);
            //订单小计
            orderItems.add(new OrderItemRepresentation(productNo, productInfo.getName(),productInfo.getPrice(),amount,amount.multiply(productInfo.getPrice())));
            //累加订单总金额
            totalPrice = totalPrice.add(amount.multiply(productInfo.getPrice()));
        }
        //优惠明细
        List<DiscountItemRepresentation> discounts = new ArrayList<>();
        //优惠总金额
        BigDecimal totalDiscountPrice = BigDecimal.ZERO;
        //优惠券
        if(discount != null && discount.size()>0){
            //循环订单信息
            for(OrderItemRepresentation orderItem : orderItems){
                //商品编号
                String productNo =  orderItem.getProductNo();
                //商品详情
                ProductInfo productDetail = ProductInfoList.getProductInfo(productNo);
                //九折
                if(productDetail.getDiscounts().contains(Constant.NINE_POINT)){
                    discounts.add(new DiscountItemRepresentation(productNo,orderItem.getProductName(),orderItem.getSubTotal().subtract(orderItem.getSubTotal().multiply(new BigDecimal(0.9)))));
                    totalDiscountPrice = totalDiscountPrice.add(orderItem.getSubTotal().subtract(orderItem.getSubTotal().multiply(new BigDecimal(0.9))));
                }
            }
        }
        //应收金额
        BigDecimal receivables = BigDecimal.ZERO;
        //付款记录
        List<PaymentRepresentation> payments = new ArrayList<>();
        //根据用户的支付方式列表信息组装数据
        Map<String,BigDecimal> payMethod = new HashMap<String,BigDecimal>();
        for(PaymentCommand p : paymentsInfo){
            //支付方式
            String type = p.getType();
            //金额
            BigDecimal amount = p.getAmount();
            if(payMethod.get(type) == null ){
                payMethod.put(type,amount);
            }else{
                payMethod.put(type,payMethod.get(type).add(amount));
            }
        }
        Iterator<String> payMethodIt = payMethod.keySet().iterator();
        while(payMethodIt.hasNext()){
            //组装支付金额
            String type= payMethodIt.next();
            payments.add(new PaymentRepresentation(type,payMethod.get(type)));
        }
        //付款使用的打折券
        List<String> discountCards = discount;
        //优惠后总金额更新
        receivables = totalPrice.subtract(totalDiscountPrice);
        String level = userinfo.getLevel();
        if("普卡".equals(level)){
            memberPointsIncreased =  receivables.multiply(new BigDecimal(1)).intValue();
        }
        if("金卡".equals(level)){
            memberPointsIncreased = receivables.multiply(new BigDecimal(1.5)).intValue();
        }
        if("白金卡".equals(level)){
            memberPointsIncreased = receivables.multiply(new BigDecimal(1.8)).intValue();
        }
        if("钻石卡".equals(level)){
            memberPointsIncreased = receivables.multiply(new BigDecimal(2)).intValue();
        }
        //更新用户积分
        userinfo.setScore(userinfo.getScore()+memberPointsIncreased);
        memberPoints = userinfo.getScore();
        newMemberType = userinfo.getLevel();
        orderRepresentation = new OrderRepresentation(orderId,createTime,memberNo,memberName,oldMemberType,newMemberType,memberPointsIncreased,memberPoints,orderItems,totalPrice,discounts,totalDiscountPrice,receivables,payments,discountCards);
        return orderRepresentation;
    }
}

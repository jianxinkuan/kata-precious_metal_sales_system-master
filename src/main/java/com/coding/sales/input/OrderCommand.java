package com.coding.sales.input;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 订单信息表
 * @author jianxinkuan
 * @date 2019年7月2日16点26分
 */
public class OrderCommand {
    //订单ID
    private String orderId;
    //创建时间
    private String createTime;
    //会员ID
    private String memberId;
    //购买商品集合
    private List<OrderItemCommand> items;
    //支付信息集合
    private List<PaymentCommand> payments;
    //此订单所用优惠券
    private List<String> discounts;

    /**
     * @desc 把字符串信息转换为实体对象
     * @param orderCommand 字符串
     * @return
     */
    public static OrderCommand from(String orderCommand) {
        JSONObject jsonObject = new JSONObject(orderCommand);

        OrderCommand command = new OrderCommand(
                jsonObject.getString("orderId"),
                jsonObject.getString("createTime"),
                jsonObject.getString("memberId"),
                parseItems(jsonObject.getJSONArray("items")),
                parsePayments(jsonObject.getJSONArray("payments")),
                parseDiscounts(jsonObject.getJSONArray("discountCards"))
        );

        return command;
    }

    public OrderCommand(String orderId, String createTime, String memberId, List<OrderItemCommand> items, List<PaymentCommand> payments, List<String> discounts) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.memberId = memberId;
        this.items = items;
        this.payments = payments;
        this.discounts = discounts;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public List<OrderItemCommand> getItems() {
        return items;
    }

    public List<PaymentCommand> getPayments() {
        return payments;
    }

    public List<String> getDiscounts() {
        return discounts;
    }

    private static List<OrderItemCommand> parseItems(JSONArray jsonArray) {
        List<OrderItemCommand> results = new ArrayList<OrderItemCommand>();

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            results.add(new OrderItemCommand(object.getString("product"), object.getBigDecimal("amount")));
        }

        return results;
    }

    private static List<PaymentCommand> parsePayments(JSONArray jsonArray) {
        List<PaymentCommand> results = new ArrayList<PaymentCommand>();

        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            results.add(new PaymentCommand(object.getString("type"), object.getBigDecimal("amount")));
        }

        return results;
    }

    private static List<String> parseDiscounts(JSONArray jsonArray) {
        List<String> results = new ArrayList<String>();

        for (Object o : jsonArray) {
            results.add((String) o);
        }

        return results;
    }
}

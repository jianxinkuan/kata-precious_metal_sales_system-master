package com.coding.sales.entity;

/**
 * @desc 用户信息
 * @author jianxinkuan
 * @date 2019年7月2日16点56分
 */
public class Userinfo {

    //姓名
    private String name;
    //等级
    private String level;
    //卡号
    private String cardNum;
    //积分
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

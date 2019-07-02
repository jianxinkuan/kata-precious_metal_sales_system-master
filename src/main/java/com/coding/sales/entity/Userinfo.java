package com.coding.sales.entity;

import java.io.Serializable;

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
    private String memberId;
    //积分
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        if(this.score == null){
            return "普卡";
        }
        if(this.score >= 0 && this.score < 10000){
            return "普卡";
        }
        if(this.score >= 10000 && this.score < 50000){
            return "金卡";
        }
        if(this.score >= 50000 && this.score < 100000){
            return "白金卡";
        }
        if(this.score >= 100000){
            return "钻石卡";
        }
        return null;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Userinfo(String name, String level, String memberId, Integer score) {
        this.name = name;
        this.level = level;
        this.memberId = memberId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "name='" + name + '\'' +
                ", level='" + getLevel() + '\'' +
                ", memberId='" + memberId + '\'' +
                ", score=" + score +
                '}';
    }
}

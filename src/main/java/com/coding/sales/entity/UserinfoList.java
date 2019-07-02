package com.coding.sales.entity;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 初始化用户信息
 * @author jianxinkuan
 * @date 2019年7月2日17点24分
 */
public class UserinfoList {

    private static List<Userinfo> userinfoList = new ArrayList<Userinfo>();

    static {
        userinfoList.add(new Userinfo("马丁",null,"6236609999",9860));
        userinfoList.add(new Userinfo("王立",null,"6630009999",48860));
        userinfoList.add(new Userinfo("李想",null,"8230009999",98860));
        userinfoList.add(new Userinfo("张三",null,"9230009999",198860));
    }

    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
    public static Userinfo getUserinfo(String memberId){
        if(StrUtil.isBlank(memberId)){
            return null;
        }

        for(Userinfo userinfo : userinfoList){
            if(memberId.equals(userinfo.getMemberId())){
                return userinfo;
            }
        }

        return null;
    }

}

package com.coding.sales.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserinfoListTest {

    /**
     * 测试获取客户信息
     */
    @Test
    public void should_get_user() {
        Userinfo userinfo = UserinfoList.getUserinfo("6236609999");
        assert userinfo != null;
        System.out.println(userinfo);
        assertNotEquals(null, userinfo);
    }

    /**
     * 测试获取用户信息为空
     */
    @Test
    public void should_get_null() {
        Userinfo userinfo = UserinfoList.getUserinfo("123");
        assertEquals(null, userinfo);
    }

}
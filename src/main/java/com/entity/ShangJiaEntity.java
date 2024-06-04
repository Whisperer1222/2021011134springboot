package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("shangjia")
public class ShangJiaEntity<T> implements Serializable {
    public ShangJiaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYonghuming() {
        return yonghuming;
    }

    public void setYonghuming(String yonghuming) {
        this.yonghuming = yonghuming;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getLianxidianhua() {
        return lianxidianhua;
    }

    public void setLianxidianhua(String lianxidianhua) {
        this.lianxidianhua = lianxidianhua;
    }

    public String getYingyezhizhao() {
        return yingyezhizhao;
    }

    public void setYingyezhizhao(String yingyezhizhao) {
        this.yingyezhizhao = yingyezhizhao;
    }

    public String getYinhangzhanghao() {
        return yinhangzhanghao;
    }

    public void setYinhangzhanghao(String yinhangzhanghao) {
        this.yinhangzhanghao = yinhangzhanghao;
    }

    public String getShenfenzheng() {
        return shenfenzheng;
    }

    public void setShenfenzheng(String shenfenzheng) {
        this.shenfenzheng = shenfenzheng;
    }

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */

    private String yonghuming;

    /**
     * 密码
     */

    private String mima;

    /**
     * 姓名
     */

    private String xingming;

    /**
     * 头像
     */

    private String touxiang;

    /**
     * 性别
     */

    private String xingbie;

    /**
     * 联系电话
     */

    private String lianxidianhua;

    private String  yingyezhizhao;

    private String yinhangzhanghao;

    private String shenfenzheng;

    public String getSfsh() {
        return sfsh;
    }

    public void setSfsh(String sfsh) {
        this.sfsh = sfsh;
    }

    private String sfsh;
}

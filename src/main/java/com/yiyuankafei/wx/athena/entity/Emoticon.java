package com.yiyuankafei.wx.athena.entity;

public class Emoticon {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 标签
     */
    private String tag;

    /**
     * 地址
     */
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}
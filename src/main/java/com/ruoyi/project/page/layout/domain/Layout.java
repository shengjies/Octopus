package com.ruoyi.project.page.layout.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 页面布局类型 dev_page_layout
 */
public class Layout extends BaseEntity {
    private int id; //编号

    private int layout_type; //布局类型  1、宫格布局 2、轮播布局 3、产线平衡

    private String layout_name; // 布局名称

    private int layout_status; //布局状态  1、可用 0、禁用

    private int layout_num;//宫格数

    private String create_date; //创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLayout_type() {
        return layout_type;
    }

    public void setLayout_type(int layout_type) {
        this.layout_type = layout_type;
    }

    public String getLayout_name() {
        return layout_name;
    }

    public void setLayout_name(String layout_name) {
        this.layout_name = layout_name;
    }

    public int getLayout_status() {
        return layout_status;
    }

    public void setLayout_status(int layout_status) {
        this.layout_status = layout_status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getLayout_num() {
        return layout_num;
    }

    public void setLayout_num(int layout_num) {
        this.layout_num = layout_num;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "id=" + id +
                ", layout_type=" + layout_type +
                ", layout_name='" + layout_name + '\'' +
                ", layout_status=" + layout_status +
                ", create_date='" + create_date + '\'' +
                '}';
    }
}

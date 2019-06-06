package com.ruoyi.project.page.layout.service;

import com.ruoyi.project.page.layout.domain.Layout;

import java.util.List;

/**
 * 页面类型布局 逻辑层
 */
public interface ILayoutService {
    /**
     * 分页查询页面布局类型
     * @param layout
     * @return
     */
    List<Layout> selectLayoutList(Layout layout);

    /**
     * 查询所以的页面布局类型
     * @return
     */
    List<Layout> selectLayoutAll(int a);

    /**
     * 添加页面布局类型
     * @param layout
     * @return
     */
    int addLayoutInfo(Layout layout);

    /**
     * 修改页面布局信息
     * @param layout
     * @return
     */
    int updateUser(Layout layout);

    /**
     * 删除页面布局类型
     * @param id 编号
     * @return
     */
    int delLayoutById(int id);

    /**
     * 改变状态
     * @param layout
     * @return
     */
    int changeStatus(Layout layout);

    /**
     * 根据编号查询页面类型
     * @param id 编号
     * @return
     */
    Layout selectLayoutById(int id);
}

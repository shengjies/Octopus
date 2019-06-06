package com.ruoyi.project.page.layout.mapper;

import com.ruoyi.project.page.layout.domain.Layout;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LayoutMapper {
    /**
     * 根据条件分页查询
     * @param layout
     * @return 页面布局类型
     */

    List<Layout> selectLayoutList(Layout layout);

    /**
     * 查询所以的页面布局类型
     * @return
     */
    List<Layout> selectLayoutAll(@Param("a")int a);

    /**
     * 保存页面布局类型
     * @param layout
     * @return
     */
    int addLayoutInfo(Layout layout);

    /**
     * 改变状态
     * @param layout
     * @return
     */
    int changeStatus(Layout layout);

    /**
     * 根据编号查询页面布局类型
     * @param id
     * @return
     */
    Layout selectLayoutById(@Param("id") int id);

    /**
     * 修改类型数据
     * @param layout
     * @return
     */
    int updateUser(Layout layout);

    /**
     * 删除页面类型
     * @param id
     * @return
     */
    int delLayoutById(@Param("id")int id);

}

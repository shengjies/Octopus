package com.ruoyi.project.page.layout.service;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.page.layout.domain.Layout;
import com.ruoyi.project.page.layout.mapper.LayoutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 页面类型布局 逻辑层
 */
@Service("layout")
public class LayoutServiceImpl implements ILayoutService {

    private static final Logger log = LoggerFactory.getLogger(LayoutServiceImpl.class);

    @Autowired
    private LayoutMapper layoutMapper;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Layout> selectLayoutList(Layout layout) {

        return layoutMapper.selectLayoutList(layout);
    }

    @Override
    @DataSource(DataSourceType.SLAVE)
    public List<Layout> selectLayoutAll(int a) {
        return layoutMapper.selectLayoutAll(a);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int addLayoutInfo(Layout layout) {
        return layoutMapper.addLayoutInfo(layout);
    }


    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateUser(Layout layout) {
        return layoutMapper.updateUser(layout);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int delLayoutById(int id) {
        return layoutMapper.delLayoutById(id);
    }

    /**
     * 改变状态
     * @param layout
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int changeStatus(Layout layout) {
        return layoutMapper.changeStatus(layout);
    }

    /**
     * 根据编号查询页面类型
     * @param id 编号
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public Layout selectLayoutById(int id) {
        return layoutMapper.selectLayoutById(id);
    }
}

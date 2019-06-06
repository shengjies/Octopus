package com.ruoyi.project.erp.productLedgerDetail.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.erp.productLedgerDetail.domain.ProductLedgerDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品对账明细 数据层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface ProductLedgerDetailMapper 
{

	/**
     * 新增产品对账明细
     * 
     * @param productLedgerDetail 产品对账明细信息
     * @return 结果
     */
	@DataSource(DataSourceType.ERP)
	public int insertProductLedgerDetail(ProductLedgerDetail productLedgerDetail);

	/**
	 * 查询对应的详情信息
	 * @param legder_id 主表id
	 * @return
	 */
	@DataSource(DataSourceType.ERP)
	List<ProductLedgerDetail> findDetailByLedgerId(@Param("legder_id")int legder_id);
}
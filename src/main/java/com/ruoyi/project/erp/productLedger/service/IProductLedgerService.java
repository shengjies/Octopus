package com.ruoyi.project.erp.productLedger.service;

import com.ruoyi.project.erp.productLedger.domain.ProductLedger;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品对账 服务层
 * 
 * @author zqm
 * @date 2019-05-13
 */
public interface IProductLedgerService 
{
	/**
     * 查询产品对账信息
     * 
     * @param id 产品对账ID
     * @return 产品对账信息
     */
	public ProductLedger selectProductLedgerById(Integer id);
	
	/**
     * 查询产品对账列表
     * 
     * @param productLedger 产品对账信息
     * @return 产品对账集合
     */
	public List<ProductLedger> selectProductLedgerList(ProductLedger productLedger, HttpServletRequest request);
	
	/**
     * 新增产品对账
     * 
     * @param productLedger 产品对账信息
     * @return 结果
     */
	public int insertProductLedger(ProductLedger productLedger,HttpServletRequest request);
	
	/**
     * 修改产品对账
     * 
     * @param productLedger 产品对账信息
     * @return 结果
     */
	public int updateProductLedger(ProductLedger productLedger);

	/**
	 * 状态修改
	 * @param productLedger
	 * @return
	 */
	int cancelLedger(ProductLedger productLedger,HttpServletRequest request);
		
	/**
     * 删除产品对账信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductLedgerByIds(String ids);

	/**
	 * 导出产品对账单
	 * @param id 产品对账单id
	 * @return 结果
	 */
    Workbook uploadProLed(Integer id);
}

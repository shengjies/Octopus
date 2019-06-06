package com.ruoyi.project.erp.productLedger.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.erp.customer.domain.Customer;
import com.ruoyi.project.erp.customer.mapper.CustomerMapper;
import com.ruoyi.project.erp.materielLedgerDetail.domain.MaterielLedgerDetail;
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import com.ruoyi.project.erp.productIntoStockDetails.mapper.ProductIntoStockDetailsMapper;
import com.ruoyi.project.erp.productLedgerDetail.domain.ProductLedgerDetail;
import com.ruoyi.project.erp.productLedgerDetail.mapper.ProductLedgerDetailMapper;
import com.ruoyi.project.erp.productOutStock.domain.ProductOutStock;
import com.ruoyi.project.erp.productOutStock.mapper.ProductOutStockMapper;
import com.ruoyi.project.erp.productOutStockDetails.domain.ProductOutStockDetails;
import com.ruoyi.project.erp.productOutStockDetails.mapper.ProductOutStockDetailsMapper;
import com.ruoyi.project.system.user.domain.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productLedger.mapper.ProductLedgerMapper;
import com.ruoyi.project.erp.productLedger.domain.ProductLedger;
import com.ruoyi.project.erp.productLedger.service.IProductLedgerService;
import com.ruoyi.common.support.Convert;

import javax.servlet.http.HttpServletRequest;

/**
 * 产品对账 服务层实现
 * 
 * @author zqm
 * @date 2019-05-13
 */
@Service
public class ProductLedgerServiceImpl implements IProductLedgerService 
{
	@Autowired
	private ProductLedgerMapper productLedgerMapper;

	@Autowired
	private ProductLedgerDetailMapper productLedgerDetailMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private ProductOutStockDetailsMapper productOutStockDetailsMapper;

	@Autowired
	private ProductIntoStockDetailsMapper productIntoStockDetailsMapper;

	@Autowired
	private DevCompanyMapper companyMapper;

	/**
     * 查询产品对账信息
     * 
     * @param id 产品对账ID
     * @return 产品对账信息
     */
    @Override
	public ProductLedger selectProductLedgerById(Integer id)
	{
		ProductLedger ledger = productLedgerMapper.selectProductLedgerById(id);
		if(ledger != null){
			ledger.setDetails(productLedgerDetailMapper.findDetailByLedgerId(ledger.getId()));
		}
	    return ledger;
	}
	
	/**
     * 查询产品对账列表
     * 
     * @param productLedger 产品对账信息
     * @return 产品对账集合
     */
	@Override
	@DataSource(DataSourceType.ERP)
	public List<ProductLedger> selectProductLedgerList(ProductLedger productLedger, HttpServletRequest request)
	{
		User u = JwtUtil.getTokenUser(request);
		if(u ==null) Collections.emptyList();
		productLedger.setCompanyId(u.getCompanyId());
	    return productLedgerMapper.selectProductLedgerList(productLedger);
	}
	
    /**
     * 新增产品对账
     * 
     * @param productLedger 产品对账信息
     * @return 结果
     */
	@Override
	public int insertProductLedger(ProductLedger productLedger,HttpServletRequest request)
	{
		//查询客户信息
		if(productLedger.getCustomerId() == null)return 0;
		Customer customer =  customerMapper.selectCustomerById(productLedger.getCustomerId());
		if(customer == null)return  0;
		User u = JwtUtil.getTokenUser(request);
		if (u == null)return 0;
		productLedger.setCompanyId(u.getCompanyId());
		productLedger.setCustomerCompanyName(customer.getCompanyName());
		productLedger.setCustomerName(customer.getCustomerName());
		productLedger.setPhone(customer.getContactInformation());
		productLedger.setManEmail(customer.getEmail());
		productLedger.setPaymentMethod(customer.getPaymentTime());
		productLedger.setCreateTime(new Date());
		productLedgerMapper.insertProductLedger(productLedger);
		//查询对应的客户的产品出库记录
		ProductLedgerDetail detail = null;
		List<ProductOutStockDetails> outStocks = productOutStockDetailsMapper.selectOutStockByCIdAndCompanyId(productLedger.getCustomerId(),u.getCompanyId(),
				productLedger.getBeginTime(),productLedger.getEndTime());
		if(outStocks != null && outStocks.size() >0) {
			for (ProductOutStockDetails outStockdetail : outStocks) {
				detail = new ProductLedgerDetail();
				detail.setCompanyId(u.getCompanyId());
				detail.setCustomerId(productLedger.getCustomerId());
				detail.setCustomerCode(outStockdetail.getCustomerCode());
				detail.setLedgerId(productLedger.getId());
				detail.setCreateTime(new Date());
				detail.setOrderCode(outStockdetail.getOrderCode());
				detail.setLedgerType(2);
				detail.setProductModel(outStockdetail.getProductModel());
				detail.setProductName(outStockdetail.getProductName());
				detail.setProductCode(outStockdetail.getProductCode());
				detail.setLedgerNumber(outStockdetail.getOutNumber());
				detail.setLedgerPrice(outStockdetail.getPrice().floatValue());
				detail.setLedgerMoney(outStockdetail.getTotalPrice().floatValue());
				detail.setDeliveryTime(outStockdetail.getCreateTime());
				productLedgerDetailMapper.insertProductLedgerDetail(detail);
			}
		}
		//查询对应的客户的产品的入库记录信息
		List<ProductIntoStockDetails> intoStock = productIntoStockDetailsMapper.selectProductIntoStockDetailsByCompanyIdAndCid(u.getCompanyId(),productLedger.getCustomerId(),
				productLedger.getBeginTime(),productLedger.getEndTime());
		if(intoStock != null && intoStock.size() >0){
			for (ProductIntoStockDetails into : intoStock) {
				detail = new ProductLedgerDetail();
				detail.setCompanyId(u.getCompanyId());
				detail.setCustomerId(productLedger.getCustomerId());
				detail.setCustomerCode(into.getCustomerCode());
				detail.setLedgerId(productLedger.getId());
				detail.setCreateTime(new Date());
				detail.setOrderCode(into.getProductCode());
				detail.setLedgerType(1);
				detail.setProductModel(into.getProductModel());
				detail.setProductCode(into.getProductCode());
				detail.setProductName(into.getProductName());
				detail.setLedgerNumber(0 - into.getIntoNumber());
				detail.setLedgerPrice(0 -into.getPrice().floatValue());
				detail.setLedgerMoney(0 -into.getTotalPrice().floatValue());
				detail.setDeliveryTime(into.getCreateTime());
				productLedgerDetailMapper.insertProductLedgerDetail(detail);
			}
		}
	    return 1;
	}
	
	/**
     * 修改产品对账
     * 
     * @param productLedger 产品对账信息
     * @return 结果
     */
	@Override
	public int updateProductLedger(ProductLedger productLedger)
	{
	    return productLedgerMapper.updateProductLedger(productLedger);
	}

	/**
	 * 状态修改
	 * @param productLedger
	 * @return
	 */
	@Override
	public int cancelLedger(ProductLedger productLedger,HttpServletRequest request) {
		User u = JwtUtil.getTokenUser(request);
		if(u == null)return 0;
		if(productLedger.getLedgerStatus() == 3){
			//作废
			productLedger.setVoidPeople(u.getUserName());
			productLedger.setVoidTime(new Date());
		}else if(productLedger.getLedgerStatus() == 2){
			//对账
			productLedger.setLedgerPeople(u.getUserName());
			productLedger.setLedgerTime(new Date());
		}
		return productLedgerMapper.updateProductLedger(productLedger);
	}

	/**
     * 删除产品对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductLedgerByIds(String ids)
	{
		return productLedgerMapper.deleteProductLedgerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 导出产品对账单
	 * @param id 产品对账单id
	 * @return
	 */
	@Override
	public Workbook uploadProLed(Integer id) {
		// 查询对账单
		ProductLedger productLedger = productLedgerMapper.selectProductLedgerById(id);
		List<ProductLedgerDetail> productLedgerDetailList = productLedgerDetailMapper.findDetailByLedgerId(productLedger.getId());
		productLedger.setDetails(productLedgerDetailList);
		// 查询公司信息
		DevCompany company = companyMapper.selectDevCompanyById(productLedger.getCompanyId());

		Workbook wb = ExcelUtils.createWorkbook();
		Sheet sheet = ExcelUtils.createSheet(wb);

		// 设置列宽
		sheet.setColumnWidth(0, 252 * 4 + 323);
		sheet.setColumnWidth(1, 252 * 25 + 323);
		sheet.setColumnWidth(2, 252 * 25 + 323);
		sheet.setColumnWidth(3, 252 * 14 + 323);
		sheet.setColumnWidth(4, 252 * 14 + 323);
		sheet.setColumnWidth(5, 252 * 14 + 323);
		sheet.setColumnWidth(6, 252 * 14 + 323);
		sheet.setColumnWidth(7, 252 * 14 + 323);
		sheet.setColumnWidth(8, 252 * 14 + 323);


		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));  // 表头公司名称
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 8));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 8));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 8));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 6, 8));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 6, 8));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 8));  // 第6行对账时间范围
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 8));  // 第7行对账单

		// 表头，公司名称
		Row row = sheet.createRow(0);
		row.setHeight((short) (14 * 36)); // 设置行高
		Cell cell = row.createCell(0);
		cell.setCellValue(company.getComName());
		// 单元格样式对象
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
		// 创建字体样式
		Font font = wb.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 18);    // 将字体大小设置为18px
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);

		// 第一行
		Row row1 = sheet.createRow(1);
		Cell cell1_1 = row1.createCell(0);
		cell1_1.setCellValue("客户公司：");
		Cell cell_2 = row1.createCell(2);
		cell_2.setCellValue(productLedger.getCustomerCompanyName());
		Cell cell1_3 = row1.createCell(5);
		cell1_3.setCellValue("供方：");
		Cell cell1_4 = row1.createCell(6);
		cell1_4.setCellValue(company.getComName());

		// 第二行
		Row row2 = sheet.createRow(2);
		Cell cell2_1 = row2.createCell(0);
		cell2_1.setCellValue("电话：");
		Cell cell2_2 = row2.createCell(2);
		cell2_2.setCellValue(productLedger.getPhone());
		Cell cell2_3 = row2.createCell(5);
		cell2_3.setCellValue("电话：");
		Cell cell2_4 = row2.createCell(6);
		cell2_4.setCellValue("");

		// 第三行
		Row row3 = sheet.createRow(3);
		Cell cell3_1 = row3.createCell(0);
		cell3_1.setCellValue("邮箱：");
		Cell cell3_2 = row3.createCell(2);
		cell3_2.setCellValue(productLedger.getManEmail());
		Cell cell3_3 = row3.createCell(5);
		cell3_3.setCellValue("邮箱：");
		Cell cell3_4 = row3.createCell(6);
		cell3_4.setCellValue("");

		// 第四行
		Row row4 = sheet.createRow(4);
		Cell cell4_1 = row4.createCell(0);
		cell4_1.setCellValue("联系人：");
		Cell cell4_2 = row4.createCell(2);
		cell4_2.setCellValue(productLedger.getCustomerName());
		Cell cell4_3 = row4.createCell(5);
		cell4_3.setCellValue("地址：");
		Cell cell4_4 = row4.createCell(6);
		cell4_4.setCellValue(company.getComAddress());

		// 第五行
		Row row5 = sheet.createRow(5);
		Cell cell5_1 = row5.createCell(0);
		cell5_1.setCellValue("对账日期：");
		Cell cell5_2 = row5.createCell(2);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date ledgerTime = productLedger.getLedgerTime();
		if (ledgerTime != null ) {
			cell5_2.setCellValue(dateFormat.format(ledgerTime));
		} else {
			cell5_2.setCellValue("");
		}

		Cell cell5_3 = row5.createCell(5);
		cell5_3.setCellValue("结款期限：");
		Cell cell5_4 = row5.createCell(6);
		cell5_4.setCellValue(productLedger.getPaymentMethod());

		// 第6行对账时间范围
		Row row6 = sheet.createRow(6);
		Cell cell6 = row6.createCell(0);
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
		cell6.setCellValue(dateFormat1.format(productLedger.getBeginTime()) + "-" + dateFormat1.format(productLedger.getEndTime()));
		CellStyle cellStyle6 = wb.createCellStyle();
		cellStyle6.setAlignment(HorizontalAlignment.RIGHT);  // 设置单元格水平方向对其方式
		cellStyle6.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
		cell6.setCellStyle(cellStyle6);

		// 第7行对账单
		Row row7 = sheet.createRow(7);
		Cell cell7 = row7.createCell(0);
		cell7.setCellValue("产品对账单");
		CellStyle cellStyle7 = wb.createCellStyle();
		Font font7 = wb.createFont();
		font7.setFontHeightInPoints((short) 14);    // 将字体大小设置为18px
		font7.setBold(true); // 加粗
		cellStyle7.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
		cellStyle7.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
		cellStyle7.setFont(font7);
		cell7.setCellStyle(cellStyle7);

		// 对账单表头
		Row row8 = sheet.createRow(8);
		CellStyle cellStyle8 = wb.createCellStyle();
		cellStyle8.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
		cellStyle8.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
		Cell cell8_0 = row8.createCell(0);
		cell8_0.setCellValue("序号");
		cell8_0.setCellStyle(cellStyle8);
		Cell cell8_1 = row8.createCell(1);
		cell8_1.setCellValue("送货日期");
		cell8_1.setCellStyle(cellStyle8);
		Cell cell8_2 = row8.createCell(2);
		cell8_2.setCellValue("订单号");
		cell8_2.setCellStyle(cellStyle8);
		Cell cell8_3 = row8.createCell(3);
		cell8_3.setCellValue("客户产品编码");
		cell8_3.setCellStyle(cellStyle8);
		Cell cell8_4 = row8.createCell(4);
		cell8_4.setCellValue("产品编码");
		cell8_4.setCellStyle(cellStyle8);
		Cell cell8_5 = row8.createCell(5);
		cell8_5.setCellValue("产品型号");
		cell8_5.setCellStyle(cellStyle8);
		Cell cell8_6 = row8.createCell(6);
		cell8_6.setCellValue("送货数量[PCS]");
		cell8_6.setCellStyle(cellStyle8);
		Cell cell8_7 = row8.createCell(7);
		cell8_7.setCellValue("含税单价[RMB]");
		cell8_7.setCellStyle(cellStyle8);
		Cell cell8_8 = row8.createCell(8);
		cell8_8.setCellValue("含税金额");
		cell8_8.setCellStyle(cellStyle8);

		int i = 9;
		int j = 1;
		Row rowDetail = null;
		CellStyle cellStyleDetail = wb.createCellStyle();
		cellStyleDetail.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
		cellStyleDetail.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
		// 对账明细
		for (ProductLedgerDetail productLedgerDetail : productLedgerDetailList) {
			rowDetail = sheet.createRow(i);
			Cell cell9_0 = rowDetail.createCell(0);
			cell9_0.setCellValue(j);
			cell9_0.setCellStyle(cellStyleDetail);
			Cell cell9_1 = rowDetail.createCell(1);
			cell9_1.setCellValue(dateFormat.format(productLedgerDetail.getDeliveryTime()));
			cell9_1.setCellStyle(cellStyleDetail);
			Cell cell9_2 = rowDetail.createCell(2);
			cell9_2.setCellValue(productLedgerDetail.getOrderCode());
			cell9_2.setCellStyle(cellStyleDetail);
			Cell cell9_3 = rowDetail.createCell(3);
			cell9_3.setCellValue(productLedgerDetail.getCustomerCode());
			cell9_3.setCellStyle(cellStyleDetail);
			Cell cell9_4 = rowDetail.createCell(4);
			cell9_4.setCellValue(productLedgerDetail.getProductCode());
			cell9_4.setCellStyle(cellStyleDetail);
			Cell cell9_5 = rowDetail.createCell(5);
			cell9_5.setCellValue(productLedgerDetail.getProductModel());
			cell9_5.setCellStyle(cellStyleDetail);
			Cell cell9_6 = rowDetail.createCell(6);
			cell9_6.setCellValue(productLedgerDetail.getLedgerNumber());
			cell9_6.setCellStyle(cellStyleDetail);
			Cell cell9_7 = rowDetail.createCell(7);
			cell9_7.setCellValue(productLedgerDetail.getLedgerPrice());
			cell9_7.setCellStyle(cellStyleDetail);
			Cell cell9_8 = rowDetail.createCell(8);
			cell9_8.setCellValue(productLedgerDetail.getLedgerMoney());
			cell9_8.setCellStyle(cellStyleDetail);
			i++;
			j++;
		}

		Row row9 = sheet.createRow(i);
		Cell cell9_5 = row9.createCell(5);
		cell9_5.setCellValue("合计");
		cell9_5.setCellStyle(cellStyleDetail);
		Cell cell9_6 = row9.createCell(6);
		cell9_6.setCellValue(productLedger.getTotalNum());
		cell9_6.setCellStyle(cellStyleDetail);
		Cell cell9_8 = row9.createCell(8);
		cell9_8.setCellValue(productLedger.getTotalMoney());
		cell9_8.setCellStyle(cellStyleDetail);

		return wb;
	}
}

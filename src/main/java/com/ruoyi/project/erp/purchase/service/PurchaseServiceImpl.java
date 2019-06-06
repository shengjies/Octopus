package com.ruoyi.project.erp.purchase.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.device.devCompany.domain.DevCompany;
import com.ruoyi.project.device.devCompany.mapper.DevCompanyMapper;
import com.ruoyi.project.erp.contract.domain.Contract;
import com.ruoyi.project.erp.contract.service.IContractService;
import com.ruoyi.project.erp.contractContent.domain.ContractContent;
import com.ruoyi.project.erp.purchaseDetails.domain.PurchaseDetails;
import com.ruoyi.project.erp.purchaseDetails.mapper.PurchaseDetailsMapper;
import com.ruoyi.project.erp.supplier.domain.Supplier;
import com.ruoyi.project.erp.supplier.mapper.SupplierMapper;
import com.ruoyi.project.system.user.domain.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.purchase.mapper.PurchaseMapper;
import com.ruoyi.project.erp.purchase.domain.Purchase;
import com.ruoyi.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购单 服务层实现
 *
 * @author zqm
 * @date 2019-05-10
 */
@Service
public class PurchaseServiceImpl implements IPurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private PurchaseDetailsMapper detailsMapper;

    @Autowired
    private DevCompanyMapper companyMapper;

    @Autowired
    private IContractService contractService;

    /**
     * 查询采购单信息
     *
     * @param id 采购单ID
     * @return 采购单信息
     */
    @Override
    public Purchase selectPurchaseById(Integer id) {
        Purchase purchase = purchaseMapper.selectPurchaseById(id);
        if (purchase != null) {
            PurchaseDetails details = new PurchaseDetails();
            details.setPurchaseId(purchase.getId());
            purchase.setDetails(detailsMapper.selectPurchaseDetailsList(details));
        }
        return purchase;
    }

    /**
     * 查询采购单列表
     *
     * @param purchase 采购单信息
     * @return 采购单集合
     */
    @Override
    public List<Purchase> selectPurchaseList(Purchase purchase) {
        User user = ShiroUtils.getSysUser();
        if (user == null) return Collections.emptyList();
        purchase.setCompanyId(user.getCompanyId());
        return purchaseMapper.selectPurchaseList(purchase);
    }

    /**
     * 新增采购单
     *
     * @param purchase 采购单信息
     * @return 结果
     */
    @Override
    public int insertPurchase(Purchase purchase) {
        if (purchase.getSupplierId() == null) return 0;
        User user = ShiroUtils.getSysUser();
        if (user == null) return 0;
        //根据供应商id查询对应的供应商信息
        Supplier supplier = supplierMapper.selectSupplierById(purchase.getSupplierId());
        if (supplier == null) return 0;
        String purchaseCode = CodeUtils.getPurchaseCode(); // 生成采购单号
        purchase.setPurchaseCode(purchaseCode);
        purchase.setCompanyId(user.getCompanyId());
        purchase.setCreate_by(user.getUserId().intValue());
        purchase.setSupplierName(supplier.getCompanyName());
        purchase.setSupplierAddress(supplier.getCompanyAddress());
        purchase.setLiaisonMan(supplier.getSupplierName());
        purchase.setPhone(supplier.getContactInformation());
        purchase.setDeliverAddress(supplier.getSendAddress());
        purchase.setManEmail(supplier.getEmail());
        purchase.setPaymentMethod(supplier.getPaymentTime());
        purchase.setCreateTime(new Date());
        //添加主表信息
        purchaseMapper.insertPurchase(purchase);
        int totalNum = 0;
        float totalPrice = 0F;
        if (purchase.getDetails() != null && purchase.getDetails().size() > 0) {
            for (PurchaseDetails detail : purchase.getDetails()) {
                detail.setCompanyId(user.getCompanyId());
                detail.setPurchaseId(purchase.getId());
                detail.setPurchaseCode(purchaseCode);
                detail.setCreateTime(new Date());
                totalNum += detail.getNumber();
                totalPrice += detail.getTotalPrict();
                detailsMapper.insertPurchaseDetails(detail);
            }
        }
        purchase.setTotalNumber(totalNum);
        purchase.setTotalPrice(totalPrice);
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 修改采购单
     *
     * @param purchase 采购单信息
     * @return 结果
     */
    @Override
    public int updatePurchase(Purchase purchase) {

        //将对应的详情数据标记为1
        detailsMapper.editPurchaseSign(purchase.getId(), 1);
        //修改详情
        int totalNum = 0;
        float totalPrice = 0F;
        if (purchase.getDetails() != null) {
            for (PurchaseDetails detail : purchase.getDetails()) {
                //查询对应的详情是否存在
                PurchaseDetails de = detailsMapper.selectDetailByPidAndMCode(purchase.getId(), detail.getMaterielCode());
                if (de != null) {
                    de.setMaterielModel(detail.getMaterielModel());//物料型号
                    de.setSupplierCode(detail.getSupplierCode());//供应商编码
                    de.setMaterielName(detail.getMaterielName());//物料名称
                    de.setMoq(detail.getMoq());//moq
                    de.setPrice(detail.getPrice());//单价
                    de.setNumber(detail.getNumber());//数量
                    de.setTotalPrict(detail.getTotalPrict());//合计总价
                    de.setSign(0);
                    detailsMapper.updatePurchaseDetails(de);
                    totalNum += de.getNumber();
                    totalPrice += de.getTotalPrict();
                } else {
                    detail.setSign(0);
                    detail.setDeliverNum(0);
                    detail.setPurchaseId(purchase.getId());
                    detail.setCreateTime(new Date());
                    detailsMapper.insertPurchaseDetails(detail);
                    totalNum += detail.getNumber();
                    totalPrice += detail.getTotalPrict();
                }
            }
        }
        //删除所以标记为1的详情
        detailsMapper.deletePurchaseDetailsBySign(purchase.getId());
        Supplier supplier = supplierMapper.selectSupplierById(purchase.getSupplierId());
        purchase.setTotalNumber(totalNum);
        purchase.setTotalPrice(totalPrice);
        if (supplier != null) {
            purchase.setDeliverAddress(StringUtils.isEmpty(purchase.getDeliverAddress()) ? supplier.getSendAddress() : purchase.getDeliverAddress());
            purchase.setPaymentMethod(StringUtils.isEmpty(purchase.getPaymentMethod()) ? supplier.getPaymentTime() : purchase.getPaymentMethod());
        }
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 删除采购单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseByIds(String ids) {
        return purchaseMapper.deletePurchaseByIds(Convert.toStrArray(ids));
    }

    /**
     * 操作采购单对象
     *
     * @param purchase 采购单对象
     * @return
     */
    @Override
    public int editStatus(Purchase purchase) {
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 查询存在预收库存的采购单信息
     *
     * @param supplierId 供应商id
     * @return 结果
     */
    @Override
    public List<Purchase> selectPurchaseHavePreNumberBySupId(Integer supplierId) {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return Collections.emptyList();
        }

        List<Purchase> purchaseList = purchaseMapper.selectPurchaseHavePreNumberBySupId(user.getCompanyId(), supplierId);
        return purchaseList;
    }


    /**
     * 关闭采购单
     *
     * @param purchase 采购单
     * @return 结果
     */
    @Override
    public int closePurchase(Purchase purchase) {
        Purchase purchase1 = purchaseMapper.selectPurchaseById(purchase.getId());
        if (StockConstants.ORDER_STATUS_THREE.equals(purchase1.getPurchaseStatut())) { // 采购单已经关闭
            throw new BusinessException("采购单已关闭，请勿重复操作");
        }
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 导出采购单明细
     *
     * @param id 采购单主键
     * @return
     */
    @Override
    public Workbook uploadPurchase(Integer id) {
        // 查询采购单
        Purchase purchase = purchaseMapper.selectPurchaseById(id);
        // 查询采购单明细
        PurchaseDetails purchaseDetails = new PurchaseDetails();
        purchaseDetails.setPurchaseId(id);
        List<PurchaseDetails> purchaseDetailList = detailsMapper.selectPurchaseDetailsList(purchaseDetails);
        purchase.setDetails(purchaseDetailList);
        // 查询合同
        Contract contract = contractService.selectContractByCompanyId();
        // 查询公司
        DevCompany company = companyMapper.selectDevCompanyById(purchase.getCompanyId());
        // 创建工作簿对象
        Workbook wb = ExcelUtils.createWorkbook();
        Sheet sheet = ExcelUtils.createSheet(wb);

        // 设置列宽
        sheet.setColumnWidth(0, 252 * 14 + 323);
        sheet.setColumnWidth(1, 252 * 14 + 323);
        sheet.setColumnWidth(2, 252 * 14 + 323);
        sheet.setColumnWidth(3, 252 * 14 + 323);
        sheet.setColumnWidth(4, 252 * 14 + 323);
        sheet.setColumnWidth(5, 252 * 14 + 323);
        sheet.setColumnWidth(6, 252 * 14 + 323);

        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));  // 表头公司名称
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 6));
        int rowNum = 3;
        while (rowNum <= 7) {
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 5, 6));
            rowNum++;
        }
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 6));

        // 表头，公司名称
        Row row = sheet.createRow(0);
        row.setHeight((short) (14 * 36)); // 设置行高
        Cell cell = row.createCell(0);
        cell.setCellValue(company.getComName());
        // 创建表头单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
        // 创建表头字体样式
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 18);    // 将字体大小设置为18px
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);

        // 第一行显示采购单
        Row row1 = sheet.createRow(1);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("采购单");
        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
        Font font1 = wb.createFont();
        font1.setBold(true);
        font.setFontHeightInPoints((short) 14);    // 将字体大小设置为18px
        cellStyle1.setFont(font1);
        cell1.setCellStyle(cellStyle1);

        // 第二行
        Row row2 = sheet.createRow(2);
        Cell cell2_0 = row2.createCell(0);
        cell2_0.setCellValue("采购单号:");
        Cell cell2_1 = row2.createCell(1);
        cell2_1.setCellValue(purchase.getPurchaseCode());

        // 第三行
        Row row3 = sheet.createRow(3);
        Cell cell3_0 = row3.createCell(0);
        cell3_0.setCellValue("甲方(采购方)：");
        Cell cell3_1 = row3.createCell(1);
        cell3_1.setCellValue(company.getComName());
        Cell cell3_4 = row3.createCell(4);
        cell3_4.setCellValue("乙方(供货方)：");
        Cell cell3_5 = row3.createCell(5);
        cell3_5.setCellValue(purchase.getSupplierName());

        // 第四行
        Row row4 = sheet.createRow(4);
        Cell cell4_0 = row4.createCell(0);
        cell4_0.setCellValue("地址：");
        Cell cell4_1 = row4.createCell(1);
        cell4_1.setCellValue(company.getComAddress());
        Cell cell4_4 = row4.createCell(4);
        cell4_4.setCellValue("地址：");
        Cell cell4_5 = row4.createCell(5);
        cell4_5.setCellValue(purchase.getSupplierAddress());

        // 第五行
        Row row5 = sheet.createRow(5);
        Cell cell5_0 = row5.createCell(0);
        cell5_0.setCellValue("联系人：");
        Cell cell5_1 = row5.createCell(1);
        cell5_1.setCellValue(contract != null ? contract.getLiaisonMan() : "");
        Cell cell5_4 = row5.createCell(4);
        cell5_4.setCellValue("联系人：");
        Cell cell5_5 = row5.createCell(5);
        cell5_5.setCellValue(purchase.getLiaisonMan());

        // 第六行
        Row row6 = sheet.createRow(6);
        Cell cell6_0 = row6.createCell(0);
        cell6_0.setCellValue("联系电话：");
        Cell cell6_1 = row6.createCell(1);
        cell6_1.setCellValue(contract != null ? contract.getPhone() : "");
        Cell cell6_4 = row6.createCell(4);
        cell6_4.setCellValue("联系电话：");
        Cell cell6_5 = row6.createCell(5);
        cell6_5.setCellValue(purchase.getPhone());

        // 第七行
        Row row7 = sheet.createRow(7);
        Cell cell7_0 = row7.createCell(0);
        cell7_0.setCellValue("电子邮箱：");
        Cell cell7_1 = row7.createCell(1);
        cell7_1.setCellValue(contract != null ? contract.getManEmail() : "");
        Cell cell7_4 = row7.createCell(4);
        cell7_4.setCellValue("电子邮箱：");
        Cell cell7_5 = row7.createCell(5);
        cell7_5.setCellValue(purchase.getManEmail());

        // 第八行
        Row row8 = sheet.createRow(8);
        Cell cell8 = row8.createCell(0);
        cell8.setCellValue("甲乙双方经协商，签订本采购订单，采购物料明细如下：");

        // 第九行 采购单明细表头
        CellStyle cellStyle9 = wb.createCellStyle();
        cellStyle9.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cellStyle9.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式
        Font font9 = wb.createFont();
        font9.setBold(true);
        cellStyle9.setFont(font9);
        Row row9 = sheet.createRow(9);
        Cell cell9_0 = row9.createCell(0);
        cell9_0.setCellValue("物料编码");
        cell9_0.setCellStyle(cellStyle9);
        Cell cell9_1 = row9.createCell(1);
        cell9_1.setCellValue("物料型号");
        cell9_1.setCellStyle(cellStyle9);
        Cell cell9_2 = row9.createCell(2);
        cell9_2.setCellValue("供应商物料编码");
        cell9_2.setCellStyle(cellStyle9);
        Cell cell9_3 = row9.createCell(3);
        cell9_3.setCellValue("价格[含税]");
        cell9_3.setCellStyle(cellStyle9);
        Cell cell9_4 = row9.createCell(4);
        cell9_4.setCellValue("数量");
        cell9_4.setCellStyle(cellStyle9);
        Cell cell9_5 = row9.createCell(5);
        cell9_5.setCellValue("合计");
        cell9_5.setCellStyle(cellStyle9);
        Cell cell9_6 = row9.createCell(6);
        cell9_6.setCellValue("交付数量");
        cell9_6.setCellStyle(cellStyle9);

        // 采购单明细列表
        int i = 10;
        Row rowDetail = null;
        CellStyle cellStyleDetail = wb.createCellStyle();
        cellStyleDetail.setAlignment(HorizontalAlignment.CENTER);  // 设置单元格水平方向对其方式
        cellStyleDetail.setVerticalAlignment(VerticalAlignment.CENTER); // 设置单元格垂直方向对其方式

        for (PurchaseDetails details : purchaseDetailList) {
            rowDetail = sheet.createRow(i);
            Cell cell10_0 = rowDetail.createCell(0);
            cell10_0.setCellValue(details.getMaterielCode());
            cell10_0.setCellStyle(cellStyleDetail);
            Cell cell10_1 = rowDetail.createCell(1);
            cell10_1.setCellValue(details.getMaterielModel());
            cell10_1.setCellStyle(cellStyleDetail);
            Cell cell10_2 = rowDetail.createCell(2);
            cell10_2.setCellValue(details.getMaterielName());
            cell10_2.setCellStyle(cellStyleDetail);
            Cell cell10_3 = rowDetail.createCell(3);
            cell10_3.setCellValue(details.getPrice());
            cell10_3.setCellStyle(cellStyleDetail);
            Cell cell10_4 = rowDetail.createCell(4);
            cell10_4.setCellValue(details.getNumber());
            cell10_4.setCellStyle(cellStyleDetail);
            Cell cell10_5 = rowDetail.createCell(5);
            cell10_5.setCellValue(details.getTotalPrict());
            cell10_5.setCellStyle(cellStyleDetail);
            Cell cell10_6 = rowDetail.createCell(6);
            cell10_6.setCellValue(details.getDeliverNum());
            cell10_6.setCellStyle(cellStyleDetail);
            i++;
        }

        // 总金额大小写
        sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 5, 6));
        Row row11 = sheet.createRow(i);
        Cell cell11_0 = row11.createCell(0);
        cell11_0.setCellValue("总金额(大写)");
        Cell cell11_1 = row11.createCell(1);
        cell11_1.setCellValue("");
        Cell cell11_4 = row11.createCell(4);
        cell11_4.setCellValue("总金额(小写)");
        Cell cell11_5 = row11.createCell(5);
        cell11_5.setCellValue("");
        i++;

        // 交货地点及时间
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 6));
        Row row12 = sheet.createRow(i);
        Cell cell12_0 = row12.createCell(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        cell12_0.setCellValue("1、交货日期及地点:" + sdf.format(purchase.getDeliverTime()) + "交货，送至" + purchase.getDeliverAddress());
        i++;
        // 运费方式
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 6));
        Row row13 = sheet.createRow(i);
        Cell cell13_0 = row13.createCell(0);
        cell13_0.setCellValue("2、运输费用由" + purchase.getCost() + "负责。");
        i++;
        // 付款方式
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 6));
        Row row14 = sheet.createRow(i);
        Cell cell14_0 = row14.createCell(0);
        cell14_0.setCellValue("3、税费及付款方式：" + purchase.getPaymentMethod());
        i++;
        // 合同明细
        Row rowDetail2 = null;
        List<ContractContent> contractContents = contract.getContractContents();
        for (ContractContent contractContent : contractContents) {
            rowDetail2 = sheet.createRow(i);
            Cell cellDetail2 = rowDetail2.createCell(0);
            cellDetail2.setCellValue(contractContent.getContent());
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 6));
            i++;
        }

        Row row15 = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 6));
        Cell cell15_0 = row15.createCell(0);
        cell15_0.setCellValue("甲方：" + company.getComName());
        Cell cell15_3 = row15.createCell(3);
        cell15_3.setCellValue("乙方：" + purchase.getSupplierName());
        i++;
        Row row16 = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 6));
        Cell cell16_0 = row16.createCell(0);
        cell16_0.setCellValue("授权人：");
        Cell cell16_3 = row16.createCell(3);
        cell16_3.setCellValue("授权人：");
        i++;
        Row row17 = sheet.createRow(i);
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 6));
        Cell cell17_0 = row17.createCell(0);
        cell17_0.setCellValue("时间：");
        Cell cell17_3 = row17.createCell(3);
        cell17_3.setCellValue("时间：");

        return wb;
    }
}

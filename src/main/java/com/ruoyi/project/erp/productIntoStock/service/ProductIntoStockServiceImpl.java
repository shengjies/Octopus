package com.ruoyi.project.erp.productIntoStock.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.StockConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.erp.orderDetails.domain.OrderDetails;
import com.ruoyi.project.erp.orderDetails.mapper.OrderDetailsMapper;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfo;
import com.ruoyi.project.erp.orderInfo.domain.OrderInfoResult;
import com.ruoyi.project.erp.orderInfo.mapper.OrderInfoMapper;
import com.ruoyi.project.erp.productCustomer.domain.ProductCustomer;
import com.ruoyi.project.erp.productCustomer.mapper.ProductCustomerMapper;
import com.ruoyi.project.erp.productIntoStockDetails.domain.ProductIntoStockDetails;
import com.ruoyi.project.erp.productIntoStockDetails.mapper.ProductIntoStockDetailsMapper;
import com.ruoyi.project.erp.productStock.domain.ProductStock;
import com.ruoyi.project.erp.productStock.mapper.ProductStockMapper;
import com.ruoyi.project.product.list.domain.DevProductList;
import com.ruoyi.project.product.list.mapper.DevProductListMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.productIntoStock.mapper.ProductIntoStockMapper;
import com.ruoyi.project.erp.productIntoStock.domain.ProductIntoStock;
import com.ruoyi.project.erp.productIntoStock.service.IProductIntoStockService;
import com.ruoyi.common.support.Convert;

/**
 * 产品入库 服务层实现
 *
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class ProductIntoStockServiceImpl implements IProductIntoStockService {
    @Autowired
    private ProductIntoStockMapper productIntoStockMapper;

    @Autowired
    private ProductIntoStockDetailsMapper productIntoStockDetailsMapper; // 产品退货清单数据层

    @Autowired
    private ProductStockMapper productStockMapper; // 产品库存数据层

    @Autowired
    private OrderInfoMapper orderInfoMapper; // 订单数据层

    @Autowired
    private OrderDetailsMapper orderDetailsMapper; // 订单详情数据层

    @Autowired
    private DevProductListMapper productMapper; // 产品数据层

    @Autowired
    private ProductCustomerMapper productCustomerMapper; // 客户产品关联数据层


    /**
     * 查询产品入库信息
     *
     * @param id 产品入库ID
     * @return 产品入库信息
     */
    @Override
    public ProductIntoStock selectProductIntoStockById(Integer id) {
        ProductIntoStock productIntoStock = productIntoStockMapper.selectProductIntoStockById(id);
        productIntoStock.setProductIntoStockDetails(productIntoStockDetailsMapper.selectProductIntoStockDetailsByIntoId(id));
        return productIntoStock;
    }

    /**
     * 查询产品入库列表
     *
     * @param productIntoStock 产品入库信息
     * @return 产品入库集合
     */
    @Override
    public List<ProductIntoStock> selectProductIntoStockList(ProductIntoStock productIntoStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return Collections.emptyList();
        }
        productIntoStock.setCompanyId(user.getCompanyId());
        return productIntoStockMapper.selectProductIntoStockList(productIntoStock);
    }

    /**
     * 新增客户退货
     *
     * @param productIntoStock 产品入库信息
     * @return 结果
     */
    @Override
    public int insertProductIntoStock(ProductIntoStock productIntoStock) {
        User user = ShiroUtils.getSysUser();
        if (user == null) {
            return 0;
        }
        /**
         * 产品退货单
         */
        String proIntoStockCode = CodeUtils.getProIntoStockCode(); // 自动生成客户退货单
        productIntoStock.setIntoCode(proIntoStockCode);
        productIntoStock.setCompanyId(user.getCompanyId());
        productIntoStock.setCreateId(user.getUserId().intValue());
        productIntoStock.setCreateName(user.getUserName());
        productIntoStock.setCreateTime(new Date());
        productIntoStockMapper.insertProductIntoStock(productIntoStock); // 新增产品退货单
        /**
         * 产品退货清单操作
         */
        BigDecimal bigOutNumber = null;
        List<ProductIntoStockDetails> productIntoStockDetailList = productIntoStock.getProductIntoStockDetails();
        if (!StringUtils.isEmpty(productIntoStockDetailList)) {
            for (ProductIntoStockDetails productIntoStockDetail : productIntoStockDetailList) {
                // 查询客户关联产品价格信息
                List<ProductCustomer> productCustomers = productCustomerMapper.selectProductCustomerByProIdOrCusId(productIntoStockDetail.getProductId(), productIntoStock.getCustomerId());
                if (!StringUtils.isEmpty(productCustomers)) { // 客户产品关联
                    ProductCustomer productCustomer = productCustomers.get(0);
                    bigOutNumber = new BigDecimal(productIntoStockDetail.getIntoNumber());
                    productIntoStockDetail.setPrice(productCustomer.getCustomerPrice());
                    productIntoStockDetail.setTotalPrice(productCustomer.getCustomerPrice().multiply(bigOutNumber));
                }
                productIntoStockDetail.setIntoId(productIntoStock.getId());
                productIntoStockDetail.setIntoCode(proIntoStockCode); // 退货单号
                productIntoStockDetail.setCreateTime(new Date());
                productIntoStockDetailsMapper.insertProductIntoStockDetails(productIntoStockDetail);

                /**
                 * 订单操作 <br>
                 * 客户退货修改详情交互数量 <br>
                 * 修改订单总数量 <br>
                 */
                Integer intoNumber = productIntoStockDetail.getIntoNumber(); // 退货数量
                Integer productId = productIntoStockDetail.getProductId(); // 产品id
                Integer customerId = productIntoStock.getCustomerId(); // 供应商id

                while (intoNumber > 0) {
                    // 先查询未交付完成的订单明细
                    OrderDetails orderDetails = orderDetailsMapper.selectOrderDetailsListByProIdAndCusIdOne(user.getCompanyId(), customerId, productId, StockConstants.ORDER_STATUS_TWO);
                    if (!StringUtils.isNull(orderDetails)) {
                        intoNumber = getInteger(intoNumber, orderDetails);
                    } else { // 查询已交付完成的订单明细指已经关闭的订单
                        orderDetails = orderDetailsMapper.selectOrderDetailsListByProIdAndCusIdOne(user.getCompanyId(), customerId, productId, StockConstants.ORDER_STATUS_THREE);
                        if (!StringUtils.isNull(orderDetails)) { // 查询到已经完成的订单
                            intoNumber = getInteger(intoNumber, orderDetails);
                        } else {
                            throw new BusinessException("该客户产品编码为" + productIntoStockDetail.getProductCode() + "超出订单数量");
                        }
                    }
                }

                /**
                 * 产品库存操作
                 */
                // 查看库存记录
                ProductStock productStock = productStockMapper.selectProductStockByProCode(user.getCompanyId(), productIntoStockDetail.getProductCode());
                if (StringUtils.isNull(productStock)) { // 不存在记录新增
                    productStock = new ProductStock();
                    productStock.setCompanyId(user.getCompanyId());
                    productStock.setCreateTime(new Date());
                    productStock.setLastUpdate(new Date());
                    productStock.setBadNumber(productIntoStockDetail.getIntoNumber()); // 不良品数量
                    productStock.setProductCode(productIntoStockDetail.getProductCode()); // 编码
                    productStock.setTotalNumber(productIntoStockDetail.getIntoNumber()); // 总数
                    productStock.setProductName(productIntoStockDetail.getProductName()); // 名称
                    productStock.setProductModel(productIntoStockDetail.getProductModel()); // 型号
                    // 查询产品
                    DevProductList product = productMapper.selectDevProductByCode(user.getCompanyId(), productIntoStockDetail.getProductCode());
                    productStock.setProductId(product == null ? null : product.getId());

                    productStockMapper.insertProductStock(productStock); // 新增产品库存
                } else {

                    productStock.setTotalNumber(productStock.getTotalNumber() + productIntoStockDetail.getIntoNumber()); // 更新总数
                    productStock.setBadNumber(productStock.getBadNumber() + productIntoStockDetail.getIntoNumber()); // 更新不良品
                    productStock.setLastUpdate(new Date());

                    productStockMapper.updateProductStock(productStock); // 更新产品库存
                }
            }
        }

        return productIntoStockMapper.updateProductIntoStock(productIntoStock);
    }

    /**
     * 返回更新过后的退货数量
     * @param intoNumber 退货数量
     * @param orderDetails 订单明细
     * @return 结果
     */
    private Integer getInteger(Integer intoNumber, OrderDetails orderDetails) {
        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderDetails.getOrderId());
        Integer deliverNum = orderDetails.getDeliverNum();
        if (deliverNum >= intoNumber) {
            orderInfo.setOrderDeliverNum(orderInfo.getOrderDeliverNum() - intoNumber);
            orderInfo.setOrderStatus(StockConstants.ORDER_STATUS_TWO);
            orderDetails.setDeliverNum(deliverNum - intoNumber);
            intoNumber = 0;
        } else {
            orderInfo.setOrderDeliverNum(orderInfo.getOrderDeliverNum() - deliverNum);
            orderInfo.setOrderStatus(StockConstants.ORDER_STATUS_TWO);
            orderDetails.setDeliverNum(0);
            intoNumber = intoNumber - deliverNum;
        }
        orderDetailsMapper.updateOrderDetails(orderDetails); // 更新明细数据
        orderInfoMapper.updateOrderInfo(orderInfo); // 更细订单
        return intoNumber;
    }


    /**
     * 修改产品入库
     *
     * @param productIntoStock 产品入库信息
     * @return 结果
     */
    @Override
    public int updateProductIntoStock(ProductIntoStock productIntoStock) {
        return productIntoStockMapper.updateProductIntoStock(productIntoStock);
    }

    /**
     * 删除产品入库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProductIntoStockByIds(String ids) {
        return productIntoStockMapper.deleteProductIntoStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 作废产品入库对象
     *
     * @param intoId 产品退货单主键id
     * @return 结果
     */
    @Override
    public int nullifyProductIntoStockByIds(Integer intoId) {
        User user = ShiroUtils.getSysUser();
        if (user == null) return 0;
        /**
         * 订单和订单详情数据回滚
         */
        ProductIntoStock productIntoStock = productIntoStockMapper.selectProductIntoStockById(intoId);

        List<ProductIntoStockDetails> productIntoStockDetails = productIntoStockDetailsMapper.selectProductIntoStockDetailsByIntoId(intoId);
        for (ProductIntoStockDetails productIntoStockDetail : productIntoStockDetails) {
            Integer backNumber = productIntoStockDetail.getIntoNumber(); // 作废时回滚数量
            Integer productId = productIntoStockDetail.getProductId(); // 产品id
            Integer customerId = productIntoStock.getCustomerId(); // 客户id
            // 库存数量回滚
            ProductStock productStock = productStockMapper.selectProductStockByProId(productId);
            productStock.setTotalNumber(productStock.getTotalNumber() - backNumber);
            productStock.setBadNumber(productStock.getBadNumber() - backNumber);
            productStock.setLastUpdate(new Date());
            productStockMapper.updateProductStock(productStock);

            while (backNumber > 0){
                // 查询交付未完成的订单信息
                OrderDetails orderDetails = orderDetailsMapper.selectOrderDetailsListByNullProInStock(user.getCompanyId(), customerId, productId, StockConstants.ORDER_STATUS_TWO);
                // 查询对应订单信息
                OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderDetails.getOrderId());
                Integer deliverNum = orderDetails.getDeliverNum(); // 已交付数量
                Integer number = orderDetails.getNumber(); // 总交付数量
                Integer difNumber = number - deliverNum; // 还未交付数量
                if (difNumber >= backNumber) { // 未交付数量大于退货数量
                    orderInfo.setOrderDeliverNum(orderInfo.getOrderDeliverNum() + backNumber);
                    orderDetails.setDeliverNum(orderDetails.getDeliverNum() + backNumber);
                    backNumber = 0;
                } else { // 未交付数量小于退货数量
                    orderDetails.setDeliverNum(orderDetails.getNumber()); // 设置该产品明细为全部交付
                    orderInfo.setOrderDeliverNum(orderInfo.getOrderDeliverNum() + difNumber);
                    backNumber = backNumber - difNumber;
                }
                orderInfoMapper.updateOrderInfo(orderInfo);
                orderDetailsMapper.updateOrderDetails(orderDetails);
            }

            // 作废产品退货明细
            productIntoStockDetailsMapper.deleteProductIntoStockDetailsById(productIntoStockDetail.getId());
        }
        return productIntoStockMapper.deleteProductIntoStockById(intoId);
    }
}

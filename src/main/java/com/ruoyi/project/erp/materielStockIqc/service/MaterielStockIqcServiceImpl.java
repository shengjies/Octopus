package com.ruoyi.project.erp.materielStockIqc.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.erp.materielStockIqc.mapper.MaterielStockIqcMapper;
import com.ruoyi.project.erp.materielStockIqc.domain.MaterielStockIqc;
import com.ruoyi.common.support.Convert;

/**
 * 物料库存IQC 服务层实现
 * 
 * @author zqm
 * @date 2019-04-30
 */
@Service
public class MaterielStockIqcServiceImpl implements IMaterielStockIqcService 
{
	@Autowired
	private MaterielStockIqcMapper materielStockIqcMapper;

	/**
     * 查询物料库存IQC信息
     * 
     * @param id 物料库存IQCID
     * @return 物料库存IQC信息
     */
    @Override
	public MaterielStockIqc selectMaterielStockIqcById(Integer id)
	{
	    return materielStockIqcMapper.selectMaterielStockIqcById(id);
	}
	
	/**
     * 查询物料库存IQC列表
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 物料库存IQC集合
     */
	@Override
	public List<MaterielStockIqc> selectMaterielStockIqcList(MaterielStockIqc materielStockIqc)
	{
	    return materielStockIqcMapper.selectMaterielStockIqcList(materielStockIqc);
	}
	
    /**
     * 新增物料库存IQC
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 结果
     */
	@Override
	public int insertMaterielStockIqc(MaterielStockIqc materielStockIqc)
	{
	    return materielStockIqcMapper.insertMaterielStockIqc(materielStockIqc);
	}
	
	/**
     * 修改物料库存IQC
     * 
     * @param materielStockIqc 物料库存IQC信息
     * @return 结果
     */
	@Override
	public int updateMaterielStockIqc(MaterielStockIqc materielStockIqc)
	{
	    return materielStockIqcMapper.updateMaterielStockIqc(materielStockIqc);
	}

	/**
     * 删除物料库存IQC对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMaterielStockIqcByIds(String ids)
	{
		return materielStockIqcMapper.deleteMaterielStockIqcByIds(Convert.toStrArray(ids));
	}

	/**
	 * 更改物料检验iqc的状态
	 * @param stockIqc iqc状态
	 * @return 结果
	 */
	@Override
	public int updateMaterielIQCStatus(Integer stockIqc) {
		// 判断该公司iqc状态是否存在记录
		User user = ShiroUtils.getSysUser();
		if (user == null ) {
		    return 0;
		}
		MaterielStockIqc materielStockIqc = materielStockIqcMapper.selectMaterielStockIqcByComId(user.getCompanyId());
		if (StringUtils.isNull(materielStockIqc)) { // 不存在记录
		    // 新增记录
			materielStockIqc = new MaterielStockIqc();
			materielStockIqc.setCompanyId(user.getCompanyId());
			materielStockIqc.setCreatDate(new Date());
			materielStockIqc.setStockIqc(stockIqc);
			return materielStockIqcMapper.insertMaterielStockIqc(materielStockIqc);
		} else { // 存在记录
			// 更新记录
			materielStockIqc.setStockIqc(stockIqc);
			return materielStockIqcMapper.updateMaterielStockIqc(materielStockIqc);
		}
	}

	/**
	 * 查询物料检验IQC信息
	 * @param companyId 公司id
	 * @return 结果
	 */
	@Override
	public MaterielStockIqc selectMaterielStockIqcByComId(Integer companyId) {
		return materielStockIqcMapper.selectMaterielStockIqcByComId(companyId);
	}
}


package com.ruoyi.project.erp.materielStockIqc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 物料库存IQC表 tab_materiel_stock_iqc
 * 
 * @author zqm
 * @date 2019-04-30
 */
public class MaterielStockIqc extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 物料库存IQC主键ID */
	private Integer id;
	/** 公司主键ID */
	private Integer companyId;
	/** 是否开启IQC检样(0、不开启检验，1、开启检验) */
	private Integer stockIqc;
	/** 创建时间 */
	private Date creatDate;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setStockIqc(Integer stockIqc) 
	{
		this.stockIqc = stockIqc;
	}

	public Integer getStockIqc() 
	{
		return stockIqc;
	}
	public void setCreatDate(Date creatDate) 
	{
		this.creatDate = creatDate;
	}

	public Date getCreatDate() 
	{
		return creatDate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("stockIqc", getStockIqc())
            .append("creatDate", getCreatDate())
            .toString();
    }
}

package com.ruoyi.project.erp.fileSourceInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 文件素材管理表 tab_file_source_info
 * 
 * @author zqm
 * @date 2019-05-09
 */
public class FileSourceInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键，自增长 */
	private Integer id;
	/** 所属公司 */
	private Integer companyId;
	/** 文件类型 */
	private Integer fileType; //1、图片 2、文件
	/** 保存类型 */
	// 1、订单 2、采购单 3、产品对账单文件 4、物料对账单文件 5、产品 6、物料
	// 7、产品出库 8、客户退货 9、物料入库 10、物料退货 11、生产发料 12、生产入库
	// 13、库存内部调整
	private Integer saveType;
	/** 保存id */
	private Integer saveId; //对应保存类型的id
	/**
	 * 文件保存路径
	 */
	private String savePath;
	/** 文件路径 */
	private String filePath;
	/** 文件名称 */
	private String fileName;
	/** 上传时间 */
	private Date createTime;

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
	public void setFileType(Integer fileType) 
	{
		this.fileType = fileType;
	}

	public Integer getFileType() 
	{
		return fileType;
	}
	public void setSaveType(Integer saveType) 
	{
		this.saveType = saveType;
	}

	public Integer getSaveType() 
	{
		return saveType;
	}
	public void setSaveId(Integer saveId) 
	{
		this.saveId = saveId;
	}

	public Integer getSaveId() 
	{
		return saveId;
	}
	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}

	public String getFilePath() 
	{
		return filePath;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("fileType", getFileType())
            .append("saveType", getSaveType())
            .append("saveId", getSaveId())
            .append("filePath", getFilePath())
            .append("fileName", getFileName())
            .append("createTime", getCreateTime())
            .toString();
    }
}

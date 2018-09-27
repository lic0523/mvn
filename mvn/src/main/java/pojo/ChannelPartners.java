package pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ChannelPartners implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int partId;
	private String partName;
	private String isValidate;
	@JSONField (format="yyyy-MM-dd HH:mm:ss") 
	private Date addDate;
	@JSONField (format="yyyy-MM-dd HH:mm:ss") 
	private Date updateDate;
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}

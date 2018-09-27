package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import pojo.ChannelPartners;
import pojo.PartnersTemplate;

public interface PartnersTemplateDao {
	public List<PartnersTemplate> getPartnersTemplateList(Map<String,Object> map);
	
	public int getPartnersTemplateListCount(Map<String,Object> map);
	
	public void savePartnersTemplate(PartnersTemplate partTem);
	
	public void updatePartnersTemplate(PartnersTemplate partTem); 
	
	public int getPartnersTemplateById(@Param("partid")int partid);
	
	public List<PartnersTemplate> getPartnersTemplateByIdList(@Param("partid")int partid);
	
}

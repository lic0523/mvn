package dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import pojo.ChannelPartners;

@Repository
public interface ChannelPartnersDao {
	
	public List<ChannelPartners> getChannelPartnersList(Map<String,Object> map);
	
	public int getChannelPartnersListCount(Map<String,Object> map);
	
	public void saveChannelPartner(ChannelPartners part);
	
	public void updateChannelPartner(ChannelPartners part);
	
	public void updateChannelPartnerByisValidate(ChannelPartners part);
	
	public void deleteChannelPartner(int partId);
	
	public List<ChannelPartners> findbyChannelPartnerid(Map<String,Object> map);

}

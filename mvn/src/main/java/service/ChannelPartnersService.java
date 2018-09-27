package service;

import java.util.List;
import java.util.Map;

import pojo.ChannelPartners;

public interface ChannelPartnersService {
	
	public List<ChannelPartners> getChannelPartnersList(Map<String,Object> map);
	
	public int getChannelPartnersListCount(Map<String,Object> map);
	
	public void saveChannelPartner(ChannelPartners part);
	
	public void updateChannelPartner(ChannelPartners part);
	
	public void deleteChannelPartner(int partId);
	
	public List<ChannelPartners> findbyChannelPartnerid(Map<String,Object> map); 
	
	public void updateChannelPartnerByisValidate(ChannelPartners part);

}

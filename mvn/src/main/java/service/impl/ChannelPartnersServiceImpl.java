package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.DbcontextHolder;
import pojo.ChannelPartners;
import service.ChannelPartnersService;
import dao.ChannelPartnersDao;

@Service("channelPartnersService")
public class ChannelPartnersServiceImpl implements ChannelPartnersService {
	
	@Autowired
	ChannelPartnersDao ChannelPartnersDao;

	public List<ChannelPartners> getChannelPartnersList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		return this.ChannelPartnersDao.getChannelPartnersList(map);
	}
	
	public int getChannelPartnersListCount(Map<String,Object> map){
		DbcontextHolder.setDbType("dataSourceOne");
		return this.ChannelPartnersDao.getChannelPartnersListCount(map);
	}

	public void saveChannelPartner(ChannelPartners part) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		this.ChannelPartnersDao.saveChannelPartner(part);
	}

	public void updateChannelPartner(ChannelPartners part) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		this.ChannelPartnersDao.updateChannelPartner(part);
	}

	public void deleteChannelPartner(int partId) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		this.ChannelPartnersDao.deleteChannelPartner(partId);
	}

	@Override
	public List<ChannelPartners> findbyChannelPartnerid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		return this.ChannelPartnersDao.findbyChannelPartnerid(map);
	}

	@Override
	public void updateChannelPartnerByisValidate(ChannelPartners part) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		this.ChannelPartnersDao.updateChannelPartnerByisValidate(part);
	}

}

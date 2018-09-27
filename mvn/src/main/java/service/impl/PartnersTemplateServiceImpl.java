package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.DbcontextHolder;
import dao.PartnersTemplateDao;
import pojo.PartnersTemplate;
import service.PartnersTemplateService;


@Service("partnersTemplateService")
public class PartnersTemplateServiceImpl implements PartnersTemplateService {
	
	@Autowired
	private PartnersTemplateDao ptDao;
	
	@Override
	public List<PartnersTemplate> getPartnersTemplateList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		return ptDao.getPartnersTemplateList(map);
	}

	@Override
	public int getPartnersTemplateListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		return ptDao.getPartnersTemplateListCount(map);
	}

	@Override
	public void savePartnersTemplate(PartnersTemplate partTem) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		ptDao.savePartnersTemplate(partTem);
	}

	@Override
	public void updatePartnersTemplate(PartnersTemplate partTem) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		ptDao.updatePartnersTemplate(partTem);
	}

	@Override
	public int getPartnersTemplateById(int partid) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		return ptDao.getPartnersTemplateById(partid);
	}

	@Override
	public List<PartnersTemplate> getPartnersTemplateByIdList(int partid) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceOne");
		return ptDao.getPartnersTemplateByIdList(partid);
	}



}

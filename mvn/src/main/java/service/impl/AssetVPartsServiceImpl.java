package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.DbcontextHolder;
import pojo.AssetVParts;
import service.AssetVPartsService;


@Service("AssetVPartsService")
public class AssetVPartsServiceImpl implements AssetVPartsService{

	@Autowired
	dao.AssetVPartsDao AssetVPartsDao;
	
	@Override
	public List<AssetVParts> getAssetVPartsList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceTwo");
		return AssetVPartsDao.getAssetVPartsList(map);
	}

	@Override
	public int getAssetVPartsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceTwo");
		return AssetVPartsDao.getAssetVPartsCount(map);
	}

}

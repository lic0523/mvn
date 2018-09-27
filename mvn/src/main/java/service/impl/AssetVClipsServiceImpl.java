package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.DbcontextHolder;
import dao.RedisGeneratorDao;
import pojo.AssetVClips;
import service.AssetVClipsService;

@Service("AssetVClipsService")
public class AssetVClipsServiceImpl extends RedisGeneratorDao<String,AssetVClips> implements AssetVClipsService{
	
	@Autowired
	dao.AssetVClipsDao AssetVClipsDao;

	///fffffrr
	@Override
	public List<AssetVClips> getAssetVClipsList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceTwo");
		return AssetVClipsDao.getAssetVClipsList(map);
	}

	@Override
	public int getAssetVClipsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DbcontextHolder.setDbType("dataSourceTwo");
		return AssetVClipsDao.getAssetVClipsCount(map);
	}
	
}

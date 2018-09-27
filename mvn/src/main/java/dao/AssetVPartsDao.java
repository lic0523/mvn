package dao;

import java.util.List;
import java.util.Map;

import pojo.AssetVParts;

public interface AssetVPartsDao {
	public List<AssetVParts> getAssetVPartsList(Map<String,Object> map);
	
	public int getAssetVPartsCount(Map<String,Object> map);
}

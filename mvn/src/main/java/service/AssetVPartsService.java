package service;

import java.util.List;
import java.util.Map;

import pojo.AssetVParts;

public interface AssetVPartsService {
	public List<AssetVParts> getAssetVPartsList(Map<String,Object> map);
	
	public int getAssetVPartsCount(Map<String,Object> map);
}

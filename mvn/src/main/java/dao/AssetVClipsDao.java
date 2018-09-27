package dao;

import java.util.List;
import java.util.Map;

import pojo.AssetVClips;

public interface AssetVClipsDao {
	public List<AssetVClips> getAssetVClipsList(Map<String,Object> map);
	
	public int getAssetVClipsCount(Map<String,Object> map);
}

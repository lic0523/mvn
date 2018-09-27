package controller;



import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import pojo.AssetVClips;
import pojo.AssetVParts;
import pojo.PartnersTemplate;
import service.AssetVClipsService;
import service.PartnersTemplateService;


@Controller
@RequestMapping("/asset")  
public class AssetVClipsController {

	@Autowired
	AssetVClipsService AssetVClipsService;
	
	@Autowired
	service.AssetVPartsService AssetVPartsService;
	
	@Autowired
	PartnersTemplateService partnersTemplateService;
	
	@RequestMapping(value="/getAssetVClipsList.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAssetVClipsList(int page,HttpServletRequest request) throws TemplateException, IOException{
		Map<String,Object> map = new HashMap<String, Object>();
		int beginIndex = page*100;
		map.put("beginIndex", beginIndex);
		map.put("pageSize", 100);
		List<AssetVClips> AvcList = AssetVClipsService.getAssetVClipsList(map);
		
		Map<String,Object> TemplateMap = new HashMap<String, Object>();
		TemplateMap.put("partid", request.getParameter("partid"));
		List<PartnersTemplate> TemplateList = partnersTemplateService.getPartnersTemplateList(TemplateMap);
		
		Map<String ,Object> result = new HashMap<String,Object>();
		result.put("list", AvcList);
		String templateString=TemplateList.get(0).getContent();
		StringWriter result1 = new StringWriter();
		Template t = new Template("list", new StringReader(templateString), new Configuration());
		t.process(result, result1);
        return result1.toString();
}
	
	@RequestMapping(value="/getAssetVPartsList.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAssetVPartsList(int clipId,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("clipId", clipId);
		List<AssetVParts> AvcList = AssetVPartsService.getAssetVPartsList(map);
		int count = AssetVPartsService.getAssetVPartsCount(map);
		Map<String ,Object> result = new HashMap<String,Object>();
		result.put("list", AvcList);
		result.put("count", count);
		String jsonStr = JSON.toJSONString(result) ;
        return jsonStr;
	}
	
	
}

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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import pojo.AssetVClips;
import pojo.ChannelPartners;
import pojo.PartnersTemplate;
import service.AssetVClipsService;
import service.ChannelPartnersService;
import service.PartnersTemplateService;

@Controller
@RequestMapping("/partTem")
public class PartnersTemplateController {

	@Autowired
	PartnersTemplateService partnersTemplateService;
	
	@Autowired
	ChannelPartnersService channelPartnersService;
	
	@Autowired
	AssetVClipsService AssetVClipsService;
	
	@RequestMapping("/partTemplate")
	public String partTemplate(){
		return "PartnersTemplate";
	}
	
	@RequestMapping("/editPartTemplate")
	public String editPartTemplate(int partId,HttpServletRequest request){
		List<PartnersTemplate> ptList = partnersTemplateService.getPartnersTemplateByIdList(partId);
		PartnersTemplate pt = ptList.get(0);
		request.setAttribute("pt", pt);
		return "editPartTemplate";
	}
	
	@RequestMapping("/editPartTemplateInfo")
	public String editPartTemplateInfo(int partid,String content,String subcontent,String datatype,HttpServletRequest request){
		PartnersTemplate partTem = new PartnersTemplate();
		partTem.setDatatype(datatype);
		partTem.setPartid(partid);
		partTem.setContent(content);
		partTem.setSubcontent(subcontent);
		partnersTemplateService.updatePartnersTemplate(partTem);
		return "PartnersTemplate";
	}
	
	@RequestMapping(value="/getPartnersTemplateList.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPartnersTemplateList(HttpServletRequest request){
		
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("limit");
		
		String isValidate = request.getParameter("isValidate");
		
		if(isValidate==null){
			isValidate = "1";
		}
		
		if(pageStr == null || "".equals(pageStr)){
			pageStr = "1";
		}
		
		if(pageSizeStr == null || "".equals(pageSizeStr)){
			pageSizeStr = "10";
		}
		
		int page = Integer.valueOf(pageStr);
		int pageSize = Integer.valueOf(pageSizeStr);
		int beginIndex = (page -1 )*pageSize ;
	
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("pageSize", pageSize);
		params.put("beginIndex", beginIndex);
		params.put("isValidate", isValidate);
		
		int total = partnersTemplateService.getPartnersTemplateListCount(params);
		List<PartnersTemplate> list  = partnersTemplateService.getPartnersTemplateList(params);
		
		Map<String ,Object> result = new HashMap<String,Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", total);
		result.put("data", list);
		String jsonStr = JSON.toJSONString(result) ;
		return jsonStr ;
	}

	
	@RequestMapping("/initialization.do")
	public String initialization(HttpServletRequest request) throws IOException, TemplateException{
		HashMap<String, Object> ptmap = new HashMap<String, Object>();
		
		List<ChannelPartners> ptlist = channelPartnersService.getChannelPartnersList(ptmap);
		int cpcount = channelPartnersService.getChannelPartnersListCount(ptmap);
		
		for(int i =0 ; i<cpcount ; i++){
			int id = ptlist.get(i).getPartId();
			int isvalidate = Integer.parseInt(ptlist.get(i).getIsValidate());
			if(partnersTemplateService.getPartnersTemplateById(id)==0){
				PartnersTemplate partTem = new PartnersTemplate();
				partTem.setPartid(id);
				String templateString="[<#list list as col>"
						+ "{\"id\": \"${col.clipId}\","
						+ "\"name\": \"${(col.clipName!)?json_string}\","
						+ "\"otherName\": \"${(col.otherName!)?json_string}\","
						+ "\"adaptor\": \"${(col.adaptor!)?json_string}\","
						+ "\"director\": \"${(col.director!)?json_string}\","
						+ "\"leader\": \"${(col.leader!)?json_string}\","
						+ "\"kind\": \"${(col.kind!)?json_string}\","
						+ "\"language\": \"${(col.language!)?json_string}\","
						+ "\"producer\": \"${(col.producer!)?json_string}\","
						+ "\"caption\": \"${(col.caption!)?json_string}\","
						+ "\"duration\": \"${(col.duration!)?json_string}\","
						+ "\"story\": \"${(col.story!)?json_string}\","
						+ "\"keyWord\": \"${(col.keyWord!)?json_string}\","
						+ "\"updateTime\": \"${col.updateTime?date}\","
						+ "\"createTime\": \"${col.createTime?date}\","
						+ "\"releaseTime\": \"${col.releaseTime?date}\","
						+ "\"playTime\": \"${(col.playTime!)?json_string}\","
						+ "\"speaker\": \"${(col.speaker!)?json_string}\","
						+ "\"narrator\": \"${(col.narrator!)?json_string}\","
						+ "\"seoKeyword\": \"${(col.seoKeyword!)?json_string}\","
						+ "\"speaker\": \"${(col.seoStory!)?json_string}\"}<#if col_has_next>,</#if></#list>]";
				partTem.setContent(templateString);
				partTem.setIsvalidate(isvalidate);
				partTem.setDatatype("json");
				partnersTemplateService.savePartnersTemplate(partTem);
			}
		}
		return "PartnersTemplate";
	}

}

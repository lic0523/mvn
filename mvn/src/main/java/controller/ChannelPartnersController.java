package controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pojo.ChannelPartners;
import service.ChannelPartnersService;

@Controller
@RequestMapping("/part")  
public class ChannelPartnersController {
	
	@Autowired
	ChannelPartnersService channelPartnersService;
	
	
	@RequestMapping("/channelPartnersInfo.do")
	public String channelPartnersPage(HttpServletRequest request){
		return "partnersInfo" ;
	}
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request){
		return "index" ;
	}
	
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request,int partId,Model model){
		model.addAttribute("partId", partId);
		request.setAttribute("partId", partId);
		return "edit" ;
	}
	
	@RequestMapping("/editchannel.do")
	public String editchannel(HttpServletRequest request,int partId,String partName){
		ChannelPartners CP = new ChannelPartners();
		CP.setPartId(partId);
		CP.setIsValidate("1");
		CP.setPartName(partName);
		channelPartnersService.updateChannelPartner(CP);
		return "partnersInfo" ;
	}
	
	@RequestMapping("/updatechannel.do")
	@ResponseBody
	public String updatechannel(HttpServletRequest request,int partId,String isValidate){
		ChannelPartners CP = new ChannelPartners();
		CP.setPartId(partId);
		CP.setIsValidate(isValidate);
		channelPartnersService.updateChannelPartnerByisValidate(CP);
		return "success" ;
	}
	
	
	@RequestMapping("/saveChannelPartners.do")
	public String saveChannelPartners(HttpServletRequest request,ChannelPartners partner,String partName,String isValidate) throws UnsupportedEncodingException, ParseException{
		request.setCharacterEncoding("utf-8");
		ChannelPartners CP = new ChannelPartners();	
		if(isValidate==null){
			isValidate = "2";
			CP.setIsValidate(isValidate);
		}
		else{
			CP.setIsValidate(isValidate);
		}
		CP.setPartName(partName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newTime = df.parse(df.format(new Date()));
		CP.setAddDate(newTime);
		channelPartnersService.saveChannelPartner(CP);
		return "partnersInfo" ;
	}
	
	
	@RequestMapping("/deleteChannelPartners.do")
	@ResponseBody
	public String deleteChannelPartners(HttpServletRequest request,int partId){
		channelPartnersService.deleteChannelPartner(partId);
		return "partnersInfo";
	}
	
	@RequestMapping(value="/getChannelPartnersList.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getChannelPartnersList(HttpServletRequest request){
		
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("limit");
		String name = request.getParameter("selectpartName");
		String partName = null;
		if(name!=null){
			try {
				partName = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		String isValidate = request.getParameter("isValidate");
		
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
		params.put("partName", partName);
		params.put("isValidate", isValidate);
		
		int total = channelPartnersService.getChannelPartnersListCount(params);
		List<ChannelPartners> list  = channelPartnersService.getChannelPartnersList(params);
		
		Map<String ,Object> result = new HashMap<String,Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", total);
		result.put("data", list);
		String jsonStr = JSON.toJSONString(result) ;
		return jsonStr ;
	}

}


package controller;
 

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import pojo.AssetVClips;
import pojo.User;
import service.AssetVClipsService;
import service.IUserService;
 
@Controller  
@RequestMapping("/user")  
public class UserController {
	
	  @Autowired
	  static
	  AssetVClipsService AssetVClipsService;
	
	  static Map<String,Integer> userLoginCountMap = new HashMap<String, Integer>();
	  
	  @Resource  
	    private IUserService userService;  
	   
	  public static void main(String[] args) throws IOException, TemplateException {
		  Map<String,Object> map = new HashMap<String, Object>();
		  	int page = 1;
			int beginIndex = page*100;
			map.put("beginIndex", beginIndex);
			map.put("pageSize", 100);
			List<AssetVClips> AvcList = AssetVClipsService.getAssetVClipsList(map);
			Map<String ,Object> result = new HashMap<String,Object>();
			result.put("list", AvcList);
			String templateString="${list}";
			StringWriter result1 = new StringWriter();
			Template t = new Template("name1", new StringReader(templateString), new Configuration());
			t.process(map, result1);
			System.out.println(result1);
			System.out.println(t);
	}
	  
	  
	  @RequestMapping(value="/getAllUser.do", produces = "application/json; charset=utf-8")
	  @ResponseBody
	   public String getAllUser(HttpServletRequest request){
		  	String name = request.getParameter("selectUserName");
			String userName = null;
			if(name!=null){
				try {
					userName = new String(name.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			User user = new User();
			user.setUserName(userName);
			int total = userService.findAllcount(user);
			List<User> list = userService.findAll(user);
			Map<String ,Object> result = new HashMap<String,Object>();
			result.put("code", 0);
			result.put("msg", "");
			result.put("count", total);
			result.put("data", list);
			String jsonStr = JSON.toJSONString(result);
			return jsonStr;
		}

	  @RequestMapping("AllUser.do")
	  public String AllUser(){
		return "userinfo"; 
	  }
	  
	  	@RequestMapping("test1.do")
	    public void test1() throws IOException, TemplateException{
	    	Map<String, String> map=new HashMap<String, String>();
			map.put("username", "lisi");
			String templateString="${username}/zhangsan";
			StringWriter result = new StringWriter();
			Template t = new Template("name1", new StringReader(templateString), new Configuration());
			t.process(map, result);
			System.out.println(t);
	 
	}
	  
	  @RequestMapping("exit.do")
	  public String exit(){
		return "newlogin"; 
	  }
	  
	  @RequestMapping("regist.do")
	  public String regist(){
		return "regist"; 
	  }
	  
	  @RequestMapping("test.do")
	  public String test(){
		return "test"; 
	  }
	  
	  @RequestMapping("login.do")
	  public String login(){
		return "login"; 
	  }
	  
	  @RequestMapping("updateUser.do")
	  public String updateUser(int id,HttpServletRequest request){
		  request.setAttribute("id", id);
		return "updateUser"; 
	  }
	  
	  @RequestMapping("updateUserinfo.do")
	  public String updateUserinfo(String username,String password,HttpServletRequest request,int states,int id){
		  int pwd = Integer.parseInt(password);
		  if(userService.update(id, username, pwd, states)){
			  return "userinfo";
		  }else{
			  return "login"; 
		  }
	  }
	  
	  @RequestMapping("checklogin.do")
	  public String checklogin(String username,String password,HttpServletRequest request){
		  DateFormat dateTimeformat  = new SimpleDateFormat("yyyy-MM-dd");
		  String key = dateTimeformat.format(new Date())+"-"+username;
		  if(userLoginCountMap.get(key)!=null&&userLoginCountMap.get(key)>5){
			  request.setAttribute("errormsg", "今日密码错误达到五次");
			  return "error";
		  }
		  if(userService.checkLogin(username, password)!=null){
			  List<User> u = userService.findByUname(username);
			  String img = u.get(0).getImg();
			  int id= u.get(0).getId();
			  HttpSession session = request.getSession();
			  session.setAttribute("img", img);
			  session.setAttribute("username", username);
			  request.setAttribute("username", username);
			  request.setAttribute("id", id);
			  return "index"; 
		  }
		  else{
			  
			  if(userLoginCountMap.get(key)==null){
				  userLoginCountMap.put(key, 1);
			  }else{
				  int count = userLoginCountMap.get(key) + 1;
				  userLoginCountMap.put(key, count);
			  }
			  request.setAttribute("errormsg", "密码错误");
			  return "error";
		  }
	  }
	  
	  @RequestMapping("deleteUserById.do")
	  @ResponseBody
	  public String deleteUserById(int userId){
		if(userService.delete(userId)){
			return "userInfo";
		}else{
			return "error";
		}
	  }
	  
	  @RequestMapping("registUser.do")
	  public String registUser(String username,String password,int states,HttpServletRequest request){
		 int pwd = Integer.parseInt(password);
		if(userService.regist(username, pwd, states) != 0){
			return "login";
		}else{
			return "error";
		}
	  }
}

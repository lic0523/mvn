package service;
 
import java.util.List;

import pgeUtil.PageBean;
import pojo.User;
 
public interface IUserService {
	public User getUserById(int userId);
	
	public User checkLogin(String username,String password);
	
	public int regist(String username,int pwd,int age);
	
	public List<User> findAll(User user);  
	
	public int findAllcount(User user);
	
	public boolean update(int id,String username,int pwd,int age);  
	
    public boolean delete(int id);
    
    public User findById(int id); 
    
    public List<User> findByUname(String username); 
    
    public List<User> findByPage(String  name  ,int startIndex,int pageSize);
    
    public PageBean<User> findAllUserWithPage(int pageNum,int pageSize);
}

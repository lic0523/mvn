package dao;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface IUserDao {
 
	public User selectByPrimaryKey(int userId) ;
	
	public User login(String userName,String password);
	
	public int addUser(String username,int password,int age);
	
	public List<User> findAll(User user);
	
	public int findAllcount(User user);
	
	public boolean update(@Param("id")int id,@Param("userName")String userName,@Param("password")int password,@Param("age")int age);
	
	public  boolean delete(int id);
	
	public User findById(int id);
	
	public List<User> findByUname(@Param("userName")String userName);
	
	public List<User> findByPage(@Param("name")String name,@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);
}

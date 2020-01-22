package com.cofc.service.video;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cofc.pojo.video.UserBean;

public interface UserBeanService {
	public int deleteByPrimaryKey(Integer userId);

	public void insert(UserBean record);
    
    public List<UserBean>  getUserlist(@Param("page")Integer page,@Param("limit")Integer limit);
    public List<UserBean>  getUsers(@Param("page")Integer page,@Param("limit")Integer limit);
    
    public int insertSelective(UserBean record);
    public UserBean getUserByUserPhone(@Param("userPhone")String userPhone);
    public UserBean getUserByUserId(@Param("userId")Integer userId);
    
    public UserBean selectByPrimaryKey(Integer userId);

    public int updateByPrimaryKeySelective(UserBean record);

    public int updateByPrimaryKey(UserBean record);
    
    public UserBean login(@Param("userPhone")String userPhone,@Param("userEmail")String userEmail,@Param("userPwd")String userPwd);
}
package com.dhu.huabanxie.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhu.huabanxie.dao.UserDao;
import com.dhu.huabanxie.domain.User;
import com.dhu.huabanxie.util.MD5Util;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public boolean hasUser(String username) {
		return userDao.hasUser(username);
	}

	public int insertUser(Map<String, Object> userMap) {
		return userDao.insertUser(userMap);
	}

	public int insertUser(User user) {
		user.setPassword(MD5Util.MD5(user.getPassword()));
		return userDao.insertUser(user);
	}

	public List<Map<String, Object>> getUserList() {
		return userDao.getUserList();
	}

	public Map<String, Object> getUserInfo(String username) {
		return userDao.getUserInfo(username);
	}

	public int updateUser(Map<String, Object> userMap) {
		return userDao.updateUser(userMap);
	}

	public int deleteUser(String username) {
		return userDao.deleteUser(username);
	}

	public List<Map<String, Object>> getSubUserList(User user) {
		return userDao.getSubInfo(user);
	}

	public List<Map<String, Object>> getHeadUserList(User user) {
		return userDao.getLeaderInfo(user);
	}

}

package com.dhu.huabanxie.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhu.huabanxie.dao.JobDao;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;

	public List<Map<String, Object>> getJobList() {
		return jobDao.getJobList();
	}
}

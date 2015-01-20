package com.dhu.huabanxie.domain;

import java.io.Serializable;

public class Task implements Serializable {
	private static final long serialVersionUID = 2L;

	private int task_id;
	private String task_name;
	private int resp_dept_id;
	private String co_dept;
	private int resp_user_id;
	private String deadline;
	private int state;
	private String task_desc;
	private String finish_desc;

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public int getResp_dept_id() {
		return resp_dept_id;
	}

	public void setResp_dept_id(int resp_dept_id) {
		this.resp_dept_id = resp_dept_id;
	}

	public String getCo_dept() {
		return co_dept;
	}

	public void setCo_dept(String co_dept) {
		this.co_dept = co_dept;
	}

	public int getResp_user_id() {
		return resp_user_id;
	}

	public void setResp_user_id(int resp_user_id) {
		this.resp_user_id = resp_user_id;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTask_desc() {
		return task_desc;
	}

	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}

	public String getFinish_desc() {
		return finish_desc;
	}

	public void setFinish_desc(String finish_desc) {
		this.finish_desc = finish_desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

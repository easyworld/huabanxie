# huabanxie
Oracle数据库结构与设计大作业1 工作任务督办
0.用户登录

1.总经理助理任务列表：
?初始情况下，以列表方式显示本季度所有工作任务，显示的信息点包括序号、任务名称、责任部门、责任人、要求完成时间、当前状态（未下达、未反馈、已反馈、已办结）；按照责任部门和当前状态排序；
?对于即将超期（离要求完成时间只剩7天）和已经超期的任务分别显示黄色和红色；
?在同一个界面中提供按部门、按状态、按任务名称、按时间的查询功能，可以查询本季度以及本年度所有任务，查询结果直接刷新列表；
?列表要求提供记录选中、滚动条、翻页条等基本功能，并可以对记录进行查看详细、增加、删除、修改、报表输出、Excel导出等操作。
2.任务登记界面：
?点击列表中的“增加”按钮弹出任务登记窗口，对任务名称、责任部门、配合部门、责任人、要求完成时间、任务描述等信息进行登记；
?点击列表中的“查看详细”、“修改”按钮同样弹出本窗口，但前者为只读，并需要显示完成情况（部门反馈信息）；后者为查询对应记录并可以进行修改、保存操作。
3.报表输出：点击“报表输出”按钮，将当前列表中显示的记录输出以下报表，利用浏览器的打印功能进行打印：

4.Excel导出：点击“Excel导出”按钮，将当前列表中显示的记录导出Excel文件，可保存到客户端；
5.分管总监任务列表：可复用总经理助理任务列表，不需要提供查询功能。其他要求如下：
?初始情况下，以列表方式显示本人本季度所有工作任务；
?对于即将超期（离要求完成时间只剩7天）和已经超期的任务分别显示黄色和红色；
?列表要求提供记录选中、滚动条、翻页条等基本功能，并可以对记录进行查看详细操作；
?查看详细时，用户可以修改责任人（下达任务）和完成情况（反馈信息），修改完之后点击“确定”按钮分别发送给总经理助理和部门经理，系统自动修改记录状态。
6.部门经理任务列表：基本同分管总监任务列表，但不需要下达任务功能，提交时只提交给分管总监。


数据库表结构设计
1、用户表
用户id、用户名、密码、职务id（总经理助理、分管总监、部门经理）、部门id、领导id

create sequence user_id_seq increment by 1 start with 1;

create table users(
	id	number(4) primary key,
	username	varchar2(32) not null,
	password	varchar2(32) not null,
	job_id	number(4) not null,
	dept_id	number(4) not null,
	mgr_id number(4) not null,
	constraint users_name_uk unique(username)
);


2、职务表
职务id，职务名称
create sequence job_id_seq increment by 1 start with 1;
create table jobs(
	job_id number(4) primary key,
	job_name varchar2(10) not null
);
3、部门表
部门id，部门名称
create sequence dept_id_seq increment by 1 start with 1;
create table depts(
	dept_id number(4) primary key,
	dept_name varchar2(20) not null
);
4、任务表
序号(任务id)、任务名称、责任部门id、配合部门id、责任人id（当前处理人）、要求完成时间、当前状态（未下达、未反馈、已反馈、已办结）、任务描述、完成情况
create sequence task_id_seq increment by 1 start with 1;
create table tasks(
	task_id number(4) primary key,
	task_name varchar2(20) not null,
	resp_dept_id number(4) not null,
	co_dept varchar2(20) null,
	resp_user_id number(4) not null,
	deadline date not null,
	state number(2) not null,
	task_desc varchar2(256) null,
	finish_desc varchar2(256) null
);

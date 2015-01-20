<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务</title> <#include "header.ftl" >
<script type="text/javascript">
	$(document).ready(function() {
		var params2 = JSON.parse('{}');
		var queryUser = $("#queryUser").val();
		if(queryUser!=null && queryUser!="")
			params2['queryUser'] = queryUser;
		params2['queryTime'] = 'quarter';
		$("#dg").datagrid({//?queryOrder=task_id&queryTime=year&queryUser=3
			url : 'queryPagedTaskList.html',
			queryParams: params2 ,
			singleSelect : true,
			toolbar : [ {
				iconCls : 'icon-edit',
				text : '下达',
				handler : function() {
					taskToSub();
				}
			}, '-', {
				iconCls : 'icon-edit',
				text : '反馈',
				handler : function() {
					taskToHead();
				}
			}, '-', {
				iconCls : 'icon-man',
				text : '详细',
				handler : function() {
					detailTask();
				}
			} ],
			pagination : true,
			pageList : [ 10 ],
			columns : [ [ {
				field : 'task_id',
				title : '序号',
				width : 50
			}, {
				field : 'task_name',
				title : '任务名称',
				width : 100
			}, {
				field : 'dept_name',
				title : '责任部门',
				width : 100
			}, {
				field : 'co_dept',
				title : '协助部门',
				width : 100
			}, {
				field : 'username',
				title : '责任人',
				width : 100
			}, {
				field : 'deadline',
				title : '要求完成时间',
				width : 100
			}, {
				field : 'state',
				title : '当前状态',
				width : 100
			}, {
				field : 'task_desc',
				title : '任务描述',
				width : 100
			}, {
				field : 'finish_desc',
				title : '完成情况',
				width : 100
			} ] ],
			rowStyler:function(index,row){
				var now=new Date();
				var date = new Date();
				date.setFullYear(row.deadline.substring(0,4));
				date.setMonth(row.deadline.substring(4,6)-1);
				date.setDate(row.deadline.substring(6,8));
		        if (date.getTime() - now.getTime() < 0 && (row.state !='已办结')){   
		            return 'background-color:red;';   
		        }else if((date.getTime() - now.getTime()) < 7 * 24 * 3600 * 1000 && (row.state !='已办结')){
		        	return 'background-color:yellow;';
		        }
		    }
		});
		var p = $('#dg').datagrid('getPager');  
	    $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [10],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	        /*onBeforeRefresh:function(){ 
	            $(this).pagination('loading'); 
	            alert('before refresh'); 
	            $(this).pagination('loaded'); 
	        }*/ 
	    });  
	});
	function detailTask(){
		var row = $("#dg").datagrid("getSelected");
		if (row) {
			var string = "<table>";
			string += "<tr><td>任务名称</td>" + "<td>"+row.task_name+"</td></tr>";
			string += "<tr><td>责任部门</td>" + "<td>" + row.dept_name+"</td></tr>";
			string += "<tr><td>协助部门</td>" + "<td>" + row.co_dept+"</td></tr>";
			string += "<tr><td>责任人</td>" + "<td>" + row.username+"</td></tr>";
			string += "<tr><td>要求完成时间</td>" + "<td>" + row.deadline+"</td></tr>";
			string += "<tr><td>当前状态</td>" + "<td>" + row.state+"</td></tr>";
			string += "<tr><td>任务描述</td>" + "<td>" + row.task_desc+"</td></tr>";
			string += "<tr><td>完成情况</td>" + "<td>" + row.finish_desc+"</td></tr>";
			string += "</table>"
			$.messager.alert("查看",string);
		}else{
			$.messager.alert("错误","请选中一个编辑");
		}
	}
	function taskToSub(){
		var row = $("#dg").datagrid("getSelected");
		if (row) {
			$("#dlg").dialog("open").dialog('setTitle', 'Edit Task');
			$("#fm").form("load", row);
			$(".disabled").each(function(){this.disabled = true});
			$(".easyui-datebox").each(function(){
				var data = $(this).datebox('getValue');
				$(this).datebox({disabled: true});
				$(this).datebox('setValue',data);
			});
			$("#resp_user_id").empty();
			<#if subUserList??>
				<#list subUserList as zxc>
				$("#resp_user_id").append('<option value="${zxc.id}">${zxc.username}</option>');
				</#list>
			</#if>
			$("#taskState").empty();
			$("#taskState").append('<option value="1" selected >未反馈</option>');
			for(var i=0;i<$("#dept_id option").length;i++){
				if($("#dept_id option").eq(i).text() == row.dept_name){
					$("#dept_id option").eq(i).attr("selected",true);
					break;
				}
			}
			$("#opType").val("update");
		}else{
			$.messager.alert("错误","请选中一个编辑");
		}
	}
	function taskToHead(){
		var row = $("#dg").datagrid("getSelected");
		if (row) {
			$("#dlg").dialog("open").dialog('setTitle', 'Edit Task');
			$("#fm").form("load", row);
			$(".disabled").each(function(){this.disabled = true});
			$(".easyui-datebox").each(function(){
				var data = $(this).datebox('getValue');
				$(this).datebox({disabled: true});
				$(this).datebox('setValue',data);
			});
			$("#resp_user_id").empty();
			<#if headUserList??>
				<#list headUserList as zxc>
				$("#resp_user_id").append('<option value="${zxc.id}">${zxc.username}</option>');
				</#list>
			</#if>
			$("#taskState").empty();
			$("#taskState").append('<option value="2">已反馈</option>');
			for(var i=0;i<$("#dept_id option").length;i++){
				if($("#dept_id option").eq(i).text() == row.dept_name){
					$("#dept_id option").eq(i).attr("selected",true);
					break;
				}
			}
			$("#opType").val("update");
		}else{
			$.messager.alert("错误","请选中一个编辑");
		}
	}
	function saveuser() {
		$(".disabled").each(function(){this.disabled = false});
		$(".easyui-datebox").each(function(){
			var data = $(this).datebox('getValue');
			$(this).datebox({disabled: false});
			$(this).datebox('setValue',data);
		});
		var result;
		var url;
		if($("#opType").val() == "update"){url = "updateTask.html"}
		else return;
		//alert(url);
		if($("#fm input[name='task_id']").length == 1 && $("#fm input[name='task_id']").val() == "")
			$("#fm input[name='task_id']").val("-1");
		$.ajax({
			type: "POST",
			url: url,
			data: $("#fm").serialize(),
			success: function(msg){
			$.messager.alert("提示信息", "操作成功");
			$("#dlg").dialog("close");
				$("#dg").datagrid("load");
			},
			error : function(){ $.messager.alert("提示信息", "操作失败");}
		});
    }
</script>
</head>
<body>
	<div style="width: 860px" align="right">${username!'匿名用户'},你好。点击右边按钮退出<input type="button" value="注销" onclick="javascript:location.href='logout.html'"/></div>
	<input id="queryUser" type="hidden" value="${user_id!''}" />
	<table id="dg" style="width: 860px; height: 350px">
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 450px; height: 400px; padding: 10px 20px;" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">任务编辑</div>
		<form id="fm" method="post">
			<table>
				<tr class="fitem">
					<td><label>任务名称</label></td>
					<td><input name="task_name" class="easyui-validatebox disabled"
						required="true"  /></td>
				</tr>
				<tr class="fitem">
					<td><label>责任部门</label></td>
					<td><select id="dept_id" name="resp_dept_id" class="disabled"> 
						<#if deptList??>
							<#list	deptList as zxc>
								<option value="${zxc.dept_id}">${zxc.dept_name}</option>
							</#list>
						</#if>
					</select></td>
				</tr>
				<tr class="fitem">
					<td><label>配合部门</label></td>
					<td><input name="co_dept" class="easyui-validatebox disabled"
						required="true"  /></td>
				</tr>
				<tr class="fitem">
					<td><label>责任人</label></td>
					<td><select id="resp_user_id" name="resp_user_id"> 
					</select></td>
				</tr>
				<tr class="fitem">
					<td><label>完成时间</label></td>
					<td><input name="deadline" class="easyui-datebox disabled" required="true"  /></td>
				</tr>
				<tr class="fitem">
					<td><label>状态</label></td>
					<td><select id="taskState" name="state">
					</select></td>
					</tr>
				<tr class="fitem">
					<td><label>任务描述</label></td>
					<td><textarea name="task_desc" rows="4" cols="25" class='disabled'></textarea></td>
				</tr>
				<tr class="fitem">
					<td><label>完成情况</label></td>
					<td><textarea name="finish_desc" rows="4" cols="25"></textarea></td>
				</tr>
			</table>
			<input id="opType" type="hidden" />
			<input type="hidden" name="task_id" value="null"/>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveuser()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
</body>
</html>
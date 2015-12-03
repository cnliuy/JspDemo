<%@page import="model.vo.BusinessPojo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>增加</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script language="javascript" type="text/javascript">

		function checkpwd(){
			var hasError = 0;		
			var name =  document.getElementById("name").value;	
			var link =   document.getElementById("link").value;
			
			if (name.length== 0)
			{
					 hasError = 1;
				 	 alert("请重输入项目名称内容！");
	        		 document.forms[0].name.focus();
			}
			if (link.length== 0)
			{
					 hasError = 1;
				 	 alert("请重输入链接内容！");
	        		 document.forms[0].link.focus();
			}
			
			if (hasError == 0) 
	    	{
	        		document.forms[0].submit();
	    	}
		}
	</script>
	</head>

	<body>
		<%		
			int BusinessPojoId =(int)request.getAttribute("BusinessId");
		%>
		<form action="<%=path%>/AddNew" name="myform" method="post">
		<table>
			<input name="Id" type="hidden" value="<%=BusinessPojoId%>">
			<tr>
				<td><font face="楷体_GB2312">项目名称</font>：</td>
				<td><input name="Businessname" id="name" type="text" value="" ></td>
			</tr>
			<tr>
				<td><font face="楷体_GB2312">项目链接</font>：</td> 
				<td><input name="Businesslink" id="link" type="text" value=""></td>
			</tr>
			
			<tr>
				<td>
					<input type="button" onclick="checkpwd()" value="确定">
				</td>
				<td>
				<input type="reset" value="取消">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>

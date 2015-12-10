<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="model.vo.BusinessPojo"/>

<html>
	<head>

		<title>列表页面</title>

	</head>

	<body>
	
	<a href="AddBusinessPoJo">增加一个测试增值页面</a>  &nbsp;&nbsp; &nbsp; <a href="/JspDemo/Tolist">展示列表页</a> <br></br>
		<table>
			<tr>
				<td><strong>Id  </strong> 
				</td>
				<td><strong> 
					栏目名称</strong> 
				</td>
				<td><strong> 
					栏目链接</strong> 
				</td>
				<td><strong>编辑</strong></td>
				<td><strong>删除</strong></td>
				
			</tr>

			<%
				ArrayList<BusinessPojo> bps =(ArrayList)request.getAttribute("BusinessPojoList");
				for (BusinessPojo bp : bps) {
				
			%>
			<tr>
				<td>
					<%=bp.getId()%>
				</td>
				<td>
					<%=bp.getBusinessname()%>
				</td>
				<td>  
					<a href="<%=bp.getBusinesslink()%>"><%=bp.getBusinesslink()%></a>
				</td>
				<td><a href="Update?Id=<%=bp.getId()%>">编辑</a></td>
				<td>
				    <a href="DelBusinessPoJo?Id=<%=bp.getId()%>"
		               style="color:red;" onclick="javascript:return del('您真的确定要删除吗？\n\n删除后将不能恢复!');">删除</a>
				</td>
				
			</tr>
			<%
			}
			%>
		</table>
		<br><br>
		
	</body>
</html>

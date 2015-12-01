<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="model.vo.BusinessPojo"/>

<html>
	<head>

		<title>列表页面</title>

	</head>

	<body>
		<table>
			<tr>
				<td><strong> 
					栏目id</strong> 
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
				<td><a href="Update?userid=<%=bp.getId()%>">编辑</a></td>
				<td><a href="DelUser?userid=<%=bp.getId()%>">删除</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</body>
</html>

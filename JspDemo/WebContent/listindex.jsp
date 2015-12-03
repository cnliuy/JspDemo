<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="model.vo.BusinessPojo"/>

<html>
	<head>
		<title>列表页面</title>
		<style>  
		#all{  
		    margin:0px auto;  
		    width:500px; /* 必须制定宽度 */  
		    height:200px;  
		    /* background-color:blue;  */
		}  
		</style>
	</head>

	<body>
	<div id="all">  
		<table class="divcss5">
			<tr>

				<td><strong> 
					栏目名称</strong> 
				</td>				
			</tr>

			<%
				ArrayList<BusinessPojo> bps =(ArrayList)request.getAttribute("BusinessPojoList");
				for (BusinessPojo bp : bps) {				
			%>
			<tr>
				<td>
					<a href="<%=bp.getBusinesslink()%>"><%=bp.getBusinessname()%></a>
				</td>				
			</tr>
			<%
			}
			%>
		</table>
	</div>	 
	</body>
</html>

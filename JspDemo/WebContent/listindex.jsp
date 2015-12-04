<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="model.vo.BusinessPojo"/>

<html>
	<head>
		<title>列表页面</title>
		<style>  
		#all{  
		    margin:0px auto;  
		    width:960px; /* 必须制定宽度 */  
		    height:600px;  
		    /* background-color:blue;  */
		}  
		</style>
	</head>

	<body>
	<div id="all">  
		<table class="divcss5">
			<tr>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<strong> 
					增值业务</strong> &nbsp;&nbsp;&nbsp;&nbsp;
				</td>	
				<td></td>			
			</tr>

			<%
				ArrayList<BusinessPojo> bps =(ArrayList)request.getAttribute("BusinessPojoList");
				for (int  i = 0 ; i < bps.size() ; i=i+3) {
					 int  max = bps.size() ; 
			%>
			<tr>	
			<% 
					BusinessPojo [] b3=  new BusinessPojo[3];
					for(int o=0 ;o<3 ;o++){	
						int  here = i+o ;
						if (max > here ){
							b3[o]=bps.get(here) ;
						}else{
							break;
						}
						
						 
			%>
			 
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<%=b3[o].getBusinesslink()%>"><%=b3[o].getBusinessname()%></a>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				
			 
			<%
					}
			%>
			</tr>
			<%
			}
			%>
		</table>
	</div>	 
	</body>
</html>

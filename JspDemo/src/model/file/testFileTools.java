package model.file;

import java.io.IOException;

import model.bo.AdminBean;
import model.vo.BusinessPojo;

public class testFileTools {
	/**
	 * 补写文件
	 * **/
//	public static void main(String[] args) {
//		FileTools ft = new FileTools();
//		String filepath = "./data/list.txt";
//		ft.createFile("./data/", "list.txt"); 
//		//Id:123;Name:项目名称;link:http://blog.csdn.net/liuhenghui5201/article/details/8276278
//		String appendline = "Id:5;Name:项目名称5;link:http://blog.csdn.net/liuhenghui5201/article/details/8276278";		
//		String id = "5";
//		try {
//			ft.executeWriteFileAppendlineByCheckId(appendline, filepath, id);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	/**
	 * 查询Id
	 * **/
//	public static void main(String[] args) {
//		FileTools ft = new FileTools();
//		String filepath = "./data/list.txt";
//		ft.createFile("./data/", "list.txt"); 		
//		String id = "2"; 
//		String outline=	ft.executeReadFileReturnOneline(filepath, id) ;
//		System.out.println(outline);		
//	}
	
	/**
	 * 查询用户名
	 * 
	 * */
//	public static void main(String[] args) {
//		FileTools ft = new FileTools();
//		String filepath = "./data/user.txt";
//		ft.createFile("./data/", "user.txt"); 
//		//Id:1;Username:admin;Password:admin
//		AdminBean ab = new AdminBean();
//		Boolean b = ab.checkAdminUser("admin", "admin");
//		System.out.println(b);
//
//	}
	
 
	
//	public static void main(String[] args) {
//		BusinessPojo bp = new BusinessPojo();
//		String s = "Id---5;Name---项目名称5;Link---http://blog.csdn.net/liuhenghui5201/article/details/8276278";
//		bp = bp.genBusinessPojoByStr(s);
//		System.out.println(bp.getId());
//		System.out.println(bp.getBusinesslink());
//		System.out.println(bp.getBusinessname());
//	}
	
	
	public static void main(String[] args) {
		String ss = "ha哈哈哈 ";
		//FileTools.getUTF8XMLString(ss) ;
		System.out.println(FileTools.getUTF8XMLString(ss) );
	}

}

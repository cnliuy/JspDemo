package model.bo;

import model.file.FileTools;
import model.vo.AdminPojo;

public class AdminBean {
	
	/**
	 * 按id查Admin用户
	 * @author Liuy
	 */

	public boolean checkAdminUser(String username ,String password) {
		boolean flag=false;
		String filepathtype="userfile";
		String keystr = "Username";
		username = username.toString();
		FileTools ft = new FileTools();
		String adminstr =ft.executeReadFileReturnOneline(filepathtype, keystr, username);
		//System.out.println("adminstr串："+adminstr);
		if ("".equals(adminstr)){
		
		}else{
			AdminPojo ap = AdminPojo.genAdminPojoByStr(adminstr);
			if(ap == null){
				flag = false;
			}else{
				String hereusername = ap.getUsername() ;
				String herepassword = ap.getPassword() ;
				if ( password.equals(herepassword)){
					flag = true;
				}
			}
		}
		
		return flag;
	}

}

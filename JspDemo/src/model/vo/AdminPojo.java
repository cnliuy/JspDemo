package model.vo;

/**
 * 使用 FileTools 文件的 pojo类
 * */
public class AdminPojo {

	private int id;
	private String username;
	private String password;
	
	public static AdminPojo genAdminPojoByStr(String s) {
		
		AdminPojo ap = null;
		String [] linea = s.split(";");
		
		if(linea.length>0){
			int hereidi = 0;
			String username = "";
			String password = "";
			
			for (int i=0; i<linea.length; i++){ 
				String [] lineai = linea[i].split("---");	       	
				if(("Id").equals(lineai[0])){
					String hereid = lineai[1];
					if(hereid == null || "".equals(hereid)){						
					}else{
						hereidi = Integer.parseInt(hereid.trim());
					}		
				}
				if(("Username").equals(lineai[0])){
					username = lineai[1];
					username = username.trim();					
				}
				if(("Password").equals(lineai[0])){
					password = lineai[1];
					password = password.trim() ;					
				}
			}
			if(hereidi==0||("").equals(username)||("").equals(password)){				
			}else{
				ap= new AdminPojo();
				ap.setId(hereidi);
				ap.setPassword(password);				
				ap.setUsername(username); 
			}
		}
		return ap;		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}

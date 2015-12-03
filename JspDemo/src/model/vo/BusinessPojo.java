package model.vo;

/**
 * 使用 FileTools 文件的 pojo类
 * */
public class BusinessPojo {

	private int id;
	private String businessname;
	private String businesslink;
	
	public static String genStrbyPojoByBusinessPojo(BusinessPojo bp) {
		String s ="";
		if (bp.getId()  ==0 ){
		
		}else{
			//Id---5;Name---项目名称5;Link---http://blog.csdn.net/liuhenghui5201/article/details/8276278
			s = "Id---"+bp.getId()+";Name---"+bp.getBusinessname()+";Link---"+bp.getBusinesslink();
		}
		return s;
	}
	
	
	public static BusinessPojo genBusinessPojoByStr(String s) {
		
		BusinessPojo ap = null;
		String [] linea = s.split(";");
		
		if(linea.length>0){
			int hereidi = 0;
			String name = "";
			String link = "";
			
			for (int i=0; i<linea.length; i++){ 
				String [] lineai = linea[i].split("---");	       	
				if(("Id").equals(lineai[0])){
					String hereid = lineai[1];
					if(hereid == null || "".equals(hereid)){						
					}else{
						hereidi = Integer.parseInt(hereid.trim());
					}		
				}
				if(("Name").equals(lineai[0])){
					name = lineai[1];
					name = name.trim();					
				}
				if(("Link").equals(lineai[0])){
					link = lineai[1];
					link = link.trim() ;					
				}
			}
			if(hereidi==0||("").equals(link)||("").equals(name)){				
			}else{
				ap= new BusinessPojo();
				ap.setId(hereidi);
				ap.setBusinessname(name); 			
				ap.setBusinesslink(link); 
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

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getBusinesslink() {
		return businesslink;
	}

	public void setBusinesslink(String businesslink) {
		this.businesslink = businesslink;
	}

	

}

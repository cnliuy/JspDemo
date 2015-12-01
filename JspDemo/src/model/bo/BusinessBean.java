package model.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.file.FileTools;
import model.vo.AdminPojo;
import model.vo.BusinessPojo;

public class BusinessBean {
	
	/**
	 * 查Business列表
	 * @author Liuy
	 */

	public ArrayList <BusinessPojo>  businessList ( ) {
		boolean flag=false;
		String filepathtype="listfile";		
		FileTools ft = new FileTools();
		List<String> ls = ft.executeReadFile(filepathtype);
		Iterator<String> Ils = ls.iterator();
		ArrayList <BusinessPojo> bps = new ArrayList<BusinessPojo>();
		String s ; 
		
		while(Ils.hasNext()){
			s = Ils.next();
			BusinessPojo bp = null ;
			bp = bp.genBusinessPojoByStr(s) ;
			if (bp==null){
				
			}else{
				bps.add(bp);
			}			
		}		
		return bps;
	}

}

package model.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

public class FileTools {
	private String filepathuser = Messages.getString("FileTools.filepathuser") ;   //$NON-NLS-1$
	private String filepathlist = Messages.getString("FileTools.filepathlist") ; //$NON-NLS-1$
	
	
	/**
	 * 读文件
	 *   
	 */
	public List<String> executeReadFile(String filepathtype) {
		
		String filepath = ""; 
		if ("userfile".equals(filepathtype)){ //$NON-NLS-1$
			filepath = filepathuser ;
		}else{
			filepath = filepathlist ; 
		}
		File  theFile = new File(filepath) ;
	
	
		LineIterator lit = null;
		List filelist = null;
		
		try {
			lit = FileUtils.lineIterator(theFile, "UTF-8");	 //$NON-NLS-1$
			if(lit.hasNext()){
				filelist = new ArrayList <String>();
			}
		    while (lit.hasNext()) {
		        String line = lit.nextLine();
		        filelist.add(line);		        	       
		    }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    LineIterator.closeQuietly(lit);
		}
		return filelist ;
	}
	
	
	/**
	 * 读文件 返回一行
	 *  根据id
	 *   
	 */
	public  String  executeReadFileReturnOneline(String filepathtype ,String id  ) {
		
		String filepath = ""; 
		if ("userfile".equals(filepathtype)){ //$NON-NLS-1$
			filepath = filepathuser ;
		}else{
			filepath = filepathlist ; 
		}		
		
		File  theFile = new File(filepath) ;
		LineIterator lit = null;
		List filelist = null;
		int idi = Integer.parseInt(id.trim());
		String reString = ""; //$NON-NLS-1$
		
		try{
			lit = FileUtils.lineIterator(theFile, "UTF-8");	 //$NON-NLS-1$
			
		    while (lit.hasNext()) {		    	
		        String line = lit.nextLine();
		        int befound = findlinebyId(line,idi);
	        	if(befound == 1){
	        		reString = line ;
	        		break;
	        	}		        	
		    }       	       
		    
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
		    LineIterator.closeQuietly(lit);
		}
		return reString ;
	}
	
	/**
	 * 读文件 返回最大Id
	 *  根据id
	 *   
	 */
	public  int  gogetMaxId(String filepath ) {	
		
		File  theFile = new File(filepath) ;
		LineIterator lit = null;
		List filelist = null;
		int maxid = 0;		 
		
		try{
			lit = FileUtils.lineIterator(theFile, "UTF-8");	 //$NON-NLS-1$
			
		    while (lit.hasNext()) {		    	
		        String line = lit.nextLine();
		        int hereid = gogetlineId(line);
		        if( hereid > maxid ){
		        	maxid = hereid ;
		        }	        	
		    }       	       
		    
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
		    LineIterator.closeQuietly(lit);
		}
		return maxid ;
	}
	
	
	private  int gogetlineId(String line){
		int lineId = 0 ;
		String [] linea = line.split(";"); //$NON-NLS-1$
	    if(linea.length>0){
		   String [] linea0 = linea[0].split("---"); //$NON-NLS-1$
		   if(("Id").equals(linea0[0])){ //$NON-NLS-1$
		       String hereid = linea0[1] ;
		       lineId = Integer.parseInt(hereid.trim());

		   }
	     }
	     return lineId;
	
	}
	
	
	
	/**
	 * Id:123;
	 * 
	 * @param 整行的内容
	 * @param id int型
	 * 
	 * @return  -1  不匹配
	 * 			 1 匹配
	 * 
	 * */		
	private static int findlinebyId(String line ,int id){
	   int flag = -1;
	   String [] linea = line.split(";"); //$NON-NLS-1$
       if(linea.length>0){
	       	String [] linea0 = linea[0].split("---"); //$NON-NLS-1$
	       	if(("Id").equals(linea0[0])){ //$NON-NLS-1$
	       		String hereid = linea0[1] ;
	       		int hereidi = Integer.parseInt(hereid.trim());
	       		if(hereidi==id){
	       			flag=1;
	       		}
	       	}
       }
       return flag;
       
       
       //for (int i=0; i<linea.length; i++) {  
       //	System.out.println(linea[i]);
       //}		    	
       //filelist.add(line);	
    }
	
	
	
	/**
	 * 根据str，（key）值和value值找到相应的行
	 * 
	 * @param 整行的内容
	 * @param   String 内容
	 * 
	 * @return  -1  不匹配
	 * 			 1 匹配
	 * 
	 * */		
	private static int findlinebyKeyStr(String line ,String keystr, String valuestr ){
		
	   int flag = -1;
	   String [] linea = line.split(";"); //$NON-NLS-1$
       if(linea.length>0){ 
    	  
    	   for(int li = 0 ;li < linea.length ;li ++ ){
    		   
		       	String [] linea0 = linea[li].split("---"); //$NON-NLS-1$
		       	if((keystr).equals(linea0[0])){
		       		String herevaluestr = linea0[1] ;
		       		herevaluestr=herevaluestr.trim() ;
		       		//System.out.println("herevaluestr="+herevaluestr );
		       		//System.out.println("valuestr="+valuestr );
		       		if(herevaluestr.equals(valuestr) ){
		       			flag=1;
		       		}
		       	}
		       	
    	   }
       }
       return flag;	
    }
	/**
	 * 传入文件类型 返回文件路径
	 * 
	 *  "listfile" "userfile"
	 * */
	public String gogetFilePath(String filepathtype)   {
		String filepath = ""; //$NON-NLS-1$
		if ("userfile".equals(filepathtype)){ //$NON-NLS-1$
			filepath = filepathuser ;
		}else{
			filepath = filepathlist ; 
		}
		return filepath;	
	}
	
	/**
	 * 读文件 返回一行
	 *		根据key
	 *
	 *   
	 */
	public  String  executeReadFileReturnOneline(String filepathtype ,String keystr , String valuestr  ) {
		String filepath = ""; //$NON-NLS-1$
		if ("userfile".equals(filepathtype)){ //$NON-NLS-1$
			filepath = filepathuser ;
		}else{
			filepath = filepathlist ; 
		}
		
		
		File  theFile = new File(filepath) ;
		if(!theFile.exists())  {    
		    try {
		    	System.out.println("未找到文件 开始创建"); //$NON-NLS-1$
		    	theFile.createNewFile();    
		    	
		    } catch (IOException e) {		       
		        e.printStackTrace();    
		    }    
		}
		LineIterator lit = null;
		List filelist = null;
		valuestr = valuestr.trim();
		String reString = ""; //$NON-NLS-1$
		
		try{
			lit = FileUtils.lineIterator(theFile, "UTF-8");				 //$NON-NLS-1$
		    while (lit.hasNext()) {
		    	
		        String line = lit.nextLine();
		        int befound = findlinebyKeyStr(line, keystr, valuestr);
	        	if(befound == 1){
	        		reString = line ;
	        		break;
	        	}		        	
		    }       	       
		    
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
		    LineIterator.closeQuietly(lit);
		}
		return reString ;
	}

	/**
	 * 写文件
	 *   根据id 修改某行文件的内容
	 * @throws IOException 
	 */
	public void executeWriteFile(String filepath ,String id, String writenline)   {
		
		List <String> ls = null ;
		List <String> newls = new ArrayList<String>() ;		
		File  theFile = new File(filepath) ;
		int idi = Integer.parseInt(id.trim());
		int intreturnflag = 0 ;
		
		FileInputStream fin = null ;
		
		try {
			fin= new FileInputStream(filepath);
			ls = IOUtils.readLines(fin,"utf-8");
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
				 
		
		Iterator<String> itt = ls.iterator();
		while(itt.hasNext()){
			String line = itt.next();
			int i = findlinebyId(line , idi);
			if(i==1){
				line = writenline+ IOUtils.LINE_SEPARATOR;
			}			
			//System.out.println(a);
			newls.add(line) ;
		}
        //StringWriter swr= new StringWriter();       
        //IOUtils.writeLines(ls, IOUtils.LINE_SEPARATOR_WINDOWS, swr);
        //IOUtils.writeLines(newls, IOUtils.LINE_SEPARATOR, swr);
		System.out.println("写文件开始 "+new Date()); //$NON-NLS-1$
		
		FileOutputStream fos2 = null ;
		try {
			fos2 =new FileOutputStream(theFile);
			IOUtils.writeLines(newls, IOUtils.LINE_SEPARATOR,fos2,"UTF-8");
		} catch (IOException e) {			
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(fos2);
		}	
		
        System.out.println("写文件结束 "+new Date()); //$NON-NLS-1$
        
	}
	
	/**
	 * 写文件
	 *   根据id 删除某行文件的内容
	 * @throws IOException 
	 */
	public void executeWriteFileDelLine(String filepath ,String id) throws IOException  {
		
		List <String> ls = null ;
		List <String> newls = new ArrayList<String>() ;		
		File  theFile = new File(filepath) ;
		int idi = Integer.parseInt(id.trim());
		int intreturnflag = 0 ;
		
		FileInputStream fin=new FileInputStream(filepath);
		ls = IOUtils.readLines(fin,"utf-8");		 //$NON-NLS-1$
		Iterator<String> itt = ls.iterator();
		while(itt.hasNext()){
			String line = itt.next();
			int i = findlinebyId(line , idi);
			if(i==1){				 
			}else{	
				if(line.equals(IOUtils.LINE_SEPARATOR)){
					
				}else if ((line.trim()).equals("")|| (line.trim()).equals(IOUtils.LINE_SEPARATOR)){
					System.out.println("===========");
				}else{
					newls.add(line) ;
				}
			}
		}

		System.out.println("写文件开始 "+new Date()); //$NON-NLS-1$
		FileOutputStream fos2 = null ;
		try {
			fos2 =new FileOutputStream(theFile);
			IOUtils.writeLines(newls, IOUtils.LINE_SEPARATOR,fos2,"UTF-8"); 	
		} catch (IOException e) {			
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(fos2);
		}
        System.out.println("写文件结束 "+new Date()); //$NON-NLS-1$
        
	}
	
	/**
	 * 写文件
	 *   尾部添加一行
	 * */
	
	public void executeWriteFileAppendline( String line,String filepath)    {
		
//		BufferedWriter writer = null;
//		try {
//			FileOutputStream fos = new FileOutputStream(filepath,true);  
//			//writer = new BufferedWriter(new FileWriter(filepath, true) );//old 写法
//			writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8") );			
//			writer.append(IOUtils.LINE_SEPARATOR);
//			String strUTF8 = URLDecoder.decode(line, "UTF-8");			
//			writer.append(strUTF8);
//		} catch (IOException e) {			
//			e.printStackTrace();
//		}finally{			
//			try {
//				writer.close();
//			} catch (IOException e) {			
//				e.printStackTrace();
//			}			
//		}	
		
		//------------------------------
		List <String> ls = null ;
		List <String> newls = new ArrayList<String>() ;		
		File  theFile = new File(filepath) ;
	
		int intreturnflag = 0 ;		
		FileInputStream fin;
		try {
			fin = new FileInputStream(filepath);
			ls = IOUtils.readLines(fin,"utf-8");
			Iterator<String> itt = ls.iterator();
			while(itt.hasNext()){	
				String hereline =  itt.next() ;
				if ((hereline.trim()).equals("")|| (hereline.trim()).equals(IOUtils.LINE_SEPARATOR)){
					
				}else{
					newls.add(hereline) ;
				}
			}
			newls.add(IOUtils.LINE_SEPARATOR);
			newls.add(line);
		} catch (Exception e2) {			
			e2.printStackTrace();
		}		
		
		System.out.println("写文件开始 "+new Date()); //$NON-NLS-1$
		FileOutputStream fos2 = null ;
		try {
			fos2 =new FileOutputStream(theFile);
			IOUtils.writeLines(newls, IOUtils.LINE_SEPARATOR,fos2,"UTF-8"); 	
		} catch (IOException e) {			
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(fos2);
		}
        System.out.println("写文件结束 "+new Date()); //$NON-NLS-1$
		
		
	}
	
	/**
	* Get XML String of utf-8
	* 
	* @return XML-Formed string
	*/
	public static String getUTF8XMLString(String ss) {
	 
		StringBuffer sb = new StringBuffer();
		sb.append(ss);
		String xmString = "";
		String xmlUTF8="";
		try {
			xmString = new String(sb.toString().getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
			System.out.println("utf-8 编码：" + xmlUTF8) ;
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		return xmlUTF8;
	}
	
	
	/**
	 * 写文件
	 *   尾部添加一行  含id核查
	 *   
	 *   @return -1 没有写入
	 *   		 1   有写入
	 * */
	public int executeWriteFileAppendlineByCheckId ( String appendline,String filepath ,String id)  throws IOException {
		
		
		List <String> ls = null ;
		List <String> newls = new ArrayList<String>() ;		
		File  theFile = new File(filepath) ;
		int idi = Integer.parseInt(id.trim());
		int intreturnflag = -1 ;
		boolean towrite = true ;
		
		FileInputStream fin=new FileInputStream(filepath);
		ls = IOUtils.readLines(fin,"utf-8");		 //$NON-NLS-1$
		Iterator<String> itt = ls.iterator();
		
		
		if(itt.hasNext()){
		
			while(itt.hasNext()){
				String line = itt.next();
				int bei = findlinebyId(line , idi);
				if(bei==1){
					//line = appendline+ IOUtils.LINE_SEPARATOR;
					towrite = false; //有匹配的id，禁止写入
				}			
				//System.out.println(a);
				newls.add(line) ;
			}
			newls.add(appendline) ;
	        //StringWriter swr= new StringWriter();       
	        //IOUtils.writeLines(ls, IOUtils.LINE_SEPARATOR_WINDOWS, swr);
	        //IOUtils.writeLines(newls, IOUtils.LINE_SEPARATOR, swr);
			if(towrite){
				System.out.println("写文件开始 "+new Date()); //$NON-NLS-1$
				IOUtils.writeLines(newls, IOUtils.LINE_SEPARATOR,new FileOutputStream(theFile)); 		
		        System.out.println("写文件结束 "+new Date()); //$NON-NLS-1$
		        intreturnflag = 1;
			}			
			return  intreturnflag ;
		
		}else{
			BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
			//writer.append(IOUtils.LINE_SEPARATOR);
			writer.append(appendline);
			writer.close(); 
			intreturnflag = 1;
			return  intreturnflag ;
		}
		
	}
	
	
	
	
	public void createFile(String path ,String filename){ 
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		} 
		// fileName表示你创建的文件名；为txt类型；fileName="test.txt"
	
		File file = new File(f,filename);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {		
				e.printStackTrace();
			}
		}

	}
}

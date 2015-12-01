package model.bo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.dao.DBTools;
import model.vo.User;


public class UserBean {
	/**
	 * 按用户名、密码核查用户
	 * @author Liuy
	 */

	public boolean check(String username,String password) {
		boolean flag=false;
		String sql="select * from t_userinfo where user_name='"+username+"' and user_password='"+password+"' and lock_state = 'f'";
		
		DBTools db= new DBTools();
		ResultSet rs=db.executeQuery(sql);
		try {
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}
		
//		if ("aaa".equals(username)) {
//			flag=true;
//		}
		return flag;
	}
	
	
	/**
	 * 用户登陆
	 * 注：之前需要按用户名、密码核查用户
	 * */
	public boolean login(String username,String password) {
		boolean flag=false;
		
		User user =UserBean.getOneByNamePwd(username, password);
		String  userid = user.getUserid();
		
		Date today=new Date(System.currentTimeMillis());
		
		Integer  id =UserBean.getNextId("t_userstate");		
		String sql=   "insert into t_userstate (id , userid , logintime ) values ( " + id+ ","+userid+",'"+today+"')" ;
		System.out.println(sql);		
		DBTools db= new DBTools();
		
		try {
			int i=db.executeUpdate(sql);

			if (i>0) {
				flag=true;
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;

	}
	
	/**
	 * 用户退出登录
	 * 
	 * 	传入userid参数
	 * @author Liuy
	 * */
	public boolean logout (String userid) {
		boolean flag=false;
		String sql=  "update t_userstate set logouttime='"+new Date() +  "' where userid=" + userid ;		
		DBTools db= new DBTools();
		
		
		int i=-1;
		try {
			i = db.executeUpdate(sql);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (i>0) {
			flag=true;
		}
		return flag;
	}
	/**
	 * 通过userid得到一个用户对象 
	 * @author Liuy 
	 */
	public User getOne(String userid) {
		User user = new User();
		user.setUserid(userid);
		String sql = "select * from t_userinfo where user_id=" + userid;
//		System.out.println(sql);
		DBTools db= new DBTools();
		ResultSet rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				String username = rs.getString("user_name");
				String password = rs.getString("user_password");				
				String useremail = rs.getString("user_email");
				Date intime = rs.getDate("user_intime");
				Boolean userstat = rs.getBoolean("lock_state");				
				user.setUsername(username);
				user.setPassword(password);
				user.setUseremail(useremail);
				user.setIntime(intime);
				user.setUserstat(userstat);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}
		
		return user;
	}
	

	/**
	 * 通过用户名和密码得到用户对象 
	 * @author Liuy
	 * */

	public static User getOneByNamePwd(String username,String password) {
		
		User user = new User();
		String sql="select * from t_userinfo where user_name='"+username+"' and user_password='"+password+"'";
		System.out.println(sql);
		DBTools db= new DBTools();
		ResultSet rs=db.executeQuery(sql);
		try {
			if(rs.next()){
				String userid =  rs.getString("user_id");
				String useremail = rs.getString("user_email");
				Date intime = rs.getDate("user_intime");
				Boolean userstat = rs.getBoolean("lock_state");
				
				user.setUserid(userid);
				user.setUsername(username);
				user.setPassword(password);
				user.setUseremail(useremail);
				user.setIntime(intime);
				user.setUserstat(userstat);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}		
		return user;
	}	
	
	/**
	 * 得到用户对象列表
	 * @author Liuy
	 * */
	public ArrayList<User> seachAll() {
		ArrayList<User> users = new ArrayList<User>();
		
		String sql = "select * from t_userinfo";
		System.out.println(sql);
		DBTools db= new DBTools();
		ResultSet rs = db.executeQuery(sql);

		try {
			while (rs.next()) {
				String userid =  rs.getString("user_id");
				String username = rs.getString("user_name");
				String password = rs.getString("user_password");				
				String useremail = rs.getString("user_email");
				Date intime = rs.getDate("user_intime");
				Boolean userstat = rs.getBoolean("lock_state");			

				User user = new User();
				user.setUserid(userid);
				user.setUsername(username);
				user.setPassword(password);
				user.setIntime(intime);
				user.setUseremail(useremail);
				user.setUserstat(userstat);				
				users.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}
		return users;
	}
		
	/** 
	 * 按用户姓名查找t_userinfo表,返回一组用户对象
	 * @author Liuy
	 * */
	public ArrayList<User> seachByName(String username) {
		
		ArrayList<User> users = new ArrayList<User>();
		
		String sql = "select * from t_userinfo where user_name like '"+username+"'";
		System.out.println(sql);
		DBTools db= new DBTools();
		ResultSet rs = db.executeQuery(sql);

		try {
			while (rs.next()) {

				String userid =  rs.getString("user_id");
				String usernamere = rs.getString("user_name");
				String password = rs.getString("user_password");				
				String useremail = rs.getString("user_email");
				Date intime = rs.getDate("user_intime");
				Boolean userstat = rs.getBoolean("lock_state");
				
				
				User user = new User();
				user.setUserid(userid);
				user.setUsername(usernamere);
				user.setPassword(password);
				user.setUseremail(useremail);
				user.setIntime(intime);
				user.setUserstat(userstat);
				users.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}
		return users;
	}
	
	
	/**
	 *在t_userinfo中删除用户。
	 *
	 *注：需删除t_userstate表中的对应关系（两表有约束关系）
	 *注：由于t_userstate表的存在，基本上不使用此方法，来删除用户
	 *@author Liuy
	 */
	public boolean del(int userid) {
		boolean flag=false;
		String sql="delete from t_userinfo where user_id="+userid;
		
		DBTools db= new DBTools();
		int i=db.executeUpdate(sql);
		
		if (i>0) {
			flag=true;
		}
		
		return flag;
	}
	

	/**
	 * 按用户名核查用户（静态方法）
	 * 用户名查重
	 * @author Liuy
	 */
	public static boolean check(String username) {
		//核查用户表里是否存在此用户
		//存在为ture
		//不存在为false
		boolean flag=false;
		String sql="select * from t_userinfo where user_name='"+username+"'";		
		DBTools db= new DBTools();
		ResultSet rs=db.executeQuery(sql);
		try {
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}
		return flag;
	}

	
	/**
	 * 更新用户。
	 * 注：之前需核查一下用户名。
	 * 调静态的check方法进行用户名查重。
	 *@author Liuy
	 */
	public boolean update(User user) {


			boolean flag=false;		
			String sql="update t_userinfo set user_name='"+user.getUsername()+"',user_password='"+user.getPassword()
						+"',user_email='"+user.getUseremail()+ "',lock_state='"+user.isUserstat()+ "'  where userid="+user.getUserid()+"";
		
			DBTools db= new DBTools();
			int i=db.executeUpdate(sql);
			if (i>0) {
				flag=true;
			}
			return flag;

	}
	
	/**
	 * 更新用户密码。
	 * 注：之前需核查一下用户名。
	 * 调静态的check方法进行用户名查重。
	 *@author Liuy
	 */
	public boolean updatePassword(String username ,String password) {


			boolean flag=false;		
			String sql="update t_userinfo set user_password= '"+password+"'  where user_name=  '"+username+"'";		
			DBTools db= new DBTools();
			int i=db.executeUpdate(sql);
			if (i>0) {
				flag=true;
			}
			return flag;

	}
	
	
	/**
	 * 添加用户。
	 * 注：之前需核查一下用户名。
	 * 调静态的check方法进行用户名查重。
	 * @author Liuy
	 * */
	public boolean add(User user) {
	
		boolean flag=false;		
		
			Date today=new Date(System.currentTimeMillis());
			String sql="insert into t_userinfo (user_id, user_name , user_password , user_email , user_intime ,lock_state)" +
					"	 values("+UserBean.getNextId("t_userinfo")+",'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getUseremail()+"','"+today+"' ,false )";
		
			DBTools db= new DBTools();
			int i=db.executeUpdate(sql);
		
			if (i>0) {
				flag=true;
			}
			return flag;

	}
	
	/**
	 * 选择t_userinfo表中最大的user_id值的下一个
	 * @author Liuy
	 */
	public static int getNextId(String tablename) {
		int nextid = 0;
		String sql = null;
		if (tablename == "t_userinfo")  {
			 sql="select max(user_id) from "+ tablename ;
		}else{			
			 sql="select max(id) from "+ tablename ;
		}
		
		DBTools db= new DBTools();
		ResultSet rs = db.executeQuery(sql);
		
		try {
			if(rs.next()){
				nextid = rs.getInt("max");
				nextid = nextid  + 1 ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeRs(db);
			db.closeStmt(db);
			db.closeConn(db);
		}
		
		return nextid;
	}
	
	
	
	
	
}

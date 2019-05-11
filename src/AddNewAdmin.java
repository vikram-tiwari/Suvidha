import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddNewAdmin extends HttpServlet {
	
	Connection con;
	String newEmail, newPass, reenterPass;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		newEmail=req.getParameter("newEmail");
		newPass=req.getParameter("newPass");
		reenterPass= req.getParameter("reenterPass");
		
		System.out.println(newEmail+""+newPass);
		if(getValidation(newEmail,newPass,reenterPass)){
		if(insertAdmin(newEmail,newPass)){
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Thank for adding new Admin');");  
			out.println("</script>");
			RequestDispatcher rs = req.getRequestDispatcher("loginsuccess.html");
            rs.include(req, resp);
			
		}
		}
		
		else{
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Invalid Credentials');");  
			out.println("</script>");
//			
			RequestDispatcher rs = req.getRequestDispatcher("loginsuccess.html");
            rs.include(req, resp);
			
		}
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public boolean getValidation(String newEmail, String newPass, String reenterString){
		boolean status=false;
		int ct=0;
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String PASS_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{4,20})";
		if(newPass==null || newEmail==null || reenterString==null){
			return status;
		}
		else{
		if(newEmail.matches(EMAIL_REGEX)){
			ct++;
		}
		if(newPass.matches(PASS_REGEX)){
			ct++;
		}
		if(newPass.equals(reenterString)){
			ct++;
		}
		if(ct==3){
			status=true;
		}
		}
		return status;
	}
	
	public boolean insertAdmin(String newEmail, String newPass){
		boolean st=false;
		
		try {
			con = SuvidhaConnection.getConnection();
			String sql="INSERT INTO `administrator`(admin_id, admin_password) VALUES(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			System.out.println("connectionOk");
			ps.setString(1,newEmail);
			ps.setString(2, newPass);
			int i=ps.executeUpdate();
			if(i>0){
				st=true;
			System.out.println("connectionOk");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(con!=null){
			con.close();
				}
				}
			catch(Exception e){	
				
			}
		}
		return st;
	}
	
	

}

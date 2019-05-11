import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	
	
	
	/**
	 * Created by Sohail Ahmed
	 */
	Connection con;
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doGet(req, resp);
		resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = resp.getWriter();
        String email=req.getParameter("email");
        String pass=req.getParameter("pass");
        System.out.println(email+""+pass);
     // Set standard HTTP/1.1 no-cache headers.
     		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");

     		// Set standard HTTP/1.0 no-cache header.
     		resp.setHeader("Pragma", "no-cache");
     		resp.setDateHeader("Expires", 0);
		if(validate(email, pass)){
			RequestDispatcher rs = req.getRequestDispatcher("loginsuccess.html");
            rs.forward(req, resp);
		}
		else{
			RequestDispatcher rs = req.getRequestDispatcher("erroradmin.html");
            rs.forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doPost(req, resp);
		doGet(req, resp);
	}
	public boolean validate(String email, String pass){
		boolean st=false;
		System.out.println(email+""+pass);
		try {
			con=SuvidhaConnection.getConnection();
			if(con!=null){
			System.out.println("conection");
			PreparedStatement ps =con.prepareStatement
                    ("select * from administrator where Admin_Id=? and Admin_Password=?");
			System.out.println("conection");
			ps.setString(1, email);
			ps.setString(2, pass);
			System.out.println("conection");
			ResultSet rs =ps.executeQuery();
			System.out.println(rs);
			System.out.println("conection");
			while(rs.next()){
				st=true;
			}
			System.out.println("conection");
			}else{
				System.out.println("null connecion");
			}
		} catch (Exception e) {
			// TODO: handle exception
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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowAdmin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String sql="select * from administrator";
		try {
			con=SuvidhaConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			RequestDispatcher rd=req.getRequestDispatcher("loginsuccess.html");
			rd.include(req, resp);
			out.print("<div class='col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main'>");
			out.print("<div class='row'>");
			out.print(" <div class='col-lg-12'>"
					+ "<div class='panel panel-default'>"
					+ "<div class='panel-heading'>Admin Information</div>");
			
			out.print("<div class='panel-body'>"
					+ "<div class='canvas-wrapper'>");
			
			  out.print("<table class='table'>"
			    +"<thead>"
			    + "<tr>"
			    + "<th>Admin Serial</th>"
			    + "<th>Admin Id</th>"
			    + "<th>Admin Password</th>"
			    + "</tr>"
			    + "</thead>"
			    +"<tbody>");
				
			
			while(rs.next()){
				String aSerial=rs.getString("Admin_Serial");
				String aId=rs.getString("Admin_Id");
				String aPassword=rs.getString("Admin_Password");
				
				out.println("<tr><td>" + aSerial + "</td><td>" + aId + "</td><td>" + aPassword + "</td></tr>");
			}
			
			out.print(" </tbody>"
					+ "</table>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "</div>");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}

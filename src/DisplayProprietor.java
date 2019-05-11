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


public class DisplayProprietor extends HttpServlet {

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
		String sql="select * from proprietor";
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
					+ "<div class='panel-heading'>Proprietor Table</div>");
			
			out.print("<div class='panel-body'>"
					+ "<div class='canvas-wrapper'>");
			
			  out.print("<table class='table'>"
			    +"<thead>"
			    + "<tr>"
			    + "<th>Proprietor Serial</th>"
			    + "<th>User Token</th>"
			    + "<th> Proprietor Id</th>"
			    + "</tr>"
			    + "</thead>"
			    +"<tbody>");
				
			
			while(rs.next()){
				String pSerial=rs.getString("Proprietor_Serial");
				String uToken=rs.getString("userToken");
				String pId=rs.getString("Proprietor_Id");
				
				out.println("<tr><td>" + pSerial + "</td><td>" + uToken + "</td><td>" + pId + "</td></tr>");
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

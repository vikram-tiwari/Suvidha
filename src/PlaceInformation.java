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


public class PlaceInformation extends HttpServlet {

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
		String sql="select * from place_metadata";
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
					+ "<div class='panel-heading'>Place Information</div>");
			
			out.print("<div class='panel-body'>"
					+ "<div class='canvas-wrapper'>");
			
			  out.print("<table class='table'>"
			    +"<thead>"
			    + "<tr>"
			    + "<th>Place Serial</th>"
			    + "<th>Place Id</th>"
			    + "<th>Place Name</th>"
			    + "<th>Place Rating</th>"
			    + "<th>Place Type</th>"
			    + "<th>User Token</th>"
			    + "</tr>"
			    + "</thead>"
			    +"<tbody>");
				
			
			while(rs.next()){
				String pSerial=rs.getString("Place_Serial");
				String pId=rs.getString("Place_Id");
				String pName=rs.getString("Place_Name");
				String pRating=rs.getString("Place_Rating");
				String pType=rs.getString("Place_Type");
				String uToken=rs.getString("userToken");
				
				out.println("<tr><td>" + pSerial + "</td><td>" + pId + "</td><td>" + pName + "</td><td>"+pRating+"</td><td>"+pType+"</td><td>"+uToken+"</td></tr>");
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

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




public class DisplayVisitors extends HttpServlet {

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
		String sql="select * from user";
		try {
			con=SuvidhaConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			RequestDispatcher rd=req.getRequestDispatcher("loginsuccess.html");
			rd.include(req, resp);
/*out.print("<div id='sidebar-collapse' class='col-sm-3 col-lg-2 sidebar'>"
					+ "<br />"
					+ "<ul class='nav menu'>"
					+ "<li class='active'><a href='index.html'><svg class='glyph stroked dashboard-dial'><use xlink:href='#stroked-dashboard-dial'></use></svg> Dashboard</a></li>"
					+ "<li><a href='displayVisitors'><svg class='glyph stroked calendar'><use xlink:href='#stroked-calendar'></use></svg> Display Visitors</a></li>"
					+ "<li><a href='charts.html'><svg class='glyph stroked line-graph'><use xlink:href='#stroked-line-graph'></use></svg> Display Proprietor</a></li>"
					+ "<li><a href='tables.html'><svg class='glyph stroked table'><use xlink:href='#stroked-table'></use></svg> Show Place Information</a></li>"
					+ "<li><a href='forms.html'><svg class='glyph stroked pencil'><use xlink:href='#stroked-pencil'></use></svg> Show Proprietor Places</a></li>"
					+ "<li class='parent '>"
					+ "<a href='#'>"
					+ "<span data-toggle='collapse' href='#sub-item-1'><svg class='glyph stroked chevron-down'><use xlink:href='#stroked-chevron-down'></use></svg></span> Dropdown "
					+ "</a>"
					+ "<ul class='children collapse' id='sub-item-1'>"
					+ "<li>"
					+ "<a class='' href='#'>"
					+ "<svg class='glyph stroked chevron-right'><use xlink:href='#stroked-chevron-right'></use></svg> Hospital Information"
					+ "</a>"
					+ "</li>"
					+ "<li>"
					+ "<a class='' href='#'>"
					+ "<svg class='glyph stroked chevron-right'><use xlink:href='#stroked-chevron-right'></use></svg> College Information"
					+ "</a>"
					+ "</li>"
					+ "<li>"
					+ "<a class='' href='#'>"
					+ "<svg class='glyph stroked chevron-right'><use xlink:href='#stroked-chevron-right'></use></svg> Goverment Offices"
					+ "</a>"
					+ "</li>"
					+ "<li>"
					+ "<a class='' href='#'>"
					+ "<svg class='glyph stroked chevron-right'><use xlink:href='#stroked-chevron-right'></use></svg> Shopping Malls"
					+ "</a>"
					+ "</li>"
					+ "</ul>"
					+ "</li>"
					+ "</div>");*/
			out.print("<div class='col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main'>");
			out.print("<div class='row'>");
			out.print(" <div class='col-lg-12'>"
					+ "<div class='panel panel-default'>"
					+ "<div class='panel-heading'>Visitors Table</div>");
			
			out.print("<div class='panel-body'>"
					+ "<div class='canvas-wrapper'>");
			
			  out.print("<table class='table'>"
			    +"<thead>"
			    + "<tr>"
			    + "<th>User Serial</th>"
			    + "<th>User Name</th>"
			    + "<th>User Email</th>"
			    + "<th>User Password</th>"
			    + "<th>User Mobile</th>"
			    + "<th>User Token</th>"
			    + "</tr>"
			    + "</thead>"
			    +"<tbody>");
				
			
			while(rs.next()){
				String uSerial=rs.getString("userSerial");
				String uName=rs.getString("userName");
				String uEmail=rs.getString("userEmail");
				String uPassword=rs.getString("userPassword");
				String uMobile=rs.getString("userMobile");
				String uToken=rs.getString("userToken");
				out.println("<tr><td>" + uSerial + "</td><td>" + uName + "</td><td>" + uEmail + "</td><td>"+ uPassword +"</td><td>"
						+ uMobile +"</td><td>" + uToken +"</td></tr>");
			}
			
			out.print(" </tbody>"
					+ "</table>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "</div>"
					+ "</div>");
			
			
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
	}
	

}

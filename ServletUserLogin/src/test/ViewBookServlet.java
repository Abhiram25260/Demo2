package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
@SuppressWarnings("serial")
public class ViewBookServlet extends GenericServlet {
	public Connection con;
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String BCode=req.getParameter("BCode");
		try {
			PreparedStatement ps=con.prepareStatement("select * from Book where Book_ID=?");
			ps.setString(1, BCode);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=req.getRequestDispatcher("/Link.html");
				rd.include(req, res);
				pw.println("<br>BOOK_ID:"+rs.getString(1));
				pw.println("<br>BOOK_NAME:"+rs.getString(2));
				pw.println("<br>BOOK_QTY:"+rs.getString(3));
				pw.println("<br>BOOK_PRICE:"+rs.getString(4));
			}
			else {
				pw.println("INVALID BOOK CODE");
				RequestDispatcher rd=req.getRequestDispatcher("/Viewbook.html");
				rd.include(req, res);
			}
		}catch(Exception e) {}
	}

}

package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
@SuppressWarnings("serial")
public class LoginServlet extends GenericServlet {
	public Connection con;
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String UName=req.getParameter("UName");
		String PWord=req.getParameter("PWord");
		
		try {
			PreparedStatement ps=con.prepareStatement("select * from UserDetails where UName=? AND Password=?");
			ps.setString(1,UName);
			ps.setString(2, PWord);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				pw.println("WELCOME "+rs.getString(3));
				RequestDispatcher rd=req.getRequestDispatcher("/Link.html");
				rd.include(req, res);
			}
			else {
				pw.println("INVALID USER NAME OR PASSWORD");
				RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
				rd.include(req, res);
			}
		}catch(Exception e) {}
	}

}

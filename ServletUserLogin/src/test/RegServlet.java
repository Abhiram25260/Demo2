package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
@SuppressWarnings("serial")
public class RegServlet extends GenericServlet{
	public Connection con; 
	public void init()throws ServletException{
		con=DBConnection.getcon();
	}
	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String UName=req.getParameter("UName");
		String PWord=req.getParameter("PWord");
		String FName=req.getParameter("FName");
		String LName=req.getParameter("LName");
		String Addr=req.getParameter("Addr");
		long Phno=Long.parseLong(req.getParameter("Phno"));
		String MID=req.getParameter("MID");
		
		try {
			PreparedStatement ps=con.prepareStatement("insert into UserDetails values(?,?,?,?,?,?,?)");
			ps.setString(1, UName);
			ps.setString(2, PWord);
			ps.setString(3, FName);
			ps.setString(4, LName);
			ps.setString(5, Addr);
			ps.setLong(6, Phno);
			ps.setString(7, MID);
			
			int k=ps.executeUpdate();
			if(k>0) {
				pw.println("USER REGISTERED SUCESSFULLY............!");
				
				RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
				rd.include(req, res);
			}
		}catch(Exception e) {}
	}

}

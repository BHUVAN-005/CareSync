package in.bhu;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/regserv")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;
    Statement st=null;
    public RegistrationServlet() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myseries","root","root");
        	st=con.createStatement();
        }
        catch(ClassNotFoundException e)
        {
        	System.out.println("type 4 driver not found");
        }
        catch(SQLException e)
        {
        	System.out.println(e.getMessage());
        }
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try
		{
		
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String mail=req.getParameter("email");
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		long ph=Long.parseLong(req.getParameter("num"));
		st.executeUpdate("insert into users(fname,lname,email,uname,password,phone) values('"+fname+"','"+lname+"','"+mail+"','"+uname+"','"+pwd+"',"+ph+")");
		PrintWriter out=res.getWriter();
		out.print("<h1>Registeration succesful..</h1><br>");
		out.write("<a href='Login.jsp'>Please login</a>");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
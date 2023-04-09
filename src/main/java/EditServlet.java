import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet
{
	int count;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{     //getPrintWriter
		PrintWriter out=response.getWriter();
		//set content type
		response.setContentType("text/html");
		
		
		int empid=Integer.parseInt(request.getParameter("empid"));
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		int salary=Integer.parseInt(request.getParameter("salary"));
		String birthdate=request.getParameter("birthdate");
		//load the jdbc driver
		
		try {
			//register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//get connection
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","Sonali7559321912");
			String query="update employee set name=?,address=?,gender=?,salary=?,birthdate=? where empid=?";
			
			
			PreparedStatement pst=con.prepareStatement(query);
			//set the values
			pst.setString(1,name);
			pst.setString(2,address);
			pst.setString(3,gender);
			pst.setInt(4,salary);
			pst.setString(5,birthdate);
			pst.setInt(6, empid);
			
			out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css'>");
			//execute the query
			count=pst.executeUpdate();
			if(count==1)
			{
				out.println("<h2 align='center'>Record Updated successfully </h2>");
			}
			else
			{
				out.println("<h2  align='center'>Record not Updated</h2>");
			}
			out.println("<center>");
			out.println("<a href='index.html'><button class='btn btn-success' align='center'>Home</button></a>");
			out.println("<br>");
			out.println("<br>");
			out.println("<a href='view'><button class='btn btn-primary'  align='center'>View Employee</button></a>");
			out.println("<center>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
		
	}
}

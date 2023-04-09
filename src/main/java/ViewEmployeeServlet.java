import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/view")
public class ViewEmployeeServlet extends HttpServlet
{

	int count;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{     //getPrintWriter
		PrintWriter out=response.getWriter();
		//set content type
		response.setContentType("text/html");
		
		//load the jdbc driver
	
		try {
			//register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//get connection
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","Sonali7559321912");
			String query="select * from employee";
			PreparedStatement pst=con.prepareStatement(query);
			
			out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css'>");
			//resultset
			ResultSet rs=pst.executeQuery();
			out.println("<center>");
			out.println("<div style='margin:auto; width=600px; margin-top:100px padding:20px'>");
			out.println("<table class='table table-hover table-stripped table-bordered'>");
			out.println("<thead><h4>Employee Details<h4></thead>");
			out.println("<br>");
			out.println("<tr>");
			out.println("<th>EmpId</th>");
			out.println("<th>Name</th>");
			out.println("<th>Address</th>");
			out.println("<th>Gender</th>");
			out.println("<th>Salary</th>");
			out.println("<th>Birthdate</th>");
			out.println("<th>Update</th>");
			out.println("<th>Delete</th>");
			out.println("</tr>");
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+ "</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getInt(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("<td> <a href='update?empid="+rs.getInt(1)+"'>Update </a></td>");
				out.println("<td><a href='delete?empid="+rs.getInt(1)+"'>Delete </a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("<a href='index.html'><button class='btn btn-success'>Add Employee</button></a>");
		out.println("<br>");
		out.println("<br>");
		
		out.println("</div>");
		out.println("</center>");
		
		out.close();
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
		
	}
	

}

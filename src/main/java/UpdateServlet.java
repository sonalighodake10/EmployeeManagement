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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet
{
  
	int count;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{     //getPrintWriter
		PrintWriter out=response.getWriter();
		//set content type
		response.setContentType("text/html");
		
		
		int empid=Integer.parseInt(request.getParameter("empid"));
		//load the jdbc driver
		try {
			//register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//get connection
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","Sonali7559321912");
			
			String query1="select name,address,gender,salary,birthdate from employee where empid=?";
			
			PreparedStatement pst=con.prepareStatement(query1);
			
			
			out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css'>");
			
			pst.setInt(1, empid);
			//ResultSet
			ResultSet rs=pst.executeQuery();
			rs.next() ;
			
			out.println("<center><body><html>");
			out.println("<div style='margin:auto; width=800px; margin-top:50px'>");
			
			out.println("<form action='editurl?empid="+empid+"' method='post'>");
			out.println("<h3>Update Employee details</h3>");
			out.println("<table class='table table-hover table-stripped' align='center'>");
			
			out.println("<tr>");
			out.println("<td>Name</td>");
			out.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Address</td>");
			out.println("<td><input type='text' name='address' value='"+rs.getString(2)+"'></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Gender</td>");
			out.println("<td><input type='text' name='gender' value='"+rs.getString(3)+"'></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Salary</td>");
			out.println("<td><input type='text' name='salary' value='"+rs.getInt(4)+"'></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>BirthDate</td>");
			out.println("<td><input type='date' name='birthdate' value='"+rs.getString(5)+"'></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td><button type='submit' class='btn btn-success'>Update</button></td>");
			out.println("<td><button type='reset' class='btn btn-secondary'>Cancel</button></td>");
			out.println("</tr>");

			out.println("</table>");
			out.println("</form>");
			out.println("</body></html></center>");		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("<center>");
		out.println("<a href='index.html'><button class='btn btn-success'>Add Employee</button></a>");
		out.println("<br>");
		out.println("<br>");
		
		out.println("<a href='view'><button class='btn btn-primary'>view Employee</button></a>");
		out.println("</div>");
		out.println("</center>");
		
		out.close();
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
		
	}
}

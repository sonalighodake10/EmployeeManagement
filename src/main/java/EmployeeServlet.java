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
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	int count;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{     //getPrintWriter
		PrintWriter out=response.getWriter();
		//set content type
		response.setContentType("text/html");
		//get the values
		int empid=Integer.parseInt(request.getParameter("EmployeeId"));
		String name=request.getParameter("Name");
		String address=request.getParameter("Address");
		String gender=request.getParameter("Gender");
		int salary=Integer.parseInt(request.getParameter("Salary"));
		String birthdate=request.getParameter("BirthDate");
		
		//load the jdbc driver
		
		try {
			//register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//get connection
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","Sonali7559321912");
			String query="insert into employee(empid,name,address,gender,salary,birthdate) values(?,?,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(query);
			//set the values
			pst.setInt(1,empid);
			pst.setString(2, name);
			pst.setString(3, address);
			pst.setString(4, gender);
			pst.setInt(5, salary);
			pst.setString(6, birthdate);
			//execute the query
			count=pst.executeUpdate();
			out.println("<center>");
			out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css'>");
			if(count==1)
			{
				out.println("<h2>Record Added successfully</h2>");
			}
			else
			{
				out.println("<h2>Record not Added</h2>");
			}
			out.println("<a href='index.html'><button class='btn btn-success'>Home</button></a>");
			out.println("<a href='view'><button class='btn btn-primary'>view Employee</button></a>");
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

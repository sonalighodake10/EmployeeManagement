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


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet
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
			String query="delete from employee where empid=?";
			int empid=Integer.parseInt(request.getParameter("empid"));
			PreparedStatement pst=con.prepareStatement(query);
			//set the values
			pst.setInt(1,empid);
			out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css'>");
			out.println("<html><head>");
			out.println("<script>");
			out.println("alert(Are you sure want to delete employee?)");
			out.println("</script>");
			out.println("</html></head>");
			//execute the query
			count=pst.executeUpdate();
			if(count==1)
			{
				out.println("<h2 align='center'>Record Deleted successfully </h2>");
			}
			else
			{
				out.println("<h2  align='center'>Record not Added</h2>");
			}
			out.println("<a href='index.html'><button class='btn btn-success' align='center'>Home</button></a>");
			out.println("<br>");
			out.println("<br>");
			out.println("<a href='view'><button class='btn btn-primary'  align='center'>View Employee</button></a>");
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

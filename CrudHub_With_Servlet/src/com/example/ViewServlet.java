package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<center><h2><font color='green'><a href='welcome.html'>Home Page!!</a></font></h2></center>");
		out.println("<a href='index.html'>Add New Employee</a>");
		out.println("<h1>All Details</h1>");
		
		List<Emp> list=EmpDao.getAllEmployees();
		out.print("<body bgcolor='lightyellow'</body>");
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>FullName</th><th>Password</th><th>Email</th><th>Country</th><th>Mobile</th><th>Edit</th><th>Delete</th></tr>");
		for(Emp e:list){
			out.print("<fontcolor='pink'><tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPassword()+
					"</td><td>"+e.getEmail()+"</td><td>"+e.getCountry()+"</td><td>"+e.getMobile()+
					"</td><td><a href='EditServlet?id="+e.getId()+"'>edit</a></td><td>"
					+ "<a href='DeleteServlet?id="+e.getId()+"'>delete</a></td></tr></font>");
		}
		out.print("</table>");
		
		out.close();
	}
}

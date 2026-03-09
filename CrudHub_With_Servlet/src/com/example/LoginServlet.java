package com.example;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")  
public class LoginServlet extends HttpServlet  
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		String email=request.getParameter("email");  
        String password=request.getParameter("password");
        
        List<Emp> list=new ArrayList<Emp>();
        
        list=new EmpDao().Login(email,password);
        
		if(list.size() != 0){
			
			String Name=list.get(0).getName();
			System.out.println("in User login if block"+Name);
			Cookie ck=new Cookie("name",Name);
			RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
			rd.include(request, response);
			out.print("<center><strong>Admin:</strong>"+Name+"<br></center>");//
			((HttpServletResponse) request).addCookie(ck);
			response.addCookie(ck);
			
			  
	}else{
		
	out.print("<center><h3>Login Failed!!!!!</h3></center>");
		
	           }

          }
    }
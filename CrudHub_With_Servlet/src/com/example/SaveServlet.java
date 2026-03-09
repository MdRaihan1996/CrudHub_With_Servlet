package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String country = request.getParameter("country");

		Emp e = new Emp();
		e.setName(name);
		e.setPassword(password);
		e.setEmail(email);
		e.setMobile(mobile);
		e.setCountry(country);

		int status = EmpDao.save(e);
		if (status == 1) {
			out.print("<center><h2><p><font color='green'>Record saved successfully!!!</font></p></h2></center>");
			request.getRequestDispatcher("index.html").include(request, response);
		} else if (status == 0) {
			out.println("Sorry! unable to save record");
		} else if (status == 3) {
			// out.println("Mobile number Or Email Already Present !!!!!");
			// System.err.println("Mobile number Or Email Already Present !!!!!");
			out.println("<center><h3><font color='red'>Mobile number Or Name Or Email Already Present !!!!</font></h3></center>");
			request.getRequestDispatcher("index.html").include(request, response);
			List<Emp> list = EmpDao.getAllEmployees();

		}

		out.close();
	}

}
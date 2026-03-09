package com.example;
import java.util.*;
//import java.io.IOException;
import java.sql.*;

//import javax.servlet.GenericServlet;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.connector.Request;

public class EmpDao {
	
	
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testservlet","root","root");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Emp e){
		int status=0;
		try{
	
			int count=0;
				Connection con1=EmpDao.getConnection();
				PreparedStatement ps1=con1.prepareStatement("select count(*) from emp where mobile=? or name=? or email=?");
				ps1.setString(1,e.getMobile());
				ps1.setString(2,e.getName());
				ps1.setString(3,e.getEmail());
				ResultSet rs1=ps1.executeQuery();
				if(rs1.next()){
					count=rs1.getInt(1);
				}
				if(count>0){
					status=3;
					
				}
			
			else {
					Connection con=EmpDao.getConnection();
					PreparedStatement ps=con.prepareStatement("insert into emp(name,password,email,mobile,country) values (?,?,?,?,?)");
					ps.setString(1,e.getName());
					ps.setString(2,e.getPassword());
					ps.setString(3,e.getEmail());
					ps.setString(4,e.getMobile());
					ps.setString(5,e.getCountry());
					
					status=ps.executeUpdate();
					
					con.close();
				}
			
			
				con1.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Emp e){
		int status=0;
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update emp set name=?,password=?,email=?,mobile=?,country=? where id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getMobile());
			ps.setString(5,e.getCountry());
			ps.setInt(6,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from emp where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Emp getEmployeeById(int id){
		Emp e=new Emp();
		
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from emp where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setMobile(rs.getString(5));
				e.setCountry(rs.getString(6));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Emp> getAllEmployees(){
		List<Emp> list=new ArrayList<Emp>();
		
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select ID,Name,Password,Email,Mobile,Country from emp");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Emp e=new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setMobile(rs.getString(5));
				e.setCountry(rs.getString(6));
				list.add(e);
			}
		} catch(Exception e){
			
			
		}
		return list;
}

	public static List<Emp> Login(String email,String password){
		
		List<Emp> list=new ArrayList<Emp>();
		try{
			
			Connection con2=EmpDao.getConnection();
			System.out.println("In EmpDao:user block");
			PreparedStatement ps1=con2.prepareStatement( "select * from emp where email=? and password=?");   
				ps1.setString(1,email);
				ps1.setString(2,password);
					ResultSet rs1=ps1.executeQuery();  
					
					while(rs1.next()){
						Emp e=new Emp();
						e.setId(rs1.getInt(1));
				        e.setName(rs1.getString(2));
						e.setEmail(rs1.getString(3));
						e.setPassword(rs1.getString(4));
						e.setCountry(rs1.getNString(5));
						e.setMobile(rs1.getString(6));
						list.add(e);
						
					}
		
					
		con2.close();
	}catch(Exception e){e.printStackTrace(); 
		
	              }
	
		return list;
	      }
	 }



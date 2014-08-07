package mvc;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/*
*This servlet handles the login process.
*After getting the parameters from the Home.html page, a record is inserted in the login table.
*/

public class LoginServlet extends HttpServlet 
{
	private Connection conn;
	private DAO dao;
	private static final long serialVersionUID = 1L;
     
    public LoginServlet() 
    {
        super();
    }
    
    //Service method of the servlet
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		Statement st;
		int ac_no = Integer.parseInt(req.getParameter("acNo"));
		String passwd = req.getParameter("password");
		
		try 
		{
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from login where ac_no = "+ac_no+" and passwd = '"+passwd+"'");
			boolean recordGenuine = rs.next();
			rs.close();
			if(recordGenuine)
			{
				//HttpSession sessionObj  = req.getSession(true);
				//HttpSession sessionObj1  = req.getSession(false);
				/*if(sessionObj1 != null)
				{
					RequestDispatcher rd = req.getRequestDispatcher("LoginFail.jsp");
					rd.forward(req,res);
				}
				else
				{*/
					HttpSession sessionObj  = req.getSession(true);
					Account curAccount=new Account();
					ResultSet rset1 = st.executeQuery("select * from account where ac_no = "+ac_no);
					if(rset1.next())
					{
						curAccount.setAccNo(ac_no);
						curAccount.setAccName(rset1.getString("ac_name"));
						curAccount.setHouseNo(rset1.getString("houseno"));
						curAccount.setStreet(rset1.getString("street"));
						curAccount.setCity(rset1.getString("city"));
						curAccount.setState(rset1.getString("state"));
						curAccount.setCountry(rset1.getString("country"));
						curAccount.setDob(rset1.getDate("dob"));
						curAccount.setEmail(rset1.getString("email"));
						curAccount.setPhoneNo(rset1.getString("phno"));
						curAccount.setGender(rset1.getString("gender"));
						
					}
					rset1.close();
					ResultSet rset2 = st.executeQuery("select * from balance where ac_no = "+ac_no);
					if(rset2.next())
					{
						curAccount.setBalance(rset2.getDouble("amount"));
					}
					rset2.close();
					sessionObj.setAttribute("account", curAccount);
					RequestDispatcher rd = req.getRequestDispatcher("userHome.jsp");
					rd.forward(req,res);
				//}
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("LoginFail.jsp");
				rd.forward(req,res);
			}
			st.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}

    //Init function
	public void init(ServletConfig servConfig)
	{
		dao=new DAO();
		try 
		{
			dao.connect();
			conn=dao.getConn();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}		
	}

	public DAO getDao() 
	{
		return dao;
	}
	
	public void setDao(DAO dao) 
	{
		this.dao = dao;
	}
}

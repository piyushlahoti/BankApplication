package mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.Random;
//import java.io.PrintWriter;
//import java.sql.*;

import javax.servlet.*;

/**
 * Servlet implementation class Validate
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private DAO dao;
	
	
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	PreparedStatement pst=null;
		try 
		{
		//Account acc=(Account)context.getAttribute("account");
		HttpSession sessionObj = req.getSession();
		Account acc = (Account) sessionObj.getAttribute("account");
		//System.out.println(req.getParameter("accNo"));
		int ac_no=acc.getAccNo();
		String ac_name=req.getParameter("accName");
		String houseno=req.getParameter("houseNo");
		String street=req.getParameter("street");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String country=req.getParameter("country");
		String dob=req.getParameter("dob");
		String email=req.getParameter("email");
		//System.out.println(email);
		String phno=req.getParameter("phoneNo");
		String gender=req.getParameter("gender");
		String sql = "UPDATE account SET ac_name=?,houseno=?,street=?,city=?,state=?,country=?,dob=?,email=?,phno=?,gender=? where ac_no=" +ac_no ;
		pst = conn.prepareStatement(sql);
		
		pst.setString(1,ac_name);
		pst.setString(2,houseno);
		pst.setString(3,street);
		pst.setString(4,city);
		pst.setString(5,state);
		pst.setString(6,country);
		pst.setDate(7,Date.valueOf(dob));
		pst.setString(8,email);
		pst.setString(9,phno);
		pst.setString(10,gender);
		
		pst.executeUpdate();
		acc.setAccName(ac_name);
		acc.setCity(city);
		acc.setCountry(country);
		acc.setDob(Date.valueOf(dob));
		acc.setEmail(email);
		acc.setGender(gender);
		acc.setHouseNo(houseno);
		acc.setPhoneNo(phno);
		acc.setState(state);
		acc.setStreet(street);
		
		RequestDispatcher rd = req.getRequestDispatcher("userHome.jsp");
		rd.forward(req,res);
		/*int ac_no=0;
		if(rs.next())
			ac_no = rs.getInt(1);
		pst = conn.prepareStatement("insert into login values(?,?)");		
		//final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//Random rnd = new Random();
       StringBuilder sb = new StringBuilder( 10 );
		   for( int i = 0; i < 10; i++ ) 
		     sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		pst.setInt(1,ac_no);
		pst.setString(2, sb.toString());
		pst.executeUpdate();
		req.setAttribute("rowsInserted", rows);
		req.setAttribute("accountID",ac_no);
		req.setAttribute("password",sb.toString());*/
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		

	} 
}

    public void init(ServletConfig servConfig){
    		
    	dao=new DAO();
    	try {
    		dao.connect();
    		conn=dao.getConn();
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	}

    	public DAO getDao() {
    		return dao;
    	}

    	public void setDao(DAO dao) {
    		this.dao = dao;
    	}

    }

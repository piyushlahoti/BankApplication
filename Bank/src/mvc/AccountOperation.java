package mvc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Servlet implementation class AccountOperation
 */
public class AccountOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private DAO dao;
	private PreparedStatement stmt;
	private PreparedStatement pstmt;
	private PreparedStatement dstmt;

    public AccountOperation() {
        super();
    }

    public void init(ServletConfig config){
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
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ac_no = Integer.parseInt(request.getParameter("acNo"));
		Double amount = Double.parseDouble(request.getParameter("amount"));
		String ac_op = request.getParameter("acOp");
		double balance = 0;
		try {
			stmt = conn.prepareStatement("Select amount from balance where ac_no = ?");
			stmt.setInt(1, ac_no);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()){
				// No details
				JSONObject obj = new JSONObject();
				try {
					obj.put("msgstring","Not possible to withdraw, balance must be minimum of 1000");
					obj.put("status", "FAILED");
					System.out.println("No such rows in database");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String s = obj.toString();
				response.getWriter().write(s);
			}
			else {
			balance = rs.getDouble("amount");
			if(ac_op.equals("WITHDRAW")){
				if(balance < 1000 || (balance-amount) < 1000){
					JSONObject obj = new JSONObject();
						//Not Possible to withdraw
						try {
							obj.put("status","FAILED");
							obj.put("msgstring","Not possible to withdraw, balance must be minimum of 1000");
							System.out.println("Minimum 1000");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						String s = obj.toString();
						response.getWriter().write(s);
					}
				else {
					balance = balance - amount;
					pstmt = conn.prepareStatement("Update balance set amount = ? where ac_no = ?");
					pstmt.setDouble(1, balance);
					pstmt.setInt(2, ac_no);
					int result = pstmt.executeUpdate();
					JSONObject obj = new JSONObject();
					if(result != 0) {
						//Successfully Updated
						try {
							obj.put("balance", balance);
							obj.put("msgstring","Successfully updated");
							obj.put("status","SUCCESSFUL");
							System.out.println("success in withdraw");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						String s = obj.toString();
						response.getWriter().write(s);
					}
					else{
						//Not Successful
						try {
							obj.put("msgstring","Failure in withdrawing");
							obj.put("status","FAILED");
							System.out.println("failure in withdraw");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						String s = obj.toString();
						response.getWriter().write(s);
					}
				}
			}
			else if(ac_op.equals("DEPOSIT")){
				balance = balance + amount;
				dstmt = conn.prepareStatement("Update balance set amount = ? where ac_no = ?");
				dstmt.setDouble(1, balance);
				dstmt.setInt(2, ac_no);
				int result = dstmt.executeUpdate();
				JSONObject obj = new JSONObject();
				if(result != 0) {
					//Successfully Updated
					try {
						obj.put("balance", balance);
						obj.put("msgstring","Successfully updated");
						obj.put("status","SUCCESSFUL");
						System.out.println("deposit successful");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					String s = obj.toString();
					response.getWriter().write(s);
				}
				else{
					//Not Successful
					try {
						obj.put("msgstring","Failure in depositing");
						obj.put("status","FAILED");
						System.out.println("deposit failed");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					String s = obj.toString();
					response.getWriter().write(s);
				}
			}
			else{
				//Not Withdraw nor Deposit
				JSONObject obj = new JSONObject();
				//Not Possible to withdraw
				try {
					obj.put("status","FAILED");
					obj.put("msgstring","It is neither WITHDRAW nor DEPOSIT");
					System.out.println("It is neither WITHDRAW nor DEPOSIT");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String s = obj.toString();
				response.getWriter().write(s);
			}
			
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Getter and Setter for the DAO
		public DAO getDao() 
		{
			return dao;
		}

		public void setDao(DAO dao) 
		{
			this.dao = dao;
		}

}

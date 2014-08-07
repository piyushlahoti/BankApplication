package mvc;

import static org.junit.Assert.*;
import javax.servlet.ServletException;
import org.junit.Test;
import java.sql.Connection;

public class LoginServletTest 
{

	@Test
	public void connectionEstablished() 
	{
		LoginServlet testLoginServlet = new LoginServlet();
		try 
		{
			
			testLoginServlet.init();
			DAO testDao = testLoginServlet.getDao();
			//Connection conn = testDao.getConn();
			assertNotNull(testDao);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

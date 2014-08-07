package mvc;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 * Account bean class which contains all the details of an account viz. balance , account number, account holder's name etc. 
 * All the attributes are private and suitable getters and setters have been added to facilitate accessing them.
 * Camel case naming convention has been followed for the variables, which will further be used in the jsp 
 */


public class Account implements Serializable
{
	
	private int accNo;
	private String accName;
	private Date dob;
	private String phoneNo;
	private String gender;
	private String city;
	private String state;
	private String country;
	private String houseNo;
	private String street;
	private String email;
	private double balance;
	
	public Account()	//Default Constructor
	{
		super();
	}
	
	//Getters and Setters
	public double getBalance() 
	{
		return balance;
	}
	
	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
	
	public int getAccNo() 
	{
		return accNo;
	}

	public void setAccNo(int accNo) 
	{
		this.accNo = accNo;
	}

	public String getAccName() 
	{
		return accName;
	}

	public void setAccName(String accName) 
	{
		this.accName = accName;
	}

	public String getDob() 
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    return df.format(dob);
	}

	public void setDob(Date dob) 
	{
		this.dob = dob;
	}

	public String getPhoneNo() 
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
	}

	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getCountry() 
	{
		return country;
	}

	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getHouseNo() 
	{
		return houseNo;
	}

	public void setHouseNo(String houseNo) 
	{
		this.houseNo = houseNo;
	}

	public String getStreet() 
	{
		return street;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
}

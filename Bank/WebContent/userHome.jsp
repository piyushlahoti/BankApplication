<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="formStyle.css" />
	<script type="text/javascript" src="formvalidation.js"></script>
	<script type="text/javascript" src="AccountOps.js"></script>
	<script type="text/javascript" src="json.js"></script>
	<title>User Space</title>
</head>
<body onLoad="disableSubmitButton()">
	<jsp:useBean id="account" class="mvc.Account" scope="session"></jsp:useBean>
	<form onsubmit="return validate()" method="post" class="elegant-aero" id="detailsform">
		 <h1>Your Profile
    	</h1>
    	<label>
    		<span>Account No:</span>
    		<input type="text" id="accNo" name="accNo" value='${account.accNo}' disabled />
    	</label>
    	 <label>
    		<span>Balance:</span>
    		<input type="text" id="balance" name="balance" value='${account.balance}' disabled />
    	</label>
		<label>
			<span>Name :</span>
			<input type="text" id="accName" name="accName" onfocus="errorclear()" value='${account.accName}' disabled/>
		</label>
		<label>
			<span>Date Of Birth: </span>
			<input type="text" id="dob" name="dob" onfocus="errorclear()" value='${account.dob}' disabled/>
		</label>
		<label>
			<span>Phone :</span>
			<input type="text" id="phoneNo"  name="phoneNo" onfocus="errorclear()" value='${account.phoneNo}' disabled/>
		</label>
		<label>
			<span>Gender: </span>
			<input type="text" id="gender" name="gender" onfocus="errorclear()" value='${account.gender}' disabled/>
		</label>
		<label>
			<span>House No: </span>
			<input type="text" id="houseNo" name="houseNo" onfocus="errorclear()" value='${account.houseNo}' disabled/>
		</label>
		<label>
			<span>Street: </span>
			<input type="text" id="street" name="street" onfocus="errorclear()" value='${account.street}' disabled/>
		</label>
		<label>
			<span>City: </span>
			<input type="text" id="city" name="city" onfocus="errorclear()" value='${account.city}' disabled/>
		</label>
		<label>
			<span>State: </span>
			<input type="text" id="state" name="state" onfocus="errorclear()" value='${account.state}' disabled/>
		</label>
		<label>
			<span>Country: </span>
			<input type="text" id="country" name="country" onfocus="errorclear()" value='${account.country}' disabled/>
		</label>		
		<label>
			<span>Email: </span>
			<input type="text" id="email" name="email" onfocus="errorclear()" value='${account.email}' disabled/>
		</label>				
		<input type="submit" name="Save" id="submitButton" value="Submit" />
	</form>
	<button onclick="allowDetailsUpdate()" id="updateDetails">Update Details</button>
	<div id ="errorLog" class="errorLog">
	</div>
	<div class="elegant-aero" id="depositform">
		<label>
			<span>Amount to be Deposited:</span>
			<input type="text" name="amount" id="amtDep" value="0.0" />		
		</label>
		<label>
			<button id="depositBtn" onclick="depositAmount()" >Deposit</button>
		</label>
	</div>
	
	<div class="elegant-aero" id="withdrawform">
		<label>
			<span>Amount to be Withdrawn:</span>
			<input type="text" name="amount" id="amtWithdraw" value="0.0" />		
		</label>
		<label>
			<button id="withdrawBtn" onclick="withdrawAmount()" >Withdraw</button>
		</label>
	</div>
</body>
</html>
var xmlhttp;

function createXMLHttpRequest() {

		if (window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		}
		else{
			xmlhttp = new XMLHttpRequest();
		}
		
		disablePageFields();
}

function disablePageFields(){
	
	var depositButton = document.getElementById("depositBtn");
	var withdrawButton = document.getElementById("withdrawBtn");
	var depositField = document.getElementById("amtDep");
	var withdrawField = document.getElementById("amtWithdraw");
	var updateBtn = document.getElementById("updateDetails");
	
	depositButton.setAttribute("disabled", true);
	withdrawButton.setAttribute("disabled", true);
	depositField.setAttribute("disabled", true);
	withdrawField.setAttribute("disabled", true);
	
	var form = document.getElementById("detailsform");
	var elements = form.getElementsByTagName("input");
	for (var i = 2, len = elements.length-1; i < len; ++i) {
	    elements[i].disabled = true;
	}
	
	disableSubmitButton();
	updateBtn.disabled = true;
	
	
}

function enablePageFields(){
	
	var depositButton = document.getElementById("depositBtn");
	var withdrawButton = document.getElementById("withdrawBtn");
	var depositField = document.getElementById("amtDep");
	var withdrawField = document.getElementById("amtWithdraw");
	var updateBtn = document.getElementById("updateDetails");
	
	depositButton.disabled = false;
	withdrawButton.disabled = false;
	depositField.disabled = false;
	withdrawField.disabled = false;
	updateBtn.disabled = false;
	
}


function withdrawAmount(){
	createXMLHttpRequest();
	var acNo = document.getElementById("accNo").value;
	var amount = document.getElementById("amtWithdraw").value;
	var acOp = "WITHDRAW";
	var url ="AccountOperation?acNo="+acNo+"&amount="+amount+"&acOp="+acOp;
	//alert(url);
	xmlhttp.onreadystatechange = displayData;
	xmlhttp.open("GET",url,true);
	xmlhttp.send();	
}

function depositAmount()
{
	//alert('Hello');
	createXMLHttpRequest();
	var acNo = document.getElementById("accNo").value;
	var amount = document.getElementById("amtDep").value;
	var acOp = "DEPOSIT";
	var url ="AccountOperation?acNo="+acNo+"&amount="+amount+"&acOp="+acOp;
	//alert(url);
	xmlhttp.onreadystatechange = displayData;
	xmlhttp.open("GET",url,true);
	xmlhttp.send();	
}

function displayData(){
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
		 var str= xmlhttp.responseText;
		 var depositObj = JSON.parse(str);

		 var opStatus = depositObj["status"];
		 var msgString = depositObj["msgString"];
		 
		 
		 
		 if(opStatus == "SUCCESSFUL"){
			 var balanceField = document.getElementById("balance");			 
			 var newBalance = depositObj["balance"];
			 balanceField.value = newBalance; 
		 }
		 else{
			 
		 }
		 
		 enablePageFields();
	}
	
}



function disableSubmitButton(){
	document.getElementById("submitButton").disabled = true;
	document.getElementById("submitButton").style.visibility="hidden";
}

function allowDetailsUpdate(){
	var form = document.getElementById("detailsform");
	var elements = form.getElementsByTagName("input");
	for (var i = 2, len = elements.length-1; i < len; ++i) {
	    elements[i].disabled = false;
	}
	document.getElementById("submitButton").style.visibility="visible";
	document.getElementById("submitButton").disabled = false;
}


$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide();
});

//====== SAVE ============================
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateConsumptionForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#consumptionID").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ConsumptionAPI",
		type : t,
		data : $("#formConsumption").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onConsumptionSaveComplete(response.responseText, status);
		}
	});
}); 

function onConsumptionSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidonsumptionIDSave").val("");
	$("#formConsumption")[0].reset();
}

//===== UPDATE =========================
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#consumptionID").val($(this).closest("tr").find('#consumptionID').val());     
	$("#consumptionID").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#customerName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#customerUsage").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#price").val($(this).closest("tr").find('td:eq(3)').text());
	$("#customerType").val($(this).closest("tr").find('td:eq(4)').text()); 
	

});

//====== DELETE ===========
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ConsumptionAPI",
		type : "DELETE",
		data : "consumptionID=" + $(this).data("consumptionID"),
		dataType : "text",
		complete : function(response, status)
		{
			onConsumptionDeletedComplete(response.responseText, status);
		}
	});
});

function onConsumptionDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

function validateCustomerForm() {  
	// ID  
	if ($("#consumptionID").val().trim() == "")  {   
		return "Insert consumptionID.";  
		
	} 
	
	 // NAME 
	if ($("#customerName").val().trim() == "")  {   
		return "Insert name.";  
	} 
	
	
	//USAGE
	if ($("#customerUsage").val().trim() == "")  {   
		return "Insert customerUsage."; 
		 
	}
	 
	 //PRICE is numerical value  
	var price = $("#price").val().trim();  
	if (!$.isNumeric(price))  {   
		return "Insert a numerical value for price.";  
		
	}
	
	//TYPE
	if ($("#customerType").val().trim() == "")  {   
		return "Insert customerType."; 
		 
	}
	 
	
		

	 
	 return true; 
	 
}
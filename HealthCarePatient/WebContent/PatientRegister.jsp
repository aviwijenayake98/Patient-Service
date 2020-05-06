<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.Item"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/items.js"></script>
	<meta charset="ISO-8859-1">

	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head> 
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Patient Management</h1>
				<form id="formItem" name="formItem" method="post" action="Item.jsp">
					First Name: 
					<input id="Fname" name=Fname type="text"class="form-control form-control-sm">
					 <br> Last Name:
					<input id="Lname" name="Lname" type="text"class="form-control form-control-sm"> 
					<br> Phone Number: 
					<input id="Pnumber" name="Pnumber" type="text"class="form-control form-control-sm"> 
					<br> Email : 
					<input id="Email" name="Email" type="text"class="form-control form-control-sm">
					<br> Age : 
					<input id="Age" name="Age" type="text"class="form-control form-control-sm">
					<br> Address : 
					<input id="Address" name="Address" type="text"class="form-control form-control-sm">
					<br> Password : 
					<input id="Password" name="Password" type="text"class="form-control form-control-sm">
					 <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form><br>


				<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   <div id="divItemsGrid">
   
   <%
   
      Item itemobj = new Item();
      out.print(itemobj.readItems());
   %>
   </div>

			</div>
		</div>
	</div>

</body>
</html>
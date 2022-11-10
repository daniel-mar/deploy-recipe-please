<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!-- c:out ; c:forEach ; c:if -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>recipePlease</title>
    <!-- Bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <div class="container">
      <!-- Beginning of Container -->
		<a href="/logout" class="btn btn-danger mt-2 m-2 float-end">Logout</a>
      <a href="/dashboard" class="btn btn-success mt-2 float-end">Dashboard</a>
		
		<h1>Add a Recipe to the board!</h1>
		
		<form:form action="/createRecipe" method ="post" modelAttribute="recipe">
			<form:hidden path="user" value="${userId }"/> 
			<!-- the path="user" above from Many to One private User user -->
			<div class="form-group">
	            <label>Title:</label>
	            <form:input path="title" class="form-control" />
	            <form:errors path="title" class="text-danger" />
	        </div>
	        <div class="form-group">
	            <label>Creator:</label>
	            <form:input path="creator" class="form-control" />
	            <form:errors path="creator" class="text-danger" />
	        </div>
	        <div class="form-group">
	            <label>Description:</label>
	            <form:textarea path="description" class="form-control" />
	            <form:errors path="description" class="text-danger" />
	        </div>
			
			
			
			<input class="btn btn-info mt-2" type="submit" value="Create Recipe">
		
		</form:form>
		
		
		

		
		
	

   </div> <!-- End of Container -->
  </body>
</html>
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
		
		<a href="logout" class="btn btn-danger mt-2 float-end">Logout</a>
	
		<h1>Welcome, <span class="text-info"><c:out value="${loggedUser.firstName}"></c:out></span>!</h1>
		<p>Check out all of our available recipes!</p>
		
		<a href="/newRecipe" class="btn btn-warning mt-2 float-end">Create New Recipe</a>
		
		<table class ="table table-blue table-striped table-hover">
    	<thead>
    		<tr>
    			<th class"align-middle">Post #</th>
    			<th class"align-middle">Title</th>
    			<th class"align-middle">Creator/Username</th>
    			
    			<th class"align-middle">Posted By</th>
    			<th class"align-middle">Action</th>
    		</tr>
    	</thead>
    	
    	<tbody>
	    	<c:forEach var="i" items="${recipes}">
	    	<tr>
	    			
	    		<td>
	    			<c:out value="${i.id}"></c:out>
	    		</td>
	    		<td>
	    			<a href="/oneRecipe/${i.id }">
		    			<c:out value="${i.title}"></c:out> 
		 			</a> 
	    		</td>
	    				
	    		<td>
	    			<c:out value="${i.creator}"></c:out>
	    		</td>
	    			
	    		<td> 
	    			<p>
	    				<c:out value="${i.user.firstName }"></c:out>
	    			</p>
	    		</td>
	    				
		    	<!-- Allow poster of recipe to edit only their own recipe -->
	    		<td>
		    	
		    	<!-- Can also handle this on the controller / endpoint, to compare the id with stored in session user
		    	   - paired with flash redirects could also do this, but the buttons to view is nicer to view on a table -->
		    	
		    				
		    		<c:if test="${loggedUser.id.equals(i.user.id)}">
			    		<a class="btn btn-primary" href="/editRecipe/${i.id}"> Update Recipe</a>
		    			<a class="btn btn-danger" href="/delete/${i.id}">Delete</a>
		    		</c:if>
		    		
	    		</td>    				
	    				
	    	</tr>
	    	</c:forEach>
    	</tbody>
    			
    	</table>
		

   </div> <!-- End of Container -->
  </body>
</html>
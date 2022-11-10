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
		
		<a href="/logout" class="btn btn-danger mt-2 float-end m-2" >Logout</a>
		<a href="/dashboard" class="btn btn-success mt-2 float-end">Dashboard</a>
	
		<%-- <h1>Welcome, <span class="text-info"><c:out value="${loggedUser.firstName}"></c:out></span>!</h1> --%>
		
		
		<div>
			<h1>Recipe: <span class ="text-info"><c:out value="${recipe.title}"></c:out></span> </h1>
			
			<p><span class ="text-info"><c:out value="${recipe.user.firstName}"></c:out></span> loves <span class ="text-info"><c:out value="${recipe.title}"></c:out></span> by <span class ="text-info"><c:out value="${recipe.creator}"></c:out></span>
			<br>
			<p><span class ="text-info"><c:out value="${recipe.user.firstName}"></c:out></span>'s description</p>
			
			<p><span class ="text-info"><c:out value="${recipe.description}"></c:out></span></p>
			
		</div>
	
		
		

   </div> <!-- End of Container -->
  </body>
</html>
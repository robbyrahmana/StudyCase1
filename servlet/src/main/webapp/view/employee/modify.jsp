<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="p" uri="/WEB-INF/lib/customTags.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/view/header.jsp"></jsp:include>

<script type="text/javascript" src='<c:url value="/view/employee/employee.js"/>'></script>

<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item" aria-current="page">Employee</li>
			<li class="breadcrumb-item active" aria-current="page">Update</li>
		</ol>
	</nav>

	<h3>Update Employee</h3>
	
	<p:if test="${!status}">
		<p:then>
			<div class="alert alert-warning" role="alert">
				${ message  }
			</div>
		</p:then>
	</p:if>
	
	<form>
		<div class="form-group">
			<label for="name">Employee Name</label> 
			<input type="text" class="form-control" id="name" name="name" placeholder="Enter name" value="${employees.name }"/>
		</div>

		<div class="form-group">
			<label for="name">Employee Address</label>
			<textarea class="form-control" id="address" name="address" placeholder="Enter address">${employees.address }</textarea>
		</div>

		<input type="button" value="Update" class="btn btn-primary" onclick="doSubmit('update')"/> 
		<input type="button" value="Back" class="btn btn-success" onclick="doSubmit('back')" /> 
		
		<input type="hidden" name="id" value="${employees.id }">
		<input type="hidden" name="action">
	</form>

</div>

<jsp:include page="/view/footer.jsp"></jsp:include>
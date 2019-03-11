<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="p" uri="/WEB-INF/lib/customTags.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/view/header.jsp"></jsp:include>

<script type="text/javascript" src='<c:url value="/view/employee/employee.js"/>'></script>

<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page">Employee</li>
		</ol>
	</nav>

	<h3>Employee List</h3>
	
	<p:if test="${!status}">
		<p:then>
			<div class="alert alert-warning" role="alert">
				${ message  }
			</div>
		</p:then>
	</p:if>
	
	<form>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Address</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<p:if test="${ employees.size() != 0}">
					<p:then>
						<p:for items="${employees}" value="employee">
							<tr>
								<td>${employee.name}</td>
								<td>${employee.address}</td>
								<td>
									<input type="button" value="Update" class="btn btn-success" onclick="doUpdate(${employee.id})"/>
									<input type="button" value="Delete" class="btn btn-danger" onclick="doDelete(${employee.id})"/>
								</td>
							</tr>
						</p:for>
					</p:then>
					<p:else>
						<tr>
							<td colspan="3">no record found</td>
						</tr>
					</p:else>
				</p:if>
			</tbody>
		</table>
		
		<input type="button" value="Add New Employee" class="btn btn-primary" onclick="doSubmit('add')"/>
		
		<input type="hidden" name="id">
		<input type="hidden" name="action">
	</form>

</div>

<jsp:include page="/view/footer.jsp"></jsp:include>
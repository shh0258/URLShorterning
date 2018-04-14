<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/jpa2/res/common.css">
</head>
<body>
<div class="container">
  <h1>Url 입력 </h1>
  
  <form:form method="post" modelAttribute="Url"> <!-- 모델객체를 사용하여 데이터를 전달받고 움직임  -->
    <div class="form-group">
      <label>변경할 주소 url:</label>
      <form:input path="oriurl" class="form-control w200" />
    </div>
    
    <div class="form-group">
      <label>변경된 주소 url :</label>
      <form:input path="urlencrypt" class="form-control w200" />
    </div>
    
    <button type="submit" class="btn btn-primary">
      <i class="glyphicon glyphicon-ok"></i> 변환
    </button>
  </form:form>
  <c:if test="${ not empty message }">
    <div class="alert alert-info">${ message }</div>
  </c:if>
</div>
</body>
</html>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<%@ page isErrorPage="true" %> <!-- Указываем, что это страница для обработки ошибок -->

<div class="error-container">
    <h1>Ошибка: ${statusCode}</h1>
    <p><strong>Message:</strong> ${errorMessage}</p>
    <p><strong>Exception:</strong> ${exception}</p>
    <p><strong>Requested URI:</strong> ${requestScope['javax.servlet.error.request_uri']}</p>
    <a href="http://localhost:8080/home/jobs" class="btn btn-primary">Go back to the homepage</a>
</div>

<style>
    .error-container {
        text-align: center;
        margin: 50px;
        padding: 50px;
        border: 1px solid #ccc;
        border-radius: 10px;
        background-color: #f9f9f9;
    }
    .error-container h1 {
        color: red;
        font-size: 3rem;
    }
    .error-container p {
        font-size: 1.1rem;
        color: #555;
    }
    .btn {
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
    }
    .btn:hover {
        background-color: #0056b3;
    }
</style>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>


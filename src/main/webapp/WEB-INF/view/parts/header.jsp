<%@ page import="itis.inf304.taskhunter.entities.User" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="<c:url value='/style/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/style/styles.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scroll.js"></script>

</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    %>
<header class="p-3 text-bg-dark navbar-light">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="<c:url value = '/api/jobs' />" class="navbar-brand">
                <img src="<c:url value='/img/logo-taskhunter.png'/>" class="bi me-2" width="40" height="40" alt="Taskhunter">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="<c:url value='/WEB-INF/view/categories.jsp'/> " class="nav-link px-2 link-primary text-white" >Категории</a></li>
                <li><a href="<c:url value='/jobs'/> "class="nav-link px-2 link-primary text-white">Главная</a></li>
                <li><a href="<c:url value='/about'/> " class="nav-link px-2 link-primary text-white" >О нас</a></li>
                <li><a href="<c:url value='/contact'/> " class="nav-link px-2 link-primary text-white ">Контакты</a></li>
            </ul>


            <div class="text-end">
                <c:if test="${empty user}">
                    <a href="<c:url value='/login'/>" class="btn btn-light text-dark me-2">Вход</a>
                    <a href="<c:url value='/register'/>" class="btn btn-primary">Регистрация</a>
                </c:if>
                <c:if test="${not empty user}">
                    <div class="d-flex align-items-center">
                        <a href="<c:url value='#'/>" class="btn btn-primary me-2">Разместить объявление</a>
                        <div class="dropdown text-end">
                            <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="https://github.com/mdo.png" alt="mdo" width="40" height="40" class="rounded-circle" style="border: 2px solid #0078ff; border-radius: 50%;">
                            </a>
                            <ul class="dropdown-menu text-small">
                                <li><a class="dropdown-item" href="<c:url value = '/profile'/>">Профиль</a></li>
                                <li><a class="dropdown-item" href="#">Настройки</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><form class="dropdown-item" method="POST" action="<c:url value ='/logout'/>"><Button class="btn btn-light" type="submit">Выйти</Button></form></li>
                            </ul>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</header>
<div>


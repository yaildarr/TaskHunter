<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="<c:url value = "/style/bootstrap.min.css"/>">
    </head>
    <body>
        <header style="background-color: #f8f9fa; padding: 10px 20px; border-bottom: 1px solid #e0e0e0;">
            <div style="display: flex; justify-content: space-between; align-items: center;">
<%--                <div>--%>
<%--                    <a href="<c:url value = "WEB-INF/index.jsp"/>" style="text-decoration: none;">--%>
<%--                        <img src="path/to/logo.png" alt="Логотип" style="height: 40px;">--%>
<%--                    </a>--%>
<%--                </div>--%>
                <nav>
                    <ul style="list-style: none; display: flex; gap: 20px; margin: 0; padding: 0;">
                        <li><a href="<c:url value = "/WEB-INF/view/index.jsp"/>" style="text-decoration: none; color: #007bff;">Главная</a></li>
                        <li><a href="<c:url value = "/WEB-INF/view/categories.jsp"/>" style="text-decoration: none; color: #007bff;">Категории</a></li>
                        <li><a style="text-decoration: none; color: #007bff;">Поиск</a></li>
                        <li><a href="<c:url value = "/WEB-INF/view/about.jsp"/>" style="text-decoration: none; color: #007bff;">О нас</a></li>
                        <li><a href="<c:url value = "/WEB-INF/view/contact.jsp"/>" style="text-decoration: none; color: #007bff;">Контакты</a></li>
                        <c:if test="${ empty user}">
                            <li><a href="<c:url value = "/WEB-INF/view/login.jsp"/>" style="text-decoration: none; color: #007bff;">Вход</a></li>
                            <li><a href="<c:url value = "/WEB-INF/view/register.jsp"/>" style="text-decoration: none; color: #007bff;">Регистрация</a></li>
                        </c:if>
                        <c:if test="${not empty user}">
                            <li><a href="<c:url value = "/WEB-INF/view/profile.jsp"/>" style="text-decoration: none; color: #007bff;">Профиль</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </header>
        <div>
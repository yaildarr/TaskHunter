<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>
<body>
<div class="container">
    <h2 class="mt-5">Вход в систему</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
                ${errorMessage}
        </div>
    </c:if>

    <form action="<c:url value='/login' />" method="POST">
        <div class="form-group">
            <label for="email">Электронная почта:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary" style="margin-top: 20px;">Войти</button>
    </form>
    <p class="mt-3">Нет аккаунта? <a href="<c:url value='/' />">Зарегистрируйтесь</a></p>
</div>
</body>
<%@include file="/WEB-INF/view/parts/footer.jsp" %>

<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>


<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h2 class="card-title mb-4 text-center">Создать объявление</h2>
                    <form action="<c:url value='/createJob' />" method="POST">
                        <div class="mb-3">
                            <label for="title" class="form-label">Заголовок объявления</label>
                            <input type="text" class="form-control" id="title" name="title" required>
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Описание</label>
                            <textarea class="form-control" id="description" name="description" rows="5" required></textarea>
                        </div>
                        <div class="form-group mb-3">
                            <label for="cityInput">Введите город</label>
                            <input type="text" oninput="loadCity('cityInput','suggestions')" class="form-control" id="cityInput" placeholder="Введите город..." autocomplete="off" name="city">
                            <div id="suggestions" class="dropdown-menu w-100" aria-labelledby="cityInput" style="display: none">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="category" class="form-label">Категория</label>
                            <select class="form-select" id="category" name="category" required>
                                <c:forEach var="category" items="${jobCategories}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="payment" class="form-label">Оплата (руб./час)</label>
                            <input type="number" class="form-control" id="payment" name="payment" required>
                        </div>

                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-success px-5 py-2">Создать объявление</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>

<!-- Подключение скриптов Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>
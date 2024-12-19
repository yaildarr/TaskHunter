<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="<c:url value='/style/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/style/styles.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/searchCity.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scroll.js"></script>

    <style>
        /* Стили для dropdown-menu в хедере */
        .nav-item.dropdown .dropdown-menu {
            background-color: #f8f9fa; /* Светлый фон */
            border: 1px solid #dee2e6; /* Легкая рамка */
            padding: 10px; /* Отступы */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Тень для выпадающего меню */
        }

        /* Дополнительные стили для dropdown-item внутри меню */
        .nav-item.dropdown .dropdown-menu .dropdown-item {
            color: #007bff; /* Цвет текста для элементов */
            padding: 8px 16px; /* Отступы */
            text-align: left; /* Выравнивание текста */
        }

        /* Стили для hover на элементах выпадающего меню */
        .nav-item.dropdown .dropdown-menu .dropdown-item:hover {
            background-color: #007bff; /* Цвет фона при наведении */
            color: #fff; /* Цвет текста при наведении */
        }
    </style>
</head>
<body>
<header class="p-3 text-bg-dark navbar-light">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="<c:url value = '/jobs' />" class="navbar-brand">
                <img src="<c:url value='/img/logo-taskhunter.png'/>" class="bi me-2" width="40" height="40" alt="Taskhunter">
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <!-- Ссылка на Категории с выпадающим списком -->
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link px-2 link-primary text-white dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        Категории
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach var="category" items="${jobCategories}">
                            <li><a class="dropdown-item" href="/home/jobs?category=${category.id}">${category.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li><a href="<c:url value='/jobs'/>" class="nav-link px-2 link-primary text-white">Главная</a></li>
                <li><a href="<c:url value='/about'/>" class="nav-link px-2 link-primary text-white">О нас</a></li>
                <li><a href="<c:url value='/contact'/>" class="nav-link px-2 link-primary text-white">Контакты</a></li>
            </ul>

            <!-- Город с иконкой и модальным окном -->
            <div class="text-light d-flex align-items-center me-3 nav-link link-primary" data-bs-toggle="modal" data-bs-target="#cityModal">
                <i class="bi bi-geo-alt-fill me-2" style="cursor: pointer;"></i>
                <c:if test="${empty city}">
                    <span id="cityLabel">Выберите город</span>
                </c:if>
                <c:if test="${not empty city}">
                    <span id="cityLabel">${city}</span>
                </c:if>
            </div>

            <!-- Модальное окно для выбора города -->
            <div class="modal fade" id="cityModal" tabindex="-1" aria-labelledby="cityModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="cityModalLabel">Выберите город</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="cityForm" name="cityForm" action="<c:url value='/jobs'/>" method="post">
                                <input type="text" oninput="loadCity('cityInput2','suggestions2')" class="form-control" id="cityInput2" placeholder="Введите название города" name="city" autocomplete="off">
                                <div id="suggestions2" class="dropdown-menu" style="display:none;"></div>
                                <button type="button" class="btn btn-secondary mt-3" data-bs-dismiss="modal">Закрыть</button>
                                <button type="submit" class="btn btn-primary mt-3" id="saveCity">Сохранить</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-end">
                <c:if test="${empty user}">
                    <a href="<c:url value='/login'/>" class="btn btn-light text-dark me-2">Вход</a>
                    <a href="<c:url value='/register'/>" class="btn btn-primary">Регистрация</a>
                </c:if>
                <c:if test="${not empty user}">
                    <div class="d-flex align-items-center">
                        <a href="<c:url value='/createJob'/>" class="btn btn-primary me-2">Разместить объявление</a>
                        <div class="dropdown text-end">
                            <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="${user.photoURL}" alt="mdo" width="40" height="40" class="rounded-circle" style="border: 2px solid #0078ff; border-radius: 50%;">
                            </a>
                            <ul class="dropdown-menu text-small">
                                <li><a class="dropdown-item" href="<c:url value = '/profile'/>">Профиль</a></li>
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

</body>
</html>

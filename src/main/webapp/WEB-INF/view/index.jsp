<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<style>
    .card-custom {
        border-radius: 25px; /* Увеличенное округление для карточек */
        padding: 20px; /* Увеличенный паддинг для карточек */
    }
    .search-bar {
        margin-top: 20px; /* Отступ сверху для строки поиска */
        margin-bottom: 30px; /* Отступ снизу для строки поиска */
    }
    .search-bar input {
        height: 50px; /* Увеличенная высота строки поиска */
        border-radius: 25px; /* Округление для строки поиска */
    }
    .search-bar button {
        border-radius: 20px; /* Увеличенное округление кнопки поиска */
        height: 50px; /* Соответствующая высота кнопки */
    }
    .vacancies-title {
        font-size: 24px; /* Уменьшенный размер шрифта для заголовка */
        margin-top: 20px; /* Отступ сверху для заголовка */
        margin-bottom: 20px; /* Отступ снизу для заголовка */
    }
</style>

<div class="container mt-4">
    <!-- Форма поиска под заголовком -->
    <form class="row g-2 search-bar" role="search">
        <div class="col-10">
            <input type="search" class="form-control" placeholder="Поиск по вакансиям..." aria-label="Search" name="search">
        </div>
        <div class="col-2">
            <button class="btn btn-primary w-100" type="submit">Найти</button> <!-- Кнопка поиска занимает всю ширину -->
        </div>
    </form>

    <!-- Заголовок для актуальных вакансий -->
    <h1 class="vacancies-title">Актуальные вакансии</h1>

    <div class="row mb-4">
        <div class="col-md-3"> <!-- Уменьшили ширину области с фильтрами -->
            <h5>Фильтры</h5>
            <form>
                <div class="mb-3">
                    <label for="category" class="form-label">Категория</label>
                    <select id="category" class="form-select" name="category">
                        <option value="">Все категории</option>
                        <option value="it">IT</option>
                        <option value="marketing">Маркетинг</option>
                        <option value="design">Дизайн</option>
                        <option value="finance">Финансы</option>
                    </select>
                </div>
                <div class="mb-3">
                    <%--@declare id="payment"--%><label for="payment" class="form-label">Оплата</label>
                    <input type="number" class="form-control" placeholder="Минимальная оплата" name="minPayment">
                    <input type="number" class="form-control mt-2" placeholder="Максимальная оплата" name="maxPayment">
                </div>
                <button class="btn btn-primary" type="submit">Применить фильтры</button>
            </form>
        </div>

        <div class="col-md-9"> <!-- Увеличили ширину области с объявлениями -->
            <!-- Список объявлений -->
            <div class="row">
                <c:forEach var="job" items="${jobList}">
                    <div class="col-12 mb-3"> <!-- Увеличиваем отступы между карточками -->
                        <div class="card card-custom shadow-sm"> <!-- Используем класс card-custom для округления -->
                            <div class="card-body">
                                <h5 class="card-title">${job.title}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${job.postDate}</h6>
                                <p class="card-text">${job.description}</p>
                                <p class="card-text"><strong>Оплата: ${job.payment} руб.</strong></p>
                                <a href="#" class="btn btn-primary">Откликнуться</a> <!-- Кнопка откликнуться -->
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Примеры объявлений -->
                <div class="col-12 mb-3">
                    <div class="card card-custom shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Помощь с уборкой</h5>
                            <h6 class="card-subtitle mb-2 text-muted">30 сентября 2024</h6>
                            <p class="card-text">Ищу помощника для уборки квартиры на выходные.</p>
                            <p class="card-text"><strong>Оплата: 2000 руб.</strong></p>
                            <a href="#" class="btn btn-primary">Откликнуться</a>
                        </div>
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="card card-custom shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Репетитор по математике</h5>
                            <h6 class="card-subtitle mb-2 text-muted">29 сентября 2024</h6>
                            <p class="card-text">Нужен репетитор для подготовки к экзаменам, занятия по вечерам.</p>
                            <p class="card-text"><strong>Оплата: 1500 руб.</strong></p>
                            <a href="#" class="btn btn-primary">Откликнуться</a>
                        </div>
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="card card-custom shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Разнорабочий на стройку</h5>
                            <h6 class="card-subtitle mb-2 text-muted">28 сентября 2024</h6>
                            <p class="card-text">Требуется разнорабочий для помощи на строительстве, опыт не обязателен.</p>
                            <p class="card-text"><strong>Оплата: 2500 руб.</strong></p>
                            <a href="#" class="btn btn-primary">Откликнуться</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Пагинация -->
            <nav aria-label="Page navigation">
                <ul class="pagination mt-3">
                    <li class="page-item"><a class="page-link" href="#">Назад</a></li>
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="?page=${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link" href="#">Вперед</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>

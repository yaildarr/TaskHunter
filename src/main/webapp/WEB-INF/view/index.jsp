<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<style>
    .search-bar {
        margin-top: 20px;
        margin-bottom: 30px;
    }
    .search-bar input {
        height: 50px;
        border-radius: 25px;
    }
    .search-bar button {
        border-radius: 20px;
        height: 50px;
    }
    .vacancies-title {
        font-size: 24px;
        margin-top: 20px;
        margin-bottom: 20px;
    }
    .filters-fixed {
        position: sticky;
        top: 20px;
    }
    .card {
        transition: transform 0.2s ease-in-out;
        cursor: pointer;
    }
    .card:hover {
        transform: scale(1.03);
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
    }
</style>

<div class="container mt-4">
    <form class="row g-2 search-bar" role="search" id="searchForm">
        <div class="col-10">
            <input type="search" class="form-control" placeholder="Поиск по вакансиям..." aria-label="Search" name="search">
        </div>
        <div class="col-2">
            <button class="btn btn-primary w-100" type="submit">Найти</button>
        </div>
    </form>

    <h1 class="vacancies-title">Актуальные вакансии</h1>

    <div class="row">
        <!-- Объявления -->
        <div id="adContainer" class="col-md-9">
            <!-- Здесь будут ваши объявления -->
        </div>

        <!-- Фильтры -->
        <div class="col-md-3 filters-fixed">
            <h5>Фильтры</h5>
            <form id="filterForm" name="filterForm">
                <div class="mb-3">
                    <label for="category" class="form-label">Категория</label>
                    <select class="form-select" id="category" name="category">
                        <option value="0">Все</option>
                        <c:forEach var="category" items="${jobCategories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="minNumber" class="form-label">Оплата</label>
                    <input type="number" id="minNumber" class="form-control" placeholder="Минимальная оплата" name="minPayment">
                    <input type="number" id="maxNumber" class="form-control mt-2" placeholder="Максимальная оплата" name="maxPayment">
                </div>
                <button class="btn btn-primary" type="submit">Применить фильтры</button>
            </form>
        </div>
    </div>

    <div id="loading" style="display:none;">Загрузка...</div>
</div>


<%@include file="/WEB-INF/view/parts/footer.jsp" %>


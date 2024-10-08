<%@ page import="itis.inf304.taskhunter.entities.Job" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<style>
    .card-custom {
        border-radius: 25px;
        padding: 20px;
    }
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
</style>

<div class="container mt-4">
    <form class="row g-2 search-bar" role="search">
        <div class="col-10">
            <input type="search" class="form-control" placeholder="Поиск по вакансиям..." aria-label="Search" name="search">
        </div>
        <div class="col-2">
            <button class="btn btn-primary w-100" type="submit">Найти</button>
        </div>
    </form>

    <h1 class="vacancies-title">Актуальные вакансии</h1>

    <div class="row mb-4">
        <div class="col-md-3">
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
                    <label for="payment" class="form-label">Оплата</label>
                    <input type="number" class="form-control" placeholder="Минимальная оплата" name="minPayment">
                    <input type="number" class="form-control mt-2" placeholder="Максимальная оплата" name="maxPayment">
                </div>
                <button class="btn btn-primary" type="submit">Применить фильтры</button>
            </form>
        </div>

        <div id="adContainer" class="col-md-9">
            <!-- Здесь будут появляться объявления -->
        </div>

        <div id="loading" style="display: none;">Загрузка...</div>

        <script>
            let offset = 0;   // Смещение для загрузки следующих объявлений
            const limit = 10;  // Сколько объявлений загружать за раз

            // Функция для подгрузки объявлений
            function loadAds() {
                const loadingElement = document.getElementById('loading');
                loadingElement.style.display = 'block';

                fetch(`http://localhost:8080/home/api/jobs/?offset=${offset}&limit=${limit}`, {method: "POST"})
                    .then(response => response.json())
                    .then(data => {
                        const adContainer = document.getElementById('adContainer');

                        // Для каждого объявления создаем HTML и добавляем на страницу
                        data.forEach(job => {
                            const adElement = document.createElement('div');
                            adElement.classList.add('job');
                            adElement.innerHTML = `
                            <div class="row">
                                    <div class="col-12 mb-3">
                                        <div class="card card-custom shadow-sm">
                                            <div class="card-body">
                                                <h5 class="card-title">${job.title}</h5>
                                                <h6 class="card-subtitle mb-2 text-muted">${job.postDate}</h6>
                                                <p class="card-text">${job.description}</p>
                                                <p class="card-text"><strong>Оплата: ${job.payment} руб.</strong></p>
                                                <a href="#" class="btn btn-primary">Откликнуться</a>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            `;
                            adContainer.appendChild(adElement);
                        });

                        offset += limit;  // Увеличиваем смещение для следующего запроса
                        loadingElement.style.display = 'none';
                    })
                    .catch(error => {
                        console.error('Ошибка при загрузке объявлений:', error);
                        loadingElement.style.display = 'none';
                    });
            }

            // Функция для отслеживания прокрутки
            window.onscroll = function() {
                // Если пользователь прокрутил до конца страницы
                if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
                    loadAds();  // Загружаем следующую порцию объявлений
                }
            };

            // Загрузим первые объявления при загрузке страницы
            loadAds();
        </script>

        <div class="col-md-9">

        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>

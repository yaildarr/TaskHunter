<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<div class="bg-light py-5 text-center">
    <div class="container">
        <h1 class="display-4 fw-bold">О компании Taskhunter</h1>
        <p class="lead text-muted mt-3">
            Taskhunter — современная платформа, которая помогает найти исполнителей для подработок
            или быстро найти временную занятость. Мы делаем процесс поиска простым, быстрым и надежным.
        </p>
    </div>
</div>

<div class="py-5">
    <div class="container">
        <h2 class="text-center fw-bold mb-5">Почему выбирают нас</h2>
        <div class="row text-center g-4">
            <div class="col-md-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <i class="bi bi-lightning-fill display-4 text-primary"></i>
                        <h5 class="card-title mt-3">Скорость</h5>
                        <p class="card-text">
                            Мгновенный поиск исполнителей или вакансий, чтобы сэкономить ваше время.
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <i class="bi bi-people-fill display-4 text-primary"></i>
                        <h5 class="card-title mt-3">Надежность</h5>
                        <p class="card-text">
                            Только проверенные исполнители и заказчики, которым можно доверять.
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <i class="bi bi-shield-lock-fill display-4 text-primary"></i>
                        <h5 class="card-title mt-3">Безопасность</h5>
                        <p class="card-text">
                            Мы защищаем ваши данные и обеспечиваем безопасность каждой сделки.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="bg-light py-5">
    <div class="container">
        <h2 class="text-center fw-bold mb-5">Наша команда</h2>
        <div class="row justify-content-center text-center g-4">
            <div class="col-md-4">
                <div class="card h-100 shadow-sm">
                    <img src="https://i.ibb.co/6nsGnyL/fe284c43-0ac7-427a-846c-73ba50d08d4a.jpg" class="card-img-top rounded-circle mx-auto mt-3" alt="Team Member" style="width: 150px; height: 150px;">
                    <div class="card-body">
                        <h5 class="card-title">Харисов Ильдар</h5>
                        <p class="card-text">
                            CEO & Основатель, CTO, Разработчик, Маркетолог, HR, Product Manager, Project Manager, Плейбой, Бизнесмен
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="py-5 text-center">
    <div class="container">
        <h2 class="fw-bold mb-3">Присоединяйтесь к Taskhunter</h2>
        <p class="lead text-muted">Давайте вместе сделаем процесс поиска подработок простым и удобным.</p>
        <a href="<c:url value='/register' />" class="btn btn-primary btn-lg mt-3">Создать аккаунт</a>
    </div>
</div>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>

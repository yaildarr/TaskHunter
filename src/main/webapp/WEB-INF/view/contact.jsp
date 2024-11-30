<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<div class="container py-5">
    <div class="text-center mb-5">
        <h2 class="display-4">Контакты</h2>
        <p class="lead">Мы всегда рады помочь! Свяжитесь с нами, и мы постараемся ответить как можно быстрее.</p>
    </div>

    <div class="row">
        <div class="col-md-6 mb-4">
            <h4 class="mb-3">Наши контактные данные</h4>
            <ul class="list-unstyled">
                <li><strong>Адрес:</strong> Казань, Кремлевская, 35</li>
                <li><strong>Телефон:</strong> +7 (927) 494-00-20</li>
                <li><strong>Email:</strong> ildarkharisov23@gmail.com</li>
                <li><strong>Время работы:</strong> Пн-Пт: 9:00 - 18:00</li>
            </ul>

            <div class="my-4">
                <h4>Наши соцсети</h4>
                <a href="https://t.me/wokeupl1keth1s" class="btn" style="background-color: #0088cc; color: white; border: none; padding: 10px 20px;"><i class="bi bi-telegram"></i> Telegram</a>
                <a href="https://t.me/wokeupl1keth1s" class="btn" style="background-color: #25d366; color: white; border: none; padding: 10px 20px;"><i class="bi bi-whatsapp"></i> WhatsApp</a>
            </div>
        </div>

        <div class="col-md-6">
            <h4 class="mb-3">Напишите нам</h4>
            <form action="<c:url value='/send-message' />" method="POST">
                <div class="mb-3">
                    <label for="name" class="form-label">Ваше имя</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Ваш email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="message" class="form-label">Ваше сообщение</label>
                    <textarea class="form-control" id="message" name="message" rows="5" required></textarea>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary px-5 py-2">Отправить</button>
                </div>
            </form>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/view/parts/footer.jsp" %>

<!-- Подключение иконок Bootstrap (если необходимо) -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">


<%@include file="/WEB-INF/view/parts/footer.jsp" %>
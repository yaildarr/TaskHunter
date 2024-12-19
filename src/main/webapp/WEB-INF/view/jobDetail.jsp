<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-7">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h1 class="card-title mb-3">${job.title}</h1>
                    <hr class="border-black">
                    <div class="d-flex justify-content-between mb-4">
                        <div class="text-muted">
                            <i class="bi bi-geo-alt"></i> ${job.location} <br>
                            <i class="bi bi-folder"></i> ${jobCategory.name} <br>
                            <i class="bi bi-calendar"></i> Опубликовано: ${job.createdAt}
                        </div>
                    </div>

                    <hr class="border-black">
                    <p class="card-text">${job.description}</p>
                    <hr class="border-black">
                </div>
            </div>
        </div>

        <div class="col-md-3 d-flex flex-column">
            <div class="h3 title mb-3">${job.payment} руб./час</div>

            <!-- Блок с аватаркой и именем автора -->
            <div class="d-flex align-items-center mb-3 p-3" style="border: 1px solid #ddd; border-radius: 10px; background-color: #f9f9f9;">
                <a href="<c:url value = '/profile?id=${job.userId}'/>" class="me-3">
                    <img src="${userForJobDto.photoURL}" alt="Фото профиля" class="img-fluid rounded-circle" style="width: 80px; height: 80px; border: 4px solid #fff; box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.1);">
                </a>
                <a href="<c:url value = '/profile?id=${job.userId}'/>" class="fw-bold text-decoration-none" style="font-size: 1.25rem; color: #333;">
                    ${userForJobDto.username}
                </a>
            </div>

            <div class="alert text-center mb-3" role="alert" style="background: linear-gradient(145deg, #e3f7f0, #ccefe4); color: #0b6e4f; font-size: 1.2rem; font-weight: 500; border: 1px solid #ccefe4; border-radius: 10px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
                <c:choose>
                    <c:when test="${respondCount == 0}">
                        <i class="bi bi-star-fill" style="color: #f9a825; margin-right: 0.5rem;"></i>
                        <strong>Откликнитесь первым!</strong>
                    </c:when>
                    <c:otherwise>
                        <i class="bi bi-people-fill" style="color: #0b6e4f; margin-right: 0.5rem;"></i>
                        <strong>Откликнулись уже ${respondCount} раз(а).</strong>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Условие для проверки авторизации пользователя -->
            <c:choose>
                <c:when test="${empty user}">
                    <!-- Если пользователь не авторизован, кнопка ведет на страницу авторизации -->
                    <a href="<c:url value='/login' />" class="btn btn-primary btn-lg w-100">Войдите, чтобы откликнуться</a>
                </c:when>
                <c:otherwise>
                    <!-- Если пользователь авторизован, отображается стандартная кнопка отклика -->
                    <button class="btn btn-primary btn-lg w-100" data-bs-toggle="modal" data-bs-target="#RespondModal" onclick="sendResponse(${job.id})">Откликнуться на подработку</button>
                </c:otherwise>
            </c:choose>

            <div class="flex-grow-1"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="RespondModal" tabindex="-1" aria-labelledby="RespondModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-4">
            <div class="modal-header">
                <h3 class="modal-title" id="RespondModalLabel">Откликнуться</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
            <h4 class="modal-body">Номер телефона: ${userForJobDto.number}</h4>
            <div class="alert alert-warning mx-3 my-1" role="alert">
                <strong>Внимание!</strong> Будьте осторожны при взаимодействии с потенциальными работодателями. В случае сомнений всегда проверяйте информацию и не передавайте личные данные или деньги незнакомым людям. Мы не несем ответственности за действия третьих лиц.
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/respondJob.js"></script>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>




<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-lg rounded-5 border-0">
                <div class="card-body text-center p-4">
                    <img src="${user.photoURL}" alt="Фото профиля" class="img-fluid rounded-circle mb-4" style="width: 150px; height: 150px; border: 4px solid #fff; box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.1);">


                    <h2 class="text-dark fw-bold mb-2" style="font-size: 2rem;">${user.username}</h2>

                    <div class="text-start mb-4">
                        <p style="font-size: 1.1rem; color: #555;">
                            ${user.description}
                        </p>
                    </div>

                    <button class="btn btn-outline-secondary mb-3" data-bs-toggle="modal" data-bs-target="#editProfileModal">Редактировать профиль</button>
                </div>
            </div>

            <div class="d-flex justify-content-between mt-4">
                <a href="/my-listings" class="btn btn-light px-5 py-2">Мои объявления</a>
                <a href="/settings" class="btn btn-light px-5 py-2">Настройки</a>
                <a href="/logout" class="btn btn-danger px-5 py-2">Выйти</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-4">
            <div class="modal-header">
                <h5 class="modal-title" id="editProfileModalLabel">Редактировать профиль</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
                <form action="<c:url value='/profile' />" method="POST">
                    <div class="mb-3">
                        <label for="username" class="form-label">Имя</label>
                        <input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                    </div>
                    <div class="mb-3">
                        <label for="number" class="form-label">Телефон</label>
                        <input type="tel" class="form-control" id="number" name="number" value="${user.number}">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Описание</label>
                        <textarea class="form-control" id="description" name="description" rows="5" >${user.description}</textarea>
                    </div>
                    <div class="mb-3">
                        <input type="hidden" id="photoUrl" name="photoUrl" value="${user.photoURL}">
                        <label for="avatar" class="form-label">Загрузить новое фото</label>
                        <input type="file" class="form-control" id="avatar" name="avatar" accept="image/*">
                        <img id="profileImage" src="${user.photoURL}" alt="Фото профиля" class="img-fluid rounded-circle mb-4" style="width: 150px; height: 150px; border: 4px solid #fff; box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.1);">
                        <div id="loadingSpinner" class="spinner-border text-primary" role="status" style="display: none;">
                            <img id="loading-image" src=""<c:url value='/gif/loading-gif.gif'/>"" alt="Loading..." />
                        </div>

                    </div>
                    <div class="d-flex justify-content-center gap-4 mt-4">
                        <button type="submit" class="btn btn-primary px-5 py-2 text-uppercase">Сохранить изменения</button>
                        <button type="button" class="btn btn-outline-secondary px-5 py-2 text-uppercase" data-bs-dismiss="modal">Отменить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadImg.js"></script>


<%@include file="/WEB-INF/view/parts/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>


<style>
    .profile-photo {
        width: 150px;
        height: 150px;
        border: 4px solid #fff;
        box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.1);
    }

    .job-card {
        transition: transform 0.2s ease-in-out;
        cursor: pointer;
        position: relative;
    }

    .job-card:hover {
        transform: scale(1.03);
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        .delete-btn{
            display: block;
        }
    }
    .job-card:hover .delete-btn {
        display: block;
        opacity: 1;
    }

    .delete-btn {
        display: none;
        position: absolute;
        opacity: 0;
        top: 10px;
        right: 10px;
        transition: opacity 0.3s ease;
    }

</style>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-5 mb-4">
            <div class="card shadow-lg rounded-5 border-0">
                <div class="card-body text-center p-4">
                    <img src="${user.photoURL}" alt="Фото профиля" class="img-fluid rounded-circle mb-4 profile-photo">
                    <h2 class="text-dark fw-bold mb-2">${user.username}</h2>
                    <p class="text-muted">${user.description}</p>
                    <button class="btn btn-outline-secondary mb-3" data-bs-toggle="modal" data-bs-target="#editProfileModal">Редактировать профиль</button>
                </div>
            </div>
            <div class="d-flex justify-content-between mt-4">
                <button class="btn btn-light px-4 py-2" data-bs-toggle="modal" data-bs-target="#settingsModal">Настройки</button>
                <a href="/logout" class="btn btn-danger px-4 py-2">Выйти</a>
            </div>
        </div>

        <div class="col-lg-8 col-md-7">
            <h3 class="text-center mb-4 fw-bold">Ваши активные объявления</h3>
            <div class="row gy-3">
                <c:forEach var="job" items="${jobs}">
                    <div class="col-12">
                        <div class="card shadow-sm job-card">
                            <div class="card-body position-relative">
                                <h5 class="card-title">
                                    <a href="/home/jobs/job?id=${job.id}" class="job-link text-decoration-none text-dark fw-bold" data-job-id="${job.id}">${job.title}</a>
                                </h5>
                                <h6 class="card-subtitle mb-2 text-muted">${job.createdAt}</h6>
                                <p class="card-text"><strong>Оплата: ${job.payment} руб.</strong></p>
                                <button class="btn btn-danger btn-sm delete-btn position-absolute" style="top: 10px; right: 10px;" data-job-id="${job.id}" onclick="deleteJob('${job.id}')">Удалить</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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
                    <textarea class="form-control" id="description" name="description" rows="5">${user.description}</textarea>
                </div>
                <div class="mb-3">
                    <input type="hidden" id="photoUrl" name="photoUrl" value="${user.photoURL}">
                    <label for="avatar" class="form-label">Загрузить новое фото</label>
                    <input type="file" class="form-control" id="avatar" name="avatar" accept="image/*">
                    <img id="profileImage" src="${user.photoURL}" alt="Фото профиля" class="img-fluid rounded-circle mb-4 mt-3" style="width: 150px; height: 150px; border: 4px solid #fff; box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.1);">
                    <div id="uploadMessage" class="text-success mt-2" style="display: none;">
                        Фотография успешно загружена!
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

<div class="modal fade" id="settingsModal" tabindex="-1" aria-labelledby="settingsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-4">
            <div class="modal-header">
                <h5 class="modal-title" id="settingsModalLabel">Настройки</h5>
                <button type="button" id="close" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="currentPassword" class="form-label">Текущий пароль</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>
                <div class="mb-3">
                    <label for="newPassword" class="form-label">Новый пароль</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Подтвердите новый пароль</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>
                <p id="errorPassword" class="text-danger mt-3" ></p>
                <div class="d-flex justify-content-center gap-4 mt-4">
                    <button class="btn btn-primary px-5 py-2 text-uppercase" onclick="changePassword()">Сохранить</button>
                    <button class="btn btn-outline-secondary px-5 py-2 text-uppercase" data-bs-dismiss="modal">Отменить</button>
                </div>
                <div class="mt-4 text-center">
                    <button class="btn btn-danger text-uppercase" data-bs-toggle="modal" data-bs-target="#deleteAccountModal">Удалить аккаунт</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteAccountModal" tabindex="-1" aria-labelledby="deleteAccountModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-4">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteAccountModalLabel">Удаление аккаунта</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" onclick="closeModals()" aria-label="Закрыть"></button>
            </div>
            <div class="modal-body text-center">
                <p class="text-danger">Вы уверены, что хотите удалить свой аккаунт? Это действие нельзя отменить.</p>
                <form action="<c:url value='/deleteProfile' />" method="post">
                    <div class="d-flex justify-content-center gap-4">
                        <button type="submit" class="btn btn-danger px-5 py-2 text-uppercase">Удалить</button>
                        <button type="button" class="btn btn-outline-secondary px-5 py-2 text-uppercase" onclick="closeModals()" data-bs-dismiss="modal">Отмена</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/view/parts/footer.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadImg.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    function deleteJob(jobId) {
        if (confirm("Вы уверены, что хотите удалить это объявление?")) {
            $.ajax({
                url: '/home/deleteJob',
                type: 'POST',
                data: {
                    jobId: jobId
                },
                success: function(response) {
                    if (response.success) {
                        alert("Объявление успешно удалено.");
                        $('.delete-btn[data-job-id="' + jobId + '"]').closest('.card').remove();
                    } else {
                        alert("Произошла ошибка при удалении объявления.");
                    }
                },
                error: function() {
                    alert("Произошла ошибка при запросе.");
                }
            });
        }
    }
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax_scripts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modal.js"></script>


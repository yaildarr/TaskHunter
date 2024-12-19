<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-lg rounded-5 border-0">
                <div class="card-body text-center p-4">
                    <img src="${otherUser.photoURL}" alt="Фото профиля" class="img-fluid rounded-circle mb-4" style="width: 150px; height: 150px; border: 4px solid #fff; box-shadow: 0px 20px 30px rgba(0, 0, 0, 0.1);">
                    <h2 class="text-dark fw-bold mb-2" style="font-size: 2rem;">${otherUser.username}</h2>
                    <div class="text-start mb-4">
                        <p style="font-size: 1.1rem; color: #555;">
                            ${otherUser.description}
                        </p>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Список объявлений -->
<div class="container mt-5">
    <h3 class="text-center mb-4 fw-bold">Активные объявления</h3>
    <c:forEach var="job" items="${jobs}">
        <div class="row">
            <div class="col-12 mb-3">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="/home/jobs/job?id=${job.id}" class="job-link text-decoration-none text-dark fw-bold" data-job-id="${job.id}">${job.title}</a>
                        </h5>
                        <h6 class="card-subtitle mb-2 text-muted">${job.createdAt}</h6>
                        <p class="card-text"><strong>Оплата: ${job.payment} руб.</strong></p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>



<%@include file="/WEB-INF/view/parts/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

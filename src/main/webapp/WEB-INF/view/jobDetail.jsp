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
                    <div class="mt-4">
                        <p class="fw-bold">Автор: ${UserForJobDto.username}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-3 d-flex flex-column">
            <div class="d-flex flex-column">
                <div class="h3 title mb-3">${job.payment} руб./час</div>
                <button class="btn btn-primary btn-lg w-100 " data-bs-toggle="modal" data-bs-target="#RespondModal">Откликнуться на подработку</button>
            </div>
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
            <h4 class="modal-body">Номер телефона: ${UserForJobDto.number}</h4>
            <div class="alert alert-warning mx-3 my-1" role="alert">
                <strong>Внимание!</strong> Буыдьте осторожны при взаимодействии с потенциальными работодателями. В случае сомнений всегда проверяйте информацию и не передавайте личные данные или деньги незнакомым людям. Мы не несем ответственности за действия третьих лиц.
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/parts/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

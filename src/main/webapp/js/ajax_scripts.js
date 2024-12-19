function changePassword(){
    $.ajax({
        url: `http://localhost:8080/home/settings`,
        data: {
            currentPassword : $("#currentPassword").val(),
            newPassword : $("#newPassword").val(),
            confirmPassword : $("#confirmPassword").val(),
        },
        type: 'post',
        success: function (msg) {
            if (msg.hasOwnProperty('error')){
                $("#errorPassword").html(msg.error)
            } else {
                $('#close').click()
            }
        },
    })
}

function deleteJob(jobId) {
    // Подтверждаем удаление
    if (confirm("Вы уверены, что хотите удалить это объявление?")) {
        // Отправляем POST-запрос с помощью AJAX
        $.ajax({
            url: 'http://localhost:8080/home/deleteJob',  // Путь к вашему сервлету
            type: 'POST',            // Метод запроса
            data: {
                jobId: jobId         // Данные с ID объявления
            },
            success: function(response) {
                if (response.success) {
                    alert("Объявление успешно удалено.");
                    // Удаляем карточку объявления с UI
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

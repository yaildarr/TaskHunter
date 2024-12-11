document.getElementById("avatar").addEventListener("change", function (event) {
    const fileInput = event.target;
    const formData = new FormData();
    formData.append("file", fileInput.files[0]);  // Добавляем файл в formData

    // Показываем спиннер при начале загрузки
    document.getElementById("loadingSpinner").style.display = "inline-block";
    document.getElementById("profileImage").style.display = "none";  // Скрываем изображение во время загрузки

    fetch('http://localhost:8080/home/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            return response.json();  // Ожидаем JSON от сервера
        })
        .then(data => {
            if (data.fileUrl) {
                alert("Файл успешно загружен: " + data.fileUrl);
                // Обновляем изображение профиля после загрузки
                document.getElementById("profileImage").src = data.fileUrl;
            } else {
                alert("Ошибка загрузки файла.");
            }
        })
        .catch(error => {
            console.error("Ошибка при загрузке файла:", error);
            alert("Ошибка загрузки файла.");
        })
        .finally(() => {
            // Скрываем спиннер и показываем изображение
            document.getElementById("loadingSpinner").style.display = "none";
            document.getElementById("profileImage").style.display = "inline-block";
        });
});

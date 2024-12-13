document.getElementById("avatar").addEventListener("change", function (event) {
    const fileInput = event.target;
    const formData = new FormData();
    formData.append("file", fileInput.files[0]); // Добавляем файл в formData

    document.getElementById("loadingSpinner").style.display = "inline-block";
    document.getElementById("profileImage").style.display = "none";
    document.getElementById("uploadMessage").style.display = "none"; // Скрываем сообщение

    fetch('http://localhost:8080/home/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            return response.json(); // Ожидаем JSON от сервера
        })
        .then(data => {
            if (data.fileUrl) {

                document.getElementById("profileImage").src = data.fileUrl;
                document.getElementById("uploadMessage").textContent = "Фотография успешно загружена!";
                document.getElementById("uploadMessage").style.display = "block"; // Показываем сообщение
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

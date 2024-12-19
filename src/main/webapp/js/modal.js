document.addEventListener('click', (event) => {
    // Если нажата кнопка, которая вызывает модальное окно
    if (event.target.matches('[data-bs-toggle="modal"]')) {
        const targetModalId = event.target.getAttribute('data-bs-target');
        const targetModal = document.querySelector(targetModalId);

        if (targetModal) {
            // Убедимся, что модальное окно сброшено
            const modalInstance = bootstrap.Modal.getInstance(targetModal);
            if (modalInstance) {
                modalInstance.hide(); // Закрываем на случай несогласованного состояния
            }

            // Убираем все лишние backdrop
            const backdrops = document.querySelectorAll('.modal-backdrop');
            backdrops.forEach(backdrop => backdrop.remove());

            // Убираем лишний класс 'modal-open' у body
            document.body.classList.remove('modal-open');
            document.body.style = '';

            // Перезапускаем модальное окно
            const newModalInstance = new bootstrap.Modal(targetModal);
            newModalInstance.show();
        }
    }
});


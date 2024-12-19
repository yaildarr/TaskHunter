function sendResponse(jobId) {
    fetch('http://localhost:8080/home/respondJob', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ jobId: jobId })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка при отправке отклика');
            }
            console.log('Отклик успешно отправлен');
        })
        .catch(error => {
            console.error('Ошибка при отправке отклика:', error);
        });
}

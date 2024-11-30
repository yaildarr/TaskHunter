let offset = 0;
const limit = 10;

function loadAds() {
    const loadingElement = document.getElementById('loading');
    loadingElement.style.display = 'block';
    fetch(`http://localhost:8080/home/api/jobs?offset=${offset}&limit=${limit}`, {method: "POST"})
        .then(response => response.json())
        .then(data => {
            const adContainer = document.getElementById('adContainer');
            data.forEach(job => {
                const adElement = document.createElement('div');
                adElement.classList.add('job');
                adElement.innerHTML = `
                            <div class="row">
                                    <div class="col-12 mb-3">
                                        <div class="card card-custom shadow-sm">
                                            <div class="card-body">
                                                <h5 class="card-title">${job.title}</h5>
                                                <h6 class="card-subtitle mb-2 text-muted">${job.createdAt}</h6>
                                                <p class="card-text">${job.description}</p>
                                                <p class="card-text"><strong>Оплата: ${job.payment} руб.</strong></p>
                                                <a href="#" class="btn btn-primary">Откликнуться</a>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            `;
                adContainer.appendChild(adElement);
            });

            offset += limit;
            loadingElement.style.display = 'none';
        })
        .catch(error => {
            console.error('Ошибка при загрузке объявлений:', error);
            loadingElement.style.display = 'none';
        });
}

window.onscroll = function() {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        loadAds();
    }
};

document.addEventListener('DOMContentLoaded', () => {
    loadAds();
});

loadAds();
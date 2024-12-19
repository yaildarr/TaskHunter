let offset = 0;
const limit = 10;

function loadAds() {
    const loadingElement = document.getElementById('loading');
    loadingElement.style.display = 'block';

    const filterForm = document.getElementById('filterForm');
    const searchForm = document.getElementById('searchForm');
    const cityForm = document.getElementById('cityForm');

    const formData = new FormData();

    // Используем параметр category из URL, если он есть
    const categoryFromUrl = getUrlParameter('category');
    if (categoryFromUrl) {
        // Если в URL есть параметр category, то он будет использоваться в фильтре
        const categoryInput = filterForm.querySelector('[name="category"]');
        if (categoryInput) {
            categoryInput.value = categoryFromUrl; // Заменяем значение в форме
        }
    }

    // Добавляем параметры из фильтров
    if (filterForm) {
        const filterData = new FormData(filterForm);
        for (let [key, value] of filterData) {
            formData.append(key, value);
        }
    }

    // Добавляем параметры из фильтров
    if (cityForm) {
        console.log(cityForm)
        const cityData = new FormData(cityForm);
        for (let [key, value] of cityData) {
            formData.append(key, value);
        }
    }


    // Добавляем параметры из формы поиска
    if (searchForm) {
        const searchData = new FormData(searchForm);
        for (let [key, value] of searchData) {
            formData.append(key, value);
        }
    }

    // Добавляем параметры пагинации
    formData.append('offset', offset);
    formData.append('limit', limit);

    // Преобразуем параметры в строку query
    const queryParams = new URLSearchParams(formData).toString();

    // Отправка запроса с параметрами
    fetch(`http://localhost:8080/home/jobs?${queryParams}`, { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            const adContainer = document.getElementById('adContainer');
            if (data.length === 0 && offset === 0) {
                adContainer.innerHTML = '<p>Объявления не найдены.</p>';
            }

            data.forEach(job => {
                const adElement = document.createElement('div');
                adElement.classList.add('job');
                adElement.innerHTML = `
                    <div class="row">
                        <div class="col-12 mb-3">
                            <div class="card card-custom shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a href="/home/jobs/job?id=${job.id}" class="job-link text-decoration-none card-title" data-job-id="${job.id}">${job.title}</a>
                                    </h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${job.createdAt}</h6>
                                    <p class="card-text"><strong>Оплата: ${job.payment} руб.</strong></p>
                                    <a href="/home/jobs/job?id=${job.id}" class="btn btn-primary">Откликнуться</a>
                                </div>
                            </div>
                        </div>
                    </div>`;
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

// Функция для получения параметра из URL
function getUrlParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.getElementById('searchForm');
    const filterForm = document.getElementById('filterForm');
    const cityForm = document.getElementById('cityForm');

    // При отправке формы поиска
    searchForm.addEventListener('submit', (event) => {
        event.preventDefault();
        offset = 0; // Сбрасываем offset при новом запросе
        const adContainer = document.getElementById('adContainer');
        adContainer.innerHTML = ''; // Очищаем контейнер с объявлениями
        loadAds(); // Загружаем объявления с новыми параметрами
    });

    // При отправке формы фильтрации
    filterForm.addEventListener('submit', (event) => {
        event.preventDefault();
        offset = 0;
        const adContainer = document.getElementById('adContainer');
        adContainer.innerHTML = '';
        loadAds();
    });

    cityForm.addEventListener('submit', (event) =>{
        event.preventDefault();
        offset = 0;
        const adContainer = document.getElementById('adContainer');
        const formData = new FormData(cityForm);
        const city =formData.get('city');
        adContainer.innerHTML = '';
        loadAds();

        const cityLabel = document.getElementById('cityLabel');
        cityLabel.textContent = city || 'Выберите город';

        const cityModal = bootstrap.Modal.getInstance(document.querySelector('#cityModal'));
        if (cityModal) {
            cityModal.hide();
        }
    })


    // Загружаем объявления при загрузке страницы
    loadAds();
});

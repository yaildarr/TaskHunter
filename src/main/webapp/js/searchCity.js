
function loadCity(input,sug) {
    var query = $('#'+input).val();

    if (query.length > 2) {  // Начинаем поиск, когда введены 3 и более символов
        $.ajax({
            url: 'http://localhost:8080/home/searchCity',
            method: 'GET',
            data: { query: query },
            success: function(data) {

                if (Array.isArray(data)) {
                    $('#'+sug).empty().show(); // Показываем dropdown
                    data.forEach(function(city) {
                        $('#'+sug).append('<a id="'+city.name+'" class="dropdown-item" href="#" onclick="clickItem(\'' + input + '\', \'' + sug + '\',\'' + city.name + '\')">' + city.name + '</a>');
                    });
                } else {
                    console.error('Ответ от сервера не является массивом:', data);
                }
            }
        });
    } else {
        $('#'+sug).removeClass('show'); // Скрываем dropdown
    }
}

function clickItem(input,sug,cityid){
    $('#'+input).val($('#'+cityid).html());
    $('#'+sug).hide(); // Скрываем список предложений
}
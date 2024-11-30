<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/view/parts/header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h3 class="text-center">Чат на WebSocket</h3>
            <div class="card">
                <div class="card-body">
                    <div id="chat-box" class="border rounded p-3 mb-3" style="height: 300px; overflow-y: scroll;">
                    </div>
                    <div class="input-group">
                        <input id="message-input" type="text" class="form-control" placeholder="Введите сообщение">
                        <button id="send-button" class="btn btn-primary">Отправить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    let socket = new WebSocket("ws://localhost:8080/home/api/chat");

    const chatBox = document.getElementById("chat-box");
    const messageInput = document.getElementById("message-input");
    const sendButton = document.getElementById("send-button");

    socket.onopen = function() {
        console.log("Соединение установлено");
    };

    socket.onmessage = function(event) {
        const message = document.createElement("div");
        message.textContent = event.data;
        chatBox.appendChild(message);
        chatBox.scrollTop = chatBox.scrollHeight;
    };

    socket.onclose = function(event) {
        console.log("Соединение закрыто");
    };

    socket.onerror = function(error) {
        console.error("Ошибка: " + error.message);
    };

    sendButton.onclick = function() {
        const message = messageInput.value;
        if (message) {
            socket.send(message);
            messageInput.value = "";
        }
    };

    messageInput.addEventListener("keyup", function(event) {
        if (event.key === "Enter") {
            sendButton.click();
        }
    });
</script>


<%@include file="/WEB-INF/view/parts/footer.jsp" %>
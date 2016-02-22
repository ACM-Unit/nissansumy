<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside>
    <ul class="dropdown">
        <li  class="dropdown-top">
            <a class="dropdown-top" width="200px" href="${CONTEXT}/qashqai.jsp">NISSAN QASHQAI</a>
            <ul class="dropdown-inside">
                <li><a href="${CONTEXT}/qashqaiView">Обзор модели</a></li>
                <li><a href="${CONTEXT}/qashqaiSelectColor">Выбор цвета</a></li>
                <li><a href="${CONTEXT}/pdf/QQ_New.pdf" download>Скачать прайс</a></li>
                <li><a href="${CONTEXT}/pdf/Qashqai.pdf" download>Скачать брошюру</a></li>
            </ul>
        </li>
    </ul>
</aside>
<div class="model-content">
    <img src="${CONTEXT}/index_files/da5d9395dee81eff7d3095a0c6c91aae.jpg" width="100%">
    <div class="text">
        <div class="btext">
            <div class="wtext">
                Сочетая в себе лучшие характеристики внедорожника и традиционного хэтчбэка, этот кроссовер олицетворяет собой бескомромиссное качество и совершенство. Узнайте больше о кроссовере <b>Nissan Qashqai</b>.			</div>
        </div>
        <span class="price">от ${qashqai} грн.</span>
    </div>
</div>
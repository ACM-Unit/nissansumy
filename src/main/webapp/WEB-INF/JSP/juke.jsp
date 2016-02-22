<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside>
    <ul class="dropdown">
        <li  class="dropdown-top">
            <a class="dropdown-top" width="200px" href="${CONTEXT}/juke.jsp">ОБНОВЛЕННЫЙ NISSAN JUKE</a>
            <ul class="dropdown-inside">
                <li><a href="${CONTEXT}/jukeView">Обзор модели</a></li>
                <li><a href="${CONTEXT}/jukeSelectColor">Выбор цвета</a></li>
                <li><a href="${CONTEXT}/pdf/Juke_MC.pdf" download>Скачать прайс</a></li>
                <li><a href="${CONTEXT}/pdf/Juke.pdf" download>Скачать брошюру</a></li>
            </ul>
        </li>
    </ul>
</aside>

        <div class="model-content">
            <img src="${CONTEXT}/index_files/new-juke2.jpg" width="100%">
            <div class="text">
                <div class="btext">
                    <div class="wtext">

                        <div align="justify"><font color="#FFFFFF"><span style="font-size: 10pt; font-family: Verdana,sans-serif;"></span>Обновление <b>Nissan Juke</b> коснулось внешнего вида автомобиля: в дизайне передней и задней частей усилены премиальные и спортивные черты, также теперь предлагается программа заводской персонализации автомобиля. </font></div>
                    </div>
                </div>
                <span class="price">от ${juke} грн.</span>
            </div>
        </div>

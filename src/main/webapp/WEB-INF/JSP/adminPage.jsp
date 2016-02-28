<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <form action="/updatePrice" method="POST" style="text-align: center;">
        <input type="hidden" name="Button" value="nissan"/>
       <input name="submit" class="button" value="Обновить цены с сайта nissan.ua" type="submit">
    </form>
<article>
    <h1>Установить цены на модельный ряд автомобилей Nissan (внедорожники, кроссоверы и легковые Ниссан) у официального дилера Авто плюс в Сумах.</h1>
    <ul class="breadcrumb-navigation"><li><a href="${CONTEXT}/home.jsp" title="Главная">Главная</a></li><li>&nbsp;/&nbsp;</li><li><span>Автомобили</span></li></ul>
    <div class="model-list">
        <form action="/updatePrice" method="POST" style="text-align: center;">
        <input type="hidden" name="Button" value="text"/>
        <ul class="car-list">
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/cb64cc104a5538518bb194eebe60d828.png" alt="NISSAN MICRA" height="90" width="auto">
                    <input name="micra" value="${micra}" type="text" width="90px">
                <p>NISSAN MICRA</p>
            </li>
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/sentra-spisok.jpg" alt="NISSAN SENTRA" height="90" width="auto">
                    <input name="sentra" value="${sentra}" type="text" width="90px">
                    <p>NISSAN SENTRA</p>
            </li>
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/new-juke-4.png" alt="ОБНОВЛЕННЫЙ NISSAN JUKE" height="90" width="auto">
                    <input name="juke" value="${juke}" type="text" width="90px">
                <p>ОБНОВЛЕННЫЙ NISSAN JUKE</p>
            </li>
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/bd837d25d74f40a3a261af1993263ea2.jpg" alt="NISSAN QASHQAI" height="90" width="auto">
                    <input name="qashqai" value="${qashqai}" type="text" width="90px">
                <p>NISSAN QASHQAI</p>
            </li>
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/cd1fc188db91a3d145e7a584662f5b56.png" alt="NISSAN MURANO" height="90" width="auto">
                    <input name="murano" value="${murano}" type="text" width="90px">
                <p>NISSAN MURANO</p>
            </li>
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/patrol_big.jpg" alt="NISSAN Patrol " height="90" width="auto">
                    <input name="patrol" value="${patrol}" type="text" width="90px">
                <p>NISSAN PATROL</p>
            </li>
            <li style="padding: 20px 0 20px 0;">
                    <img src="${CONTEXT}/index_files/l2.jpg" alt="NISSAN X-TRAIL" height="90" width="auto">
                    <input name="xtrail" value="${xtrail}" type="text"  width="90px">
                <p>NISSAN X-TRAIL</p>
            </li>
        </ul>

        <input name="submit" class="button" value="Обновить цены" type="submit" >
            </form>
    </div></article>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="PageOneNuze">
    <div class="container">
        <div id="TitleOneNuze">${news.title}</div>
        <div id="ImgOneNuze"><img src="${CONTEXT_URL_TO_SOURCE}/resources/images/news/${news.image}"></div>
        <div id="DeskrNuze">
            <p id="Date">${news.date}</p>
            <c:forEach var="newsBlokString" items="${news.newsBlok}">
                <c:choose>
                    <c:when test="${newsBlokString.hasItem}">
                        <ul><p class="TitleItems">${newsBlokString.text}</p>
                            <c:forEach var="newsBlokItem" items="${newsBlokString.items}">
                                <li>${newsBlokItem.item}</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <p>${newsBlokString.text}</p>
                    </c:otherwise>
                </c:choose>
                <div id="FotoOfficeFooter">
                    <jsp:include page="/WEB-INF/JSP/partOfPage/photoOfPages.jsp" flush="true"/>
                </div>
            </c:forEach>
        </div>
        <div class="clr"></div>
    </div>
</div>


<%--<div>
    <div>
        <c:forEach var="photo" items="${news.photos}">
            <div>*** ${photo.id} / ${photo.name} / ${photo.description}</div>
            <img src="${CONTEXT_URL_TO_SOURCE}/${photo.path}" name="${photo.name}" height="300"
                 width="300" title="${photo.description}">

        </c:forEach>

        <br/>
        <br/>

    </div>--%>

<div id="NewsBlock">
    <section>
        <div id="NewsBg">
            <div id="TitleBlock">Похожие новости:</div>
            <div id="ListNews">
                <c:forEach var="n" items="${recommendedNews}">
                    <div><a href="${CONTEXT}${selectedCity.cityUrl}news/${n.url}" class="content_block">
                        <div class="over_block"></div>
                        <span>${n.title}</span><img
                            src="${CONTEXT_URL_TO_SOURCE}/resources/images/news/${n.image}"/></a></div>
                </c:forEach>
            </div>
            <div class="Clr"></div>
        </div>
    </section>
</div>
<article>
    <div class="all-cources">
        <div class="container">
            <h1>Популярные курсы:</h1>
            <div class="row">
                <div class="courses">
                    <c:forEach items="${groups}" var="groupe">
                        <c:forEach items="${groupe.courses}" var="course">
                            <a href=""><div class="wrap-col-cources">
                                <div class="buss-eng">
                                    <div style="background:  url(${CONTEXT_URL_TO_SOURCE }/images/img_p2/${course.imageMain}.png) no-repeat center center;"></div>
                                    <h4>${course.topTitle}</h4>
                                    <div class="">
                                        <img src="${CONTEXT_URL_TO_SOURCE }/images/img_p2/${groupe.image}" alt="">
                                        <p>${course.centerTitle}</p>
                                        <p>${course.imageTimeTable}</p>
                                    </div>
                                </div>
                            </div></a>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</article>
<%--<jsp:include page="../templatePartOfPages/popularCoursesSlider.jsp" flush="true"/>--%>
</div>



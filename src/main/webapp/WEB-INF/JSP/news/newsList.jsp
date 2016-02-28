<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<article>
    <div class="allnews">
        <h1>Новости</h1>
    </div>
    <div id="AllNewsList">
        <section>
            <ul>
                <c:forEach var="news" items="${newsList}">
                    <li>
                        <img src="${CONTEXT_URL_TO_SOURCE}/${news.image}">

                        <div id="DescriptNews">
                            <p class="TitleNewsList">${news.title}</p>

                            <p class="DateNewsList">${news.date}</p>

                            <div id="mainDescription">
                                <c:forEach var="newsBlokString" items="${news.newsBlok}">
                                    <p class="DescrNewsList">${newsBlokString.text}</p><br>
                                    <c:forEach var="newsBlokItem" items="${newsBlokString.items}">
                                        <p class="ItemOfNews"> - ${newsBlokItem.item}</p>
                                    </c:forEach>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="ButtomNewsList"><a href="${CONTEXT}${selectedCity.cityUrl}news/${news.url}">Читать
                            дальше</a></div>
                        <div class="clr"></div>
                    </li>
                </c:forEach>
            </ul>
        </section>
    </div>

    <div id="NavNews">
        <section>
            <div id="NavigationNews">
                <ul>
                    <li><a href="${CONTEXT}${selectedCity.cityUrl}news?page=1" class="TxNews">первая</a></li>
                    <c:forEach begin="1" end="${countOfPages}" varStatus="loop">
                        <li><a href="${CONTEXT}${selectedCity.cityUrl}news?page=${loop.index}">${loop.index}</a></li>
                    </c:forEach>
                    <li><a href="${CONTEXT}${selectedCity.cityUrl}news?page=${countOfPages}" class="TxNews">последняя</a>
                    </li>
                    <div class="clr"></div>
                </ul>
            </div>
        </section>
    </div>
</article>


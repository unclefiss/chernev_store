<!DOCTYPE>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<c:if test="${not empty user}">
    <body>
    <%@ include file="/WEB-INF/jspf/script.jspf" %>
    <div class="site-wrap">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <c:if test="${not empty user}">
            <div class="bg-light py-3">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 mb-0"><a href="index.jsp"><fmt:message key="header.home"/></a> <span
                                class="mx-2 mb-0">/</span> <strong
                                class="text-black"><fmt:message key="cabinet.cabinet"/></strong></div>
                    </div>
                </div>
            </div>
            <div class="site-section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="h3 mb-3 text-black"><fmt:message key="cabinet.cabinet"/></h2>
                        </div>
                        <div class="col-md-7">
                            <div class="card">
                                <div class="row">
                                    <div class="col-md-4">
                                        <img alt="userpic" src="${user.userPicturePath}"
                                             style="width:200px; height:200px; object-fit:cover">
                                    </div>
                                    <div class="col-md-8 text-dark">
                                        <h2 class="my-2">${user.firstName}</h2>
                                        <h2 class="my-2">${user.lastName}</h2>
                                        <h5 class="my-2"><fmt:message key="cabinet.login"/>: @${user.login}</h5>
                                        <h5><fmt:message key="cabinet.phone"/>: ${user.phone}</h5>
                                        <h5><fmt:message key="cabinet.email"/>: ${user.email}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <a href="/ChernevOnlineStore/shop"
                               class="btn btn-outline-primary btn-sm btn-block mt-1"><fmt:message key="to_shop"/></a>
                            <a href="/ChernevOnlineStore/orders"
                               class="btn btn-outline-primary btn-sm btn-block"><fmt:message key="cabinet.orders"/></a>
                            <br><br>
                            <a href="/ChernevOnlineStore/logout"
                               class="btn btn-outline-primary btn-sm btn-block"><fmt:message key="cabinet.logout"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
    </body>
</c:if>
<c:if test="${empty user}">
    <%@ include file="login.jsp" %>
</c:if>
</html>
<!DOCTYPE>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/script.jspf" %>
<div class="site-wrap">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="bg-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-0"><a href="index.html"><fmt:message key="header.home"/></a> <span
                        class="mx-2 mb-0">/</span> <strong class="text-black"><fmt:message key="my_orders"/></strong>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${not empty orders}">
    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <c:forEach var="order" items="${orders}">

                        <div class="card my-2 text-black border-primary">
                            <div class="row">
                                <div class="col-md-7">
                                    <div class="mx-3 my-3">
                                        <a class="h5 mb-3"
                                           href="/ChernevOnlineStore/order?orderId=${order.id}"><fmt:message
                                                key="order.order"/>#${order.id}</a>
                                        <br>
                                        <fmt:message key="order.date"/>: ${order.date}
                                        <ul>
                                            <c:forEach var="value" items="${order.orderedProducts.orderedProducts}">
                                                <li>${value.productCount} x ${value.product.title}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-4 mx-3">
                                    <a class="my-5 mr-5 px-4 py-4 btn btn-primary btn-block"
                                       href="/ChernevOnlineStore/order?orderId=${order.id}"><fmt:message
                                            key="order.show_info"/></a>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="row mb-5">
                        <div class="col-md-5">
                            <div class="row my-3">
                                <div class="col-md-12">
                                    <a href="/ChernevOnlineStore/shop" class="btn btn-outline-primary btn-sm btn-block"><fmt:message
                                            key="order.continue"/></a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</c:if>
<c:if test="${empty orders}">
    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <span class="icon-check_circle display-3 text-success"></span>
                    <h2 class="display-3 text-black">You don't have any orders yet!</h2>
                    <p><a href="/ChernevOnlineStore/shop" class="btn btn-sm btn-primary"><fmt:message
                            key="to_shop"/></a></p>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
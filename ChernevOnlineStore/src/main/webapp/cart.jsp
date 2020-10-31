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
                        class="mx-2 mb-0">/</span> <strong class="text-black"><fmt:message key="cart.cart"/></strong>
                </div>
            </div>
        </div>
    </div>
    <div class="site-section">
        <div class="container">
            <c:if test="${cart.count != 0}">
            <div class="row">
                <form class="col-md-12" method="post">
                    <div class="site-blocks-table border-primary">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="product-thumbnail"><fmt:message key="cart.table.image"/></th>
                                <th class="product-name"><fmt:message key="cart.table.product"/></th>
                                <th class="product-price"><fmt:message key="cart.table.price"/></th>
                                <th class="product-quantity"><fmt:message key="cart.table.quantity"/></th>
                                <th class="product-total"><fmt:message key="cart.table.total"/></th>
                                <th class="product-remove"><fmt:message key="cart.table.remove"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="entry" items="${cart.entry}">
                                <tr>
                                    <td class="product-thumbnail">
                                        <img src="${entry.key.imagePath}"
                                             style="width:100px; height:100px; object-fit:cover" alt="Image"
                                             class="img-fluid">
                                    </td>
                                    <td class="product-name">
                                        <h2 class="h5 text-black">${entry.key.title}</h2>
                                    </td>
                                    <td>$ ${entry.key.price}</td>
                                    <td width="100">
                                        <div class="input-group text-center mb-1">
                                            <div class="input-group-prepend">
                                                <input type="hidden" name="productId" value="${entry.key.id}">
                                                <button class="btn btn-outline-primary decCount" type="button">&minus;
                                                </button>
                                            </div>
                                            <input type="hidden" name="productId" value="${entry.key.id}">
                                            <input name="count" type="text" type="submit"
                                                   class="form-control text-center input-count" value="${entry.value}"
                                                   placeholder="" disabled>
                                            <div class="input-group-append">
                                                <input type="hidden" name="productId" value="${entry.key.id}">
                                                <button class="btn btn-outline-primary incCount" type="button">&plus;
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="total-price">$${entry.key.price * entry.value}</td>
                                    <td>
                                        <a href="/ChernevOnlineStore/cart?command=deleteProduct&productId=${entry.key.id}"
                                           class="btn btn-primary btn-sm">X</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div class="row mb-5">
                <div class="col-md-5">
                    <div class="row my-3">
                        <div class="col-md-6">
                            <a href="/ChernevOnlineStore/shop"
                               class="btn btn-outline-primary btn-sm btn-block"><fmt:message key="cart.continue"/></a>
                        </div>
                        <div class="col-md-6">
                            <a href="/ChernevOnlineStore/cart?command=clearCart&productId="
                               class="btn btn-primary btn-sm btn-block clearCart"><fmt:message key="cart.clear"/> <span
                                    class="icon icon-trash-o"></span></a>
                        </div>
                    </div>
                </div>
                <div class="col-md-7 card border-primary">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="col-md-12 mt-4">
                                <h4 class="text-black h5 text-uppercase"><fmt:message key="cart.totals"/>: </h4>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="row ">
                                <div class="col-md-2 mt-4">
                                    <span class="text-black"><fmt:message key="cart.total"/></span>
                                </div>
                                <div class="col-md-2 text-right mt-4">
                                    <strong class="text-black total">$${cart.totalPrice}</strong>
                                </div>
                                <div class="col-md-8 mt-3">
                                    <c:if test="${not empty user}">
                                        <button class="btn btn-primary btn-md btn-block"
                                                onclick="window.location='/ChernevOnlineStore/checkout.jsp'">
                                            <fmt:message key="cart.proceed"/></button>
                                    </c:if>
                                    <c:if test="${empty user}">
                                        <button class="btn btn-primary btn-md btn-block"
                                                onclick="window.location='/ChernevOnlineStore/login'"><fmt:message
                                                key="cart.proceed"/></button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${cart.count == 0}">
                        <div class="site-section">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-12 text-center">
                                        <span class="icon-check_circle display-3 text-success"></span>
                                        <h2 class="display-3 text-black"><fmt:message key="cart.havent_order"/></h2>
                                        <p><a href="/ChernevOnlineStore/shop"
                                              class="btn btn-sm btn-primary"><fmt:message key="to_shop"/></a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </div>
</body>
</html>
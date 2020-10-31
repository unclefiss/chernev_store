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
                <div class="col-md-12 mb-0"><a href="/ChernevOnlineStore/index.jsp"><fmt:message key="header.home"/></a>
                    <span class="mx-2 mb-0">/</span>
                    <div class="col-md-12 mb-0"><a href="/ChernevOnlineStore/cart.jsp"><fmt:message
                            key="cart.cart"/></a> <span class="mx-2 mb-0">/</span> <strong
                            class="text-black"><fmt:message key="checkout.checkout"/></strong></div>
                </div>
            </div>
        </div>
        <div class="site-section">
            <div class="container">
                <c:if test="${cart.count != 0}">
                    <div class="row">
                        <form class="col-md-12" method="post">
                            <div class="site-blocks-table">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="product-thumbnail"><fmt:message key="cart.table.image"/></th>
                                        <th class="product-name"><fmt:message key="cart.table.product"/></th>
                                        <th class="product-price"><fmt:message key="cart.table.price"/></th>
                                        <th class="product-quantity"><fmt:message key="cart.table.quantity"/></th>
                                        <th class="product-total"><fmt:message key="cart.table.total"/></th>
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
                                            <td width="50">
                                                <div class="input-group text-center mb-3">
                                                    <input type="hidden" name="productId" value="${entry.key.id}">
                                                    <input name="count" type="text" type="submit"
                                                           class="form-control text-center input-count"
                                                           value="${entry.value}" placeholder="" disabled>
                                                </div>
                                            </td>
                                            <td class="total-price">$${entry.key.price * entry.value}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                    <form class="js-form-validate" action="/ChernevOnlineStore/order" method="POST">
                        <div class="container card border-primary">
                            <div class="row">
                                <div class="col-md-6 ">
                                    <div class="form-group input-group my-2 ">
                                        <span class="input-group-text "> The address </span>
                                        <input name="address" class="form-control border-primary"
                                               placeholder="<fmt:message key="checkout.address" />" type="text"
                                               data-validate>
                                        <span class="some-form__hint"><fmt:message key="checkout.address"/></span>
                                    </div>
                                    <div class="card bg-primary my-2">
                                        <div class="form-group input-group my-2 px-2">
                                            <span class="input-group-text"><fmt:message
                                                    key="checkout.card_numbers"/> </span>
                                            <input name="card-numbers" class="form-control bg-light"
                                                   placeholder="<fmt:message key="checkout.card_numbers" />" type="text"
                                                   data-validate>
                                            <span class="some-form__hint px-2">Write your card numbers (1234-5678-1234-5678)</span>
                                        </div>
                                        <div class="row my-2 px-2">
                                            <div class="col-md-6 form-group input-group"><span class="input-group-text"><fmt:message
                                                    key="checkout.expired"/></span>
                                                <input name="password" class="form-control bg-light"
                                                       placeholder="<fmt:message key="checkout.expired" />" type="text"
                                                       data-validate>
                                                <span class="some-form__hint px-2">Write expiration date ('12/12')</span>
                                            </div>
                                            <div class="col-md-6 form-group input-group">
                                                <span class="input-group-text"> CVV</span>
                                                <input name="password" class="form-control bg-light" placeholder="CVV"
                                                       type="password"
                                                       data-validate>
                                                <span class="some-form__hint px-2">Write your CVV2 code</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 pl-5">
                                    <div class="row justify-content-end mr-3 my-2 ">
                                        <div class="col-md-7 card border-primary">
                                            <div class="col-md-12 text-center border-bottom mb-4 mt-2">
                                                <h3 class="text-black h4 text-uppercase"><fmt:message
                                                        key="cart.totals"/></h3>
                                            </div>
                                            <div class="row my-4">
                                                <div class="col-md-6 mb-5">
                                                    <strong class="text-black h5"><fmt:message
                                                            key="cart.total"/></strong>
                                                </div>
                                                <div class="col-md-6 text-right">
                                                    <strong class="text-black total h5">$${cart.totalPrice}</strong>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 my-4">
                                <a href="/ChernevOnlineStore/cart.jsp"
                                   class="btn btn-primary btn-lg py-3 btn-block"><fmt:message
                                        key="checkout.to_cart"/></a>
                            </div>
                            <div class="col-md-4"></div>
                            <div class="col-md-4 my-4">
                                <button class="btn btn-primary btn-lg py-3 btn-block" type="submit"><fmt:message
                                        key="checkout.order"/></button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>
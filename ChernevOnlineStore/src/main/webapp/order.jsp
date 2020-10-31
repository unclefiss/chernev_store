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
                    <span class="mx-2 mb-0">/</span> <strong class="text-black"><fmt:message
                            key="order.order"/></strong></div>
            </div>
        </div>
    </div>
    <div class="site-section">
        <div class="container">
            <div class="row">
                <form class="col-md-12" method="post">
                    <div class=" border-primary text-dark">
                        <h2 class="mb-3"><fmt:message key="order.order"/> #${order.id}</h2>
                        <h4><fmt:message key="order.status"/>: <ct:locale collection="${orderStatuses}"
                                                                          locale="${locale}"
                                                                          id="${order.status.id}"/></h4>
                        <p><fmt:message key="order.user"/>: ${order.user.firstName} ${order.user.lastName}</p>
                        <p><fmt:message key="order.date"/> : ${date}</p>
                        <table class="table table-bordered text-dark">
                            <thead class="text-black text-center">
                            <tr>
                                <th class="product-thumbnail"><fmt:message key="cart.table.image"/></th>
                                <th class="product-name"><fmt:message key="cart.table.product"/></th>
                                <th class="product-price"><fmt:message key="cart.table.price"/></th>
                                <th class="product-quantity"><fmt:message key="cart.table.quantity"/></th>
                                <th class="product-total"><fmt:message key="cart.table.total"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="entry" items="${order.orderedProducts.orderedProducts}">
                                <tr class="text-center">
                                    <td class="product-thumbnail">
                                        <img src="${entry.product.imagePath}"
                                             style="width:50px; height:50px; object-fit:cover" alt="Image"
                                             class="img-fluid">
                                    </td>
                                    <td class="product-name ">
                                        <h2 class="h5 text-black mt-2">${entry.product.title}</h2>
                                    </td>
                                    <td>$ ${entry.product.price}</td>
                                    <td>
                                       <span class="text-center mt-3">${entry.productCount}<span>
                                    </td>
                                    <td class="total-price mt-3">$${entry.product.price * entry.productCount}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
            <div class="row mb-5">
                <div class="col-md-8">
                    <div class="row my-3">
                        <div class="col-md-6">
                            <a href="/ChernevOnlineStore/shop"
                               class="btn btn-outline-primary btn-sm btn-block"><fmt:message key="order.continue"/></a>
                        </div>
                        <div class="col-md-6">
                            <a href="/ChernevOnlineStore/orders"
                               class="btn btn-outline-primary btn-sm btn-block"><fmt:message key="my_orders"/></a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 card border-primary">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="col-md-12 mt-4">
                                <h4 class="text-black h5 text-uppercase"><fmt:message key="cart.totals"/>: </h4>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row ">
                                <div class="col-md-6 mt-4">
                                    <span class="text-black h5"><fmt:message key="cart.total"/></span>
                                </div>
                                <div class="col-md-6 mt-4">
                                    <strong class="text-black total h5">$${order.totalPrice}</strong>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </div>
</body>
</html>
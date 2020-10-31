<!DOCTYPE>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/script.jspf" %>
<div class="site-wrap">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="" data-aos="fade-up">
        <div class="row mb-5 mx-3">
            <c:if test="${ empty noProducts}">
                <div class="col-md-9 order-2">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="float-md-left my-3">
                                <h2 class="text-black h5"><fmt:message key="shop.shop"/></h2>

                            </div>
                            <form name="topBar" action="/ChernevOnlineStore/shop">
                                <c:if test="${not empty filters.name}">
                                    <input type="hidden" name="name" value="${name}">
                                </c:if>
                                <c:if test="${not empty filters.categories}">
                                    <c:forEach var="value" items="${filters.categories}">
                                        <input type="hidden" name="category" value="${value}">
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty filters.from}">
                                    <input type="hidden" name="from" value="${from}">
                                </c:if>
                                <c:if test="${not empty filters.to}">
                                    <input type="hidden" name="to" value="${to}">
                                </c:if>
                                <c:if test="${not empty filters.brands}">
                                    <c:forEach var="value" items="${filters.brands}">
                                        <input type="hidden" name="brand" value="${value}">
                                    </c:forEach>
                                </c:if>
                                <input type="hidden" name="page" value="0">
                                <div class="d-flex">
                                    <div class="dropdown mr-1 my-1 ml-md-auto">
                                        <input type="number" name="byPage" value="${filters.byPage}"
                                               class="form-control" placeholder="Products by page">
                                    </div>
                                    <div class="dropdown mr-1 my-1">
                                        <select onChange="submit();" type="select" name="sortBy" class="form-control"
                                                value="${sortBy}">
                                            <option value="0"><fmt:message key="shop.sort_by"/></option>
                                            <option value="1"><fmt:message key="shop.sort_by.name_a_z"/></option>
                                            <option value="2"><fmt:message key="shop.sort_by.name_z_a"/></option>
                                            <option value="3"><fmt:message key="shop.sort_by.price_low"/></option>
                                            <option value="4"><fmt:message key="shop.sort_by.price_high"/></option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <form name="paginationTop" action="/ChernevOnlineStore/shop">
                        <c:if test="${not empty name}">
                            <input type="hidden" name="name" value="${name}">
                        </c:if>
                        <c:if test="${not empty category}">
                            <c:forEach var="value" items="${category}">
                                <input type="hidden" name="category" value="${value}">
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty from}">
                            <input type="hidden" name="from" value="${from}">
                        </c:if>
                        <c:if test="${not empty to}">
                            <input type="hidden" name="to" value="${to}">
                        </c:if>
                        <c:if test="${not empty brand}">
                            <c:forEach var="value" items="${brand}">
                                <input type="hidden" name="brand" value="${value}">
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty byPage}">
                            <input type="hidden" name="byPage" value="${byPage}">
                        </c:if>
                        <c:if test="${not empty sortBy}">
                            <input type="hidden" name="sortBy" value="${sortBy}">
                        </c:if>
                        <div class="row mb-2" data-aos="fade-up">
                            <div class="col-md-12 text-center">
                                <div class="site-block-27">
                                    <ul>
                                        <ct:pagination pages="${pages}" currentPage="${currentPage}"/>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="row mb-5">
                        <c:forEach var="product" items="${products}">
                            <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
                                <div class="block-4 text-center border">
                                    <figure class="block-4-image">
                                        <a href="shop-single.html"><img
                                                style="width:200px; height:200px; object-fit:cover"
                                                src="${product.imagePath}" alt="Image placeholder"
                                                class="img-fluid"></a>
                                    </figure>
                                    <div class="block-4-text p-4">
                                        <h3><a href="shop-single.html">${product.title}</a></h3>
                                        <p class="mb-0"><fmt:message key="shop.category"/>:
                                            <ct:locale collection="${categories}" locale="${locale}"
                                                       id="${product.categoryId}"/></p>

                                        <p class="mb-0"><fmt:message key="shop.brand"/>:
                                            <ct:locale collection="${brands}" locale="${locale}"
                                                       id="${product.brandId}"/></p>
                                        <p class="text-primary font-weight-bold"><fmt:message
                                                key="shop.price"/>: ${product.price} $</p>
                                        <input type="hidden" name="productId" value="${product.id}">
                                        <button class="btn btn-outline btn-primary addProduct"><fmt:message
                                                key="shop.to_cart"/></button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <form name="paginationTop" action="/ChernevOnlineStore/shop">
                        <c:if test="${not empty filters.name}">
                            <input type="hidden" name="name" value="${filters.name}">
                        </c:if>
                        <c:if test="${not empty filters.categories}">
                            <c:forEach var="value" items="${filters.categories}">
                                <input type="hidden" name="category" value="${value}">
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty from}">
                            <input type="hidden" name="from" value="${filters.from}">
                        </c:if>
                        <c:if test="${not empty to}">
                            <input type="hidden" name="to" value="${filters.to}">
                        </c:if>
                        <c:if test="${not empty filters.brands}">
                            <c:forEach var="value" items="${filters.brands}">
                                <input type="hidden" name="brand" value="${value}">
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty filters.byPage}">
                            <input type="hidden" name="byPage" value="${filters.byPage}">
                        </c:if>
                        <c:if test="${not empty filters.sortBy}">
                            <input type="hidden" name="sortBy" value="${filters.sortBy}">
                        </c:if>
                        <div class="row mb-2" data-aos="fade-up">
                            <div class="col-md-12 text-center">
                                <div class="site-block-27">
                                    <ul>
                                        <ct:pagination pages="${pages}" currentPage="${currentPage}"/>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </c:if>
            <c:if test="${ not empty noProducts}">
                <div class="col-md-9 order-2 text-center">
                    <span class="icon-check_circle display-3 text-danger"></span>
                    <h2 class="display-3 text-black"><fmt:message key="shop.sorry"/></h2>
                    <p><a href="/ChernevOnlineStore/shop" class="btn btn-sm btn-primary"><fmt:message
                            key="to_shop"/></a></p>
                </div>
            </c:if>
            <div class="col-md-3 order-1 mb-2  border rounded">
                <form class="js-form-validate" name="sideBar">
                    <div class=" btn btn-block btn-secondary btn-outline mt-3 my-1" data-toggle="collapse"
                         href="#nameCollapse" role="button" aria-expanded="true" aria-controls="nameCollapse">
                        <fmt:message key="shop.name"/>
                    </div>
                    <div id="nameCollapse" class="collapse show border p-4 rounded  text-dark">
                        <div class="input-group mb-3">
                            <input name="name" type="text" class="form-control"
                                   placeholder="<fmt:message key="shop.name" />" value="${filters.name}">
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="submit"><fmt:message
                                        key="shop.name.search"/></button>
                            </div>
                        </div>
                    </div>
                    <div class=" btn btn-block btn-secondary btn-outline my-1" data-toggle="collapse"
                         href="#categoryCollapse" role="button" aria-expanded="true">
                        <fmt:message key="shop.categories"/>
                    </div>
                    <div id="categoryCollapse" class=" collapse show border p-4 rounded mb-1 text-dark">
                        <h3 class="mb-3 h6 text-uppercase text-black d-block"><fmt:message key="shop.categories"/></h3>
                        <c:forEach var="value" items="${categories}">
                            <div class="form-check">
                                <c:if test="${ fn:contains( category, value.id ) }">
                                    <input class="form-check-input" type="checkbox" name="category" value="${value.id}"
                                           checked>
                                    <ct:locale collection="${categories}" locale="${locale}" id="${value.id}"/>
                                </c:if>
                                <c:if test="${ not fn:contains( category, value.id ) }">
                                    <input class="form-check-input" type="checkbox" name="category" value="${value.id}">
                                    <ct:locale collection="${categories}" locale="${locale}" id="${value.id}"/>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                    <div class=" btn btn-block btn-secondary btn-outline my-1" data-toggle="collapse"
                         href="#priceCollapse" role="button" aria-expanded="true" aria-controls="priceCollapse">
                        <fmt:message key="shop.price"/>
                    </div>
                    <div class="collapse show border p-4 rounded mb-1" id="priceCollapse">
                        <h3 class="mb-3 h6 text-uppercase text-black d-block"><fmt:message key="shop.price"/></h3>
                        <div class="row mb-1">
                            <div class="input-group">
                                <input name="from" type="number" class="form-control"
                                       placeholder="<fmt:message key="shop.price.from" />" value="${filters.from}">
                                <input name="to" type="number" class="form-control"
                                       placeholder="<fmt:message key="shop.price.to" />" value="${filters.to}">
                            </div>
                        </div>
                    </div>
                    <div class=" btn btn-block btn-secondary btn-outline my-1" data-toggle="collapse"
                         href="#manufacturerCollapse" role="button" aria-expanded="true">
                        <fmt:message key="shop.brands"/>
                    </div>
                    <div class="collapse show border p-4 rounded mb-1 text-dark" id="manufacturerCollapse">
                        <div class="mb-1">
                            <h3 class="mb-3 h6 text-uppercase text-black d-block"><fmt:message key="shop.brands"/></h3>
                            <c:forEach var="value" items="${brands}">
                                <div class="form-check">
                                    <c:if test="${ fn:contains( brand, value.id ) }">
                                        <input class="form-check-input" type="checkbox" name="brand" value="${value.id}"
                                               checked>
                                        <ct:locale collection="${brands}" locale="${locale}" id="${value.id}"/>
                                    </c:if>
                                    <c:if test="${ not fn:contains( brand, value.id ) }">
                                        <input class="form-check-input" type="checkbox" name="brand"
                                               value="${value.id}">
                                        <ct:locale collection="${brands}" locale="${locale}" id="${value.id}"/>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" name="page" value="0">
                    <c:if test="${not empty byPage}">
                        <input type="hidden" name="byPage" value="${filters.byPage}">
                    </c:if>
                    <c:if test="${not empty sortBy}">
                        <input type="hidden" name="sortBy" value="${filters.sortBy}">
                    </c:if>
                    <div class="form-group">
                        <button type="submit" placeholder="Search"
                                class="btn btn-primary btn-block"><fmt:message key="shop.name.search"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
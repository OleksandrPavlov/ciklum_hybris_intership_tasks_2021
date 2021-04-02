<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags"%>
<div class="container-fluid">
    <div class="row">
        <!--Here will be some Options-->
        <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
            <div>
                <h4>Options:</h4>
                <div id="crt-prod"><a href="/create-product">Create Product</a></div>
                <div id="rmv-prod">
                    <form method="POST" action="${pageContext.request.contextPath}/remove-all-products">
                        <button style="all: none;" type="submit" > Remove all products</button>
                    </form>
                </div>
            </div>
        </aside>
        <!--Here will be product main part -->
        <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
            <!--Product list-->
            <div class="product-list">
                <div class="row">
                    <c:forEach var="product" items="${PRODUCT_LIST}">
                        <div class="col-sm-12 col-md-4 col-lg-3 mbt-5">
                            <!--Product card block-->
                            <div class="card product">
                                <div class="card-body">
                                    <!--Image block-->
                                    <div>
                                        <img class="product-pic" src="https://via.placeholder.com/250"/>
                                    </div>
                                    <!--Product name-->
                                    <div class="center-text product-name">
                                        <h4>${product.name}</h4>
                                    </div>
                                    <!--Code Buy-->
                                    <div class="container">
                                        <div class="small-text">
                                            Code: ${product.id}
                                        </div>
                                        <input type="button" class="btn btn-info addProductToCart" value="buy" id="buy_${product.id}"/>
                                    </div>
                                    <div class="price">
                                        $ ${product.price}
                                    </div>
                                    <div style="text-align: center; margin-top:10px">
                                    <form method="POST" action="${pageContext.request.contextPath}/product-remove?productId=${product.id}" >
                                        <button type="submit" class="btn btn-danger">remove</button>
                                    </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${empty PRODUCT_LIST}">
                    <div class="alert alert-info" role="alert">
                        <h3>No one items found by your request!</h3>
                    </div>
                </c:if>
            </div>
        </main>
    </div>
</div>
<script src = "${pageContext.request.contextPath}/static/js/products.js"></script>
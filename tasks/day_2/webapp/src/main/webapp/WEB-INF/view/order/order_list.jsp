<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="cartBlock">
    <div><a href="${pageContext.request.contextPath}/product_list" class="modern">Back to products</a></div>
    <div id="cart">
            <div class="header">
                <img src = "${pageContext.request.contextPath}/static/img/order/cart.png">
            </div>

            <c:choose>
                <c:when test="${not empty currentOrder}">
                    <div class="body">
                        <div class="orderItems">
                            <table class="table orderItem">
                                 <thead>
                                    <tr>
                                        <th>name</th>
                                        <th>count</th>
                                        <th>price</th>
                                    </tr>
                                </thead>
                                <c:forEach var="item" items="${currentOrder.orderItems}">
                                    <tr id="orderItem_${item.product.id}">
                                        <td class="text-center pic">
                                            <img src="${pageContext.request.contextPath}/static/img/product/download.jpg"><br/>
                                            ${item.product.name}
                                        </td>
                                        <td class="count text-center">
                                            <input type="number" value="${item.quantity}" class="productCounter" min="1" max="1000"
                                                   id="count_${item.product.id}"/>
                                        </td>
                                        <td class="price text-center">$ <span id="price_${item.product.id}">${item.product.price}</span></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <div class="body">
                        <p>Currently your cart is empty!</p>
                    </div>
                    <div></div>
                </c:otherwise>
            </c:choose>
    </div>
<script src = "${pageContext.request.contextPath}/static/js/productCart.js"></script>
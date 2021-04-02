<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="cart" padding-left:auto>
    <form action="${pageContext.request.contextPath}/order/display" method="GET">
        <button type="submit" class="btn btn-primary "  style="background: transparent; border-color: #e8e8e8">
            cart <span id="productCartItemCount" class="badge badge-light">
                <c:choose>
                    <c:when test="${empty currentOrder}">
                        0
                    </c:when>
                    <c:otherwise>
                        ${currentOrder.orderItems.size()}
                    </c:otherwise>
                </c:choose>
            </span>
        </button>
    </form>
</div>

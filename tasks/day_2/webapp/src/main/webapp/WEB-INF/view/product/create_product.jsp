<div class="container-fluid">
    <div class="row">
        <div class="col-xs-0 col-sm-1  col-md-2 col-lg-4"></div>
	        <div class="col-xs-12 col-sm-10 col-md-8 col-lg-4" style="padding: 20px 40px 0px 40px">
                <form id="crt_product" action="${pageContext.request.contextPath}/create-product" method="POST">
                    <div class="card">
                        <div class="card-header">
                            Creation new product
                        </div>
                        <div class="card-body">
                            <div class="center-text">
                            </div>
                            <div class="form-group">
                                <span class="small-text">name:</span>
                                <input type="text" class="form-control" name="product-name" style="height: 28px"/>
                            </div>
                            <div class="form-group">
                                <span class="small-text">price:</span>
                                <input type="text" class="form-control" name="product-price" style="height: 28px"/>
                            </div>
                            <div class="row margin-top-bottom-5">
                                <div class="col-sm-3">Status:</div>
                                <div class="col-sm-7">
                                    <div class="form_radio">
                                        <input id="radio-1" type="radio" name="product-status" value="out_of_stock" checked>
                                        <label for="radio-1">Out of stock</label>
                                    </div>
                                    <div class="form_radio">
                                        <input id="radio-2" type="radio" name="product-status" value="in_stock">
                                        <label for="radio-2">In stock</label>
                                    </div>
                                    <div class="form_radio">
                                        <input id="radio-3" type="radio" name="product-status" value="running_low">
                                        <label for="radio-3">Running low</label>
                                    </div>
                                     <button type="submit" class="btn btn-success" style="margin-left:50px">create</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
	        </div>
    </div>
</div>
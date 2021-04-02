<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-0 col-sm-1  col-md-2 col-lg-4"></div>
	        <div class="col-xs-12 col-sm-10 col-md-8 col-lg-4" style="padding: 20px 40px 0px 40px">
                   <form id="login" action="${pageContext.request.contextPath}/sign_up" method="POST">
                        <div class="card">
                              <div class="card-header">
                               Sign up form
                              </div>
                              <div class="card-body">
                                  <div class="center-text">
                                  </div>
                                  <div class="form-group">
                                      <span class="small-text">login:</span>
                                      <input type="text" class="form-control" id="user-login" name="user-login" style="height: 28px"/>
                                  </div>
                                  <div class="form-group">
                                      <span class="small-text">password:</span>
                                      <input type="password" class="form-control" id="user-password" name="user-password" style="height: 28px"/>
                                  </div>
                                  <div class=" row margin-top-bottom-5">
                                      <div class="col-sm-12" style="text-align: center">
                                          <button type="submit" class="btn btn-success">sign in</button>
                                      </div>
                                  </div>
                              </div>
                        </div>
                   </form>
	        </div>
        <div class="col-xs-0 col-sm-1  col-md-2 col-lg-4"></div>
    </div>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/favicon.ico">

    <title>MiNaZg PMS</title>

    <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
    <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>

    <script src='<c:url value="/static/js/adminlte.min.js"/>'></script>
    <script src='<c:url value="/static/js/app.js"/>'></script>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
    <link href="<c:url value='/static/css/AdminLTE.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/_all-skins.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="container">
    <div class="row">
        <div id="mainWrapper">
            <div class="login-container">
                <div class="login-card">
                    <div class="login-form">
                        <c:url var="loginUrl" value="/login"/>
                        <form action="${loginUrl}" method="post" class="form-horizontal">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                                <input type="text" class="form-control" id="username" name="ssoId"
                                       placeholder="Enter Username"
                                       required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Enter Password" required>
                            </div>
                            <div class="input-group input-sm">
                                <div class="checkbox">
                                    <label><input type="checkbox" id="rememberme" name="remember-me"> Remember
                                        Me</label>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /
                            >
                            <div class="form-actions">
                                <input type="submit"
                                       class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>

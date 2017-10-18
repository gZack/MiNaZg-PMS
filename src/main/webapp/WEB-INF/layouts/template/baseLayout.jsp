<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <meta name="description" content="">
   <meta name="author" content="">
   <link rel="icon" href="https://getbootstrap.com/favicon.ico">

   <title>MiNaZg PMS</title>

   <!-- Bootstrap core CSS -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

   <!-- Custom styles for this template -->
   <link href="<c:url value='/static/css/dashboard.css' />"  rel="stylesheet"></link>

</head>

<body>
   <tiles:insertAttribute name="navigation" />

   <div class="container-fluid">
      <div class="row">
         <tiles:insertAttribute name="sidebar" />

         <main class="col-sm-9 ml-sm-auto col-md-10 pt-3" role="main">
            <h2><tiles:insertAttribute name="heading" /></h2>
            <hr/>
            <%--
            <section class="row text-center placeholders">
               <tiles:insertAttribute name="inner_nav" />
            </section> --%>

            <div class="table-responsive">
               <tiles:insertAttribute name="content" />
            </div>

            <tiles:insertAttribute name="footer" />

         </main>
      </div>
   </div>

   <%--
   <div class="container">
      <div class="jumbotron">
         <div class="header">
            <ul class="nav nav-pills pull-right">

            </ul>
            <h3 class="text-muted">Web Store</h3>
         </div>

         <h1>
            <tiles:insertAttribute name="heading" />
         </h1>
         <p>
            <tiles:insertAttribute name="tagline" />
         </p>
      </div>

      <div class="row">
         <tiles:insertAttribute name="content" />
      </div>

      <div class="footer">
         <tiles:insertAttribute name="footer" />
      </div>

   </div> --%>
</body>
</html>

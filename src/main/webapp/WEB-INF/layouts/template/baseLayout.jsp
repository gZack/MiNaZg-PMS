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

   <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
   <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

   <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
   <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>

   <script src='<c:url value="/static/js/adminlte.min.js"/>' ></script>

   <!-- Bootstrap core CSS -->
   <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
   <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
   <!-- Custom styles for this template -->
   <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
   <link href="<c:url value='/static/css/AdminLTE.min.css' />"  rel="stylesheet"></link>
   <link href="<c:url value='/static/css/_all-skins.min.css' />"  rel="stylesheet"></link>
   <link href="<c:url value='/static/css/app.css' />"  rel="stylesheet"></link>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<!-- Site wrapper -->
<div class="wrapper">
   <header class="main-header">
      <!-- Logo -->
      <a href="#" class="logo">
         <!-- mini logo for sidebar mini 50x50 pixels -->
         <span class="logo-mini"><b>P</b>MS</span>
         <!-- logo for regular state and mobile devices -->
         <span class="logo-lg"><b>MiNaZg</b>PMS</span>
      </a>
      <tiles:insertAttribute name="navigation" />
   </header>
   <aside class="main-sidebar">
      <tiles:insertAttribute name="sidebar" />
   </aside>
   <div class="content-wrapper">
      <section class="content">

      <tiles:insertAttribute name="content" />

      </section>
   </div>
   <footer class="main-footer">
      <tiles:insertAttribute name="footer" />
   </footer>
</div>

<script>
    $(document).ready(function () {
        $('.sidebar-menu').tree();
        $('.formWithDateValidation').submit(function () {
            var start = new Date($('#startDate').val());
            var end = new Date($('#endDate').val());
            if(start > end) {
                alert("Start Date can not be greater than End Date");
                return false;
            }
        });
    });

    // # ref http://eonasdan.github.io/bootstrap-datetimepicker/Options/
    $(".date").datetimepicker({
        format: 'MM-DD-YYYY',
        widgetPositioning: {
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });
</script>
<script src='<c:url value="/static/js/app.js"/>'></script>
</body>
</html>

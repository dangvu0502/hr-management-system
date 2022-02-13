<%-- 
    Document   : GroupViewAdd
    Created on : Feb 13, 2022, 10:33:40 AM
    Author     : quocb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Contract | Add</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" />
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' />
        <!-- Theme style -->
        <link href="../css/style.css" rel="stylesheet"/>
        <link href="../css/dialog.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="../Views/Home.jsp" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Home
            </a>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
       

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="panel">
                            <header class="panel-heading">
                                Contract Add
                            </header>
                            <div class="panel-body">
                                <form action="../Group/GroupAdd" method="POST">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">

                                            <c:forEach items="${Vgroup}" var="g">
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">Full Name</label>
                                                        <input type="text" class="form-control" disabled="" value="${g.name}">
                                                    </div>
                                                </div>
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">User Name</label>
                                                        <input type="text" class="form-control" disabled="" value="${g.code}">
                                                    </div>
                                                </div>
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">Email</label>
                                                        <input type="text" class="form-control" disabled="" value="${user.email}">
                                                    </div>
                                                </div>
                                            </c:forEach>

                                            <c:if test="${listUser==null||listUser.size()==0}"><h3 style="color: red">Select user account to add contract !!</h3></c:if>
                                            <div class=" form-group row col-lg-12 text-center" ${listUser==null||listUser.size()==0 ?'hidden':'' }>
                                                <button type="submit" id="submit-btn" class="btn btn-info">Add</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </section>
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/0.10.0/lodash.min.js"></script>
    </body>
</html>


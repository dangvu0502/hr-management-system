<%-- 
    Document   : ContractEdit
    Created on : Feb 12, 2022, 3:03:10 PM
    Author     : Egamorft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Contract | Edit</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="../css/style.css" rel="stylesheet" type="text/css" />


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
            <a href="Views1/Home.jsp" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Home
            </a>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../userimg/${sessionScope.account.avatar}" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>${sessionScope.account.fullname}</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href="../Contract/Details">
                                <i class="fa fa-glass"></i> <span>Contract Details</span>
                            </a>
                        </li>
                        <li>
                            <a href="../Contract/Add">
                                <i class="fa fa-glass"></i> <span>Contract Add</span>
                            </a>
                        </li>

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div  class="col-lg-3"></div>
                    <div class="col-lg-6 ">
                        <section class="panel">
                            <header class="panel-heading text-center">
                                Edit Contract
                            </header>
                            <c:if test="${message != null}">
                                <c:choose>
                                    <c:when test = "${message eq 'Edit Contract Successfully!!'}">
                                        <div class="error alert alert-success" role="alert">
                                            <h4 class="alert-heading">Error</h4><hr>
                                            <p class="mb-0">${message}</p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="error alert alert-danger" role="alert">
                                            <h4 class="alert-heading">Error</h4><hr>
                                            <p class="mb-0">${message}</p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <c:remove var="message" scope="session" /> 
                            </c:if>
                            <div class="panel-body">
                                <form action="../Contract/EditContract" method="POST">

                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <c:forEach items="${contract}" var="c">
                                            <input name="id" value="${c.id}" hidden="">
                                            <div class="col-lg-8">
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">Full name</label>
                                                        <input type="text" class="form-control" name="fullname" disabled="" value="${c.user_id.fullname}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="email">Email address</label>
                                                        <input type="email" class="form-control" name="email" disabled="" value="${c.user_id.email}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="StartDate">Start Date</label>
                                                        <input type="date" class="form-control" value="${c.startDate}" disabled="">
                                                        <input name="StartDate" value="${c.startDate}" hidden="">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="EndDate">End Date</label>
                                                        <input type="date" class="form-control" name="EndDate" value="${c.endDate}">
                                                    </div>
                                                </div>
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="type">Select type:</label>
                                                        <select class="form-control input-md" style="width: 200px;" name="type">
                                                            <option value="0"${c.type == 0 ? "selected":""}>Intern</option>
                                                            <option value="1"${c.type == 1 ? "selected":""}>Official</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <c:choose>
                                                            <c:when test = "${c.status == 0}">
                                                                <span class="badge bg-red">Contract Expired</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="badge bg-green">On contract</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                                <div class=" form-group row col-lg-12 text-center">
                                                    <button type="submit" id="submit-btn" class="btn btn-info"  >Save Change</button>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </form>

                            </div>
                        </section>
                    </div>
                </div>
            </section>

        </div>
        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="../js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="../js/Director/app.js" type="text/javascript"></script>
        <script src="../js/Director/myScript.js" type="text/javascript"></script>
        <script>

                                                        /** HIDE ALERT**/
                                                        $(document).click(function (e) {
                                                            $('.message').hide();
                                                        });
                                                        /** HIDE ALERT**/


        </script>
    </body>
</html>



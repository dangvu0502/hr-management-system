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
        <title>Project | Edit</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <!--<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
        <style>
            .box .slider{
                height: 40px;
                width: 300px;
                display: flex;
                align-items: center;
            }
            .box .slider .input{
                height: 10px;
                width: 100%;
                -webkit-appearance: none;
                outline: none;
                border-radius: 25px;
                box-shadow: inset 0px 0px 4px rgba(0,0,0,0.2);
            }
            .box .value{
                font-size: 30px;
                font-weight: 600;
                font-family: sans-serif;
                color: #3498db;
                width: 55px;
                text-align: center;
            }
        </style>
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
                            <a href="../Project/List">
                                <i class="fa fa-glass"></i> <span>Project List</span>
                            </a>
                        </li>
                        <li>
                            <a href="../Project/Add">
                                <i class="fa fa-glass"></i> <span>Project Add</span>
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
                                Edit Request
                            </header>
                            <c:if test="${message != null}">
                                <c:choose>
                                    <c:when test = "${message eq 'Edit Project Successfully!!'}">
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
                                <form action="../Request/EditRequest" method="POST">

                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <c:forEach items="${requestlist}" var="r">
                                            <input name="id" value="${r.id}" hidden="">
                                            <div class="col-lg-8">
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="request_date">Request Date</label>
                                                        <input type="date" class="form-control" name="request_date" value="${r.request_date}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="title">Title</label>
                                                        <input type="text" class="form-control" name="title" value="${r.title}" required="">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="support_type_id">Request Name</label>
                                                        <select class="form-control input-md" style="width: 200px;" name="support_type_id" >
                                                            <c:forEach var="sp" items="${listSP}">
                                                                <option value="${sp.id}" >${sp.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="manager">Username Manager</label>
                                                        <select class="form-control input-md" style="width: 200px;" name="manager">
                                                            <c:forEach items="${listU}" var="u">
                                                                <option value="${u.id}">${u.username}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="Status">Status</label><br>
                                                        <c:choose>
                                                            <c:when test = "${r.status == 0}">
                                                                <input type="radio" name="status" value="1" style="margin-left: 10px" > <span style="margin-right: 5px" >Submitted</span>
                                                                <input type="radio" name="status" value="2" checked="checked" style="margin-right: 5px"   > Assigned
                                                                <input type="radio" name="status" value="3"  style="margin-right: 5px;" > Completed
                                                                <input type="radio" name="status" value="4"  style="margin-right: 5px;" > Closed
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="radio" name="status" value="1" style="margin-left: 10px" > <span style="margin-right: 5px" >Submitted</span>
                                                                <input type="radio" name="status" value="2" checked="checked" style="margin-right: 5px"   > Assigned
                                                                <input type="radio" name="status" value="3"  style="margin-right: 5px;" > Completed
                                                                <input type="radio" name="status" value="4"  style="margin-right: 5px;" > Closed
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="update_date">Update Date</label>
                                                        <input type="date" class="form-control" name="update_date" value="${r.update_date}">
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
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>-->
        <script src="../js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="../js/Director/app.js" type="text/javascript"></script>
        <script src="../js/Director/myScript.js" type="text/javascript"></script>
        <script>

            /** HIDE ALERT**/
            $(document).click(function (e) {
                $('.error').hide();
            });
            /** HIDE ALERT**/

            function rangeSlide(value) {
                document.getElementById('rangeValue').innerHTML = value;
            }
        </script>
    </body>
</html>



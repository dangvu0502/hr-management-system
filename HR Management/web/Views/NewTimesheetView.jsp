
<%-- 
    Document   : Test
    Created on : Jan 12, 2022, 8:30:25 AM
    Author     : dangGG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Timesheet Detail</title>
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
        <div class="wrapper row-offcanvas row-offcanvas-left">

            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div  class="col-lg-3"></div>

                    <div class="col-lg-6 ">
                        <section class="panel">
                            <header class="panel-heading text-center">
                                New Timesheet
                            </header>
                            <div class="panel-body">
                                <form action="" method="POST" role="form" onsubmit="return chooseRole">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">

                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="fullname">Title</label>
                                                    <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter timesheet title" required>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="form-group col-lg-6">
                                                    <label for="username">Date</label>
                                                    <input type="date" class="form-control" id="username" name="username" onkeyup="checkUsername()" placeholder="Enter your username" required>

                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label for="email">Duration (hour)</label>
                                                    <input type="email" class="form-control" id="email" name="email" placeholder="Ex: 1:30" required>

                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-lg-6" >
                                                    <label for="system-role">Project</label>
                                                    <select class="form-control text-bold" aria-label="" id="system-role" name="system-role" onchange="val()">
                                                        <option value="0" selected></option>
                                                        <c:forEach var="role" items="${roles}">
                                                            <option value="${role.key}">${role.value } </option>
                                                        </c:forEach>
                                                        <c:remove var="roles" scope="session" />    
                                                    </select>

                                                </div>
                                                <div class="form-group col-lg-6" >

                                                    <label for="system-role">Process</label>
                                                    <select class="form-control text-bold" aria-label="" id="system-role" name="system-role" onchange="val()">
                                                        <option value="0" selected></option>
                                                        <c:forEach var="process" items="${timesheetProcess}">
                                                            <option value="${process.key}">${process.value}</option>
                                                        </c:forEach>
                                                    </select>

                                                </div>
                                            </div>



                                            <div class=" form-group col-lg-12 text-center">
                                                <br>
                                                <button type="submit" id="submit-btn" class="btn btn-info"  >Add new timesheet</button>
                                            </div>

                                        </div>
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


    </body>
</html>






























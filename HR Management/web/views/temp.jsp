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
        <title>New User</title>
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
                                Timesheet Title
                            </header>
                            <div class="panel-body">
                                <form action="../User/NewUser" method="POST" role="form" onsubmit="return chooseRole">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">
                                            <div class="row">
                                                <div class="form-group col-lg-12">
                                                    <p class="text-bold" >Project: &nbsp xyz</p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-lg-8">
                                                    <p class="text-bold" >Process: &nbsp training </p>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <p class="text-bold" >Date: &nbsp 2022-02-02</p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-lg-8">
                                                    <p class="text-bold" >Status: &nbsp <span class="text-success">Approved</span></p>
                                                </div>
                                                <div class="form-group col-lg-4">
                                                    <p class="text-bold" >Duration: &nbsp 1:30</p>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="work-result">Work result</label><p></p>
                                                    <textarea rows="10" cols="60" id="work-result" name="work-result" form="usrform">
                                                     </textarea>
                                                </div>
                                                <div class="form-group col-lg-12">
                                                    <label for="work-result">Reject reason</label><p></p>
                                                    <textarea rows="5" cols="60" id="work-result" name="work-result" form="usrform">
                                                     </textarea>
                                                </div>
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


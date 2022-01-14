<%-- 
    Document   : login
    Created on : Jan 11, 2022, 9:05:18 PM
    Author     : quocb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet" type="text/css" />


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
                                Login
                            </header>
                            <div class="panel-body">
                                <form action="login" method="POST" role="form" onsubmit="return isValid">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="username">Username or Email</label>
                                                    <input type="text" name="username" class="form-control" id="fullname" placeholder="Enter username or email" required>
                                                </div>
                                            </div>

                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="password">Password</label>
                                                    <input type="password" name ="password" class="form-control" id="password" onkeyup="checkEqual()" placeholder="Enter your password"  required >
                                                </div>
                                            </div>
                                            <p class="text-danger">${err}</p>

                                            <div class="row">

                                                <div class="form-group col-lg-6">
                                                    <span> <input type="checkbox" onclick="password_show_hide()">Show Password </span>

                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <a href="forgot-password.jsp">Forgot Password </a>
                                                </div>
                                            </div>

                                            <div class=" form-group row col-lg-12 text-center">
                                                <button type="submit" id="submit-btn" class="btn btn-info" onclick="ansValidation(event)">Login</button>
                                            </div>

                                            <div class="row ">
                                                <div class="col-lg-3"></div>
                                                <div class="col-lg-8">
                                                    <p>Do not have an account? &nbsp &nbsp  &nbsp  <a href="#">SignUp</a></p>
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
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>
        <script src="js/Director/myScript.js" type="text/javascript"></script>
    </body>
</html>


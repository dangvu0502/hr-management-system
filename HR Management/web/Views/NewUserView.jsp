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
                                New User
                            </header>
                            <c:if test="${successMessage != null}">
                                <div class="message alert alert-success" role="alert">
                                    <p class="mb-0">${successMessage}</p>
                                </div>
                                <c:remove var="successMessage" scope="session" /> 
                            </c:if>
                            <div class="panel-body">
                                <form action="../User/NewUser" method="POST" role="form" onsubmit="return chooseRole">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="group-code">Group code</label>
                                                    <input type="text" class="form-control" id="group-code" name="group-code" placeholder="Enter group code" required>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="fullname">Full name</label>
                                                    <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Enter your name" required>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="username">Username</label>
                                                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                                                    <c:if test="${usernameErrorMessage != null}">
                                                        <p class="message text-danger">${usernameErrorMessage}</p>
                                                        <c:remove var="usernameErrorMessage" scope="session" /> 
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-lg-12">
                                                    <label for="email">Email address</label>
                                                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                                                    <c:if test="${emailErrorMessage != null}">
                                                        <p class="message text-danger">${emailErrorMessage}</p>
                                                        <c:remove var="emailErrorMessage" scope="session" /> 
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-lg-12">
                                                    <label for="mobile">Mobile</label>
                                                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="Enter your mobile" required>
                                                </div>
                                            </div>
                                            <div class="row">

                                                <div class="form-group col-lg-12">
                                                    <span class="text-bold" >Choose Gender  &nbsp &nbsp 
                                                        <label for="male">Male</label>
                                                        <input type="radio" name="gender" id="male" value="male" checked> &nbsp &nbsp &nbsp
                                                        <label for="female">Female</label>
                                                        <input type="radio" name="gender" id="female" value="female">
                                                    </span>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group form-inline col-lg-6" >
                                                    <span style="white-space: nowrap">
                                                        <label for="system-role">System Role  &nbsp &nbsp &nbsp </label>
                                                        <select class="form-control text-bold" aria-label="" id="system-role" name="system-role" onchange="val()">
                                                            <option value="0" selected></option>
                                                            <c:forEach var="role" items="${roles}">
                                                                <option value="${role.key}">${role.value} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </span>

                                                </div>
                                            </div>



                                            <div class=" form-group row col-lg-12 text-center">
                                                <br>
                                                <button type="submit" id="submit-btn" class="btn btn-info"  >Add new user</button>
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


        <script>

                                                            /** HIDE ALERT**/
                                                            $(document).click(function (e) {
                                                                $('.message').hide();
                                                            });
                                                            /** HIDE ALERT**/

                                                            var chooseRole = false;
                                                            function val() {
                                                                var value = document.getElementById("system-role").value;
                                                                if (value != 0)
                                                                    chooseRole = true;
                                                                else
                                                                    chooseRole = false;
                                                            }
        </script>
    </body>
</html>


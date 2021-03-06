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
        <!--<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />-->
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <!--<link href="../css/style.css" rel="stylesheet" type="text/css" />-->



        <!--Boostrap 4-->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>


        <link rel="icon" href="<%= request.getContextPath()%>/img/HoneyBee.png" />
        <!--Boostrap 4-->

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <style> 
            .skin-black{
                background: #f1f2f7
            }
            .panel{
                background: #fff;
                margin-top:30px;
            }
            .panel-heading{
                padding:10px 15px;
                text-transform: uppercase;   
                border-bottom: 1px solid transparent;
                border-top-right-radius: 3px;
                border-top-left-radius: 3px;
                border-bottom: 1px solid #eee;
                background-color: #FAFAFA;
            }
            .form-group {
                margin-bottom: 15px;            
                margin-top:  15px;
            }
        </style>
    </head>

    <body class="skin-black">
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@ include file = "Header/NavBar.jsp" %>
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
                                <form action="../User/NewUser" method="POST" role="form" onsubmit="return chooseRole && chooseCode">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">
                                            <!--                                        <div class="row ">
                                                                                            <div class="form-group col-lg-12">
                                                                                                <label for="group-code">Group code</label>
                                                                                                <input type="text" class="form-control" id="group-code" name="group-code" placeholder="Enter group code" required>
                                                                                            </div>
                                                                                        </div>-->
                                            <div class="row">
                                                <div class="form-group form-inline col-lg-12" >
                                                    <div class="col-lg-12"> </div>
                                                    <label for="group-code">Group Code  &nbsp &nbsp &nbsp </label>
                                                    <select class="form-control text-bold" aria-label="" id="group-code" name="group-code" onchange="val2()">
                                                        <option value="0" selected></option>
                                                        <c:forEach var="group" items="${groups}">
                                                            <option value="${group.key}">${group.key} </option>
                                                        </c:forEach>
                                                        <c:remove var="groups" scope="session" />     
                                                    </select>

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
                                                    <input type="text" class="form-control" id="username" name="username" onkeyup="checkUsername()" placeholder="Enter your username" required>
                                                    <c:if test="${usernameErrorMessage != null}">
                                                        <p class="message text-danger">${usernameErrorMessage}</p>
                                                        <c:remove var="usernameErrorMessage" scope="session" /> 
                                                    </c:if>
                                                    <span id='inValidUsername'></span>       
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
                                                    <input type="text" class="form-control" id="mobile" name="mobile" onkeyup="checkMobile()" placeholder="Enter your mobile" required>
                                                    <span id='inValidPhone'></span>
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
                                                <div class="form-group form-inline col-lg-12" >
                                                    <div class="col-lg-12"> </div>
                                                    <label for="system-role">System Role  &nbsp &nbsp &nbsp </label>
                                                    <select class="form-control text-bold" aria-label="" id="system-role" name="system-role" onchange="val1()">
                                                        <option value="0" selected></option>
                                                        <c:forEach var="role" items="${roles}">
                                                            <option value="${role.key}">${role.value } </option>
                                                        </c:forEach>
                                                        <c:remove var="roles" scope="session" />    
                                                    </select>

                                                </div>
                                            </div>



                                            <div class=" form-group col-lg-12 text-center">
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
                                                        function val1() {
                                                            var value = document.getElementById("system-role").value;
                                                            if (value != 0)
                                                                chooseRole = true;
                                                            else
                                                                chooseRole = false;
                                                        }
                                                        
                                                        var chooseCode = false;
                                                        function val2() {
                                                            var value = document.getElementById("group-code").value;
                                                            if (value != 0)
                                                                chooseCode  = true;
                                                            else
                                                                chooseCode  = false;
                                                        }

                                                        function checkMobile() {
                                                            var mobile = document.getElementById('mobile');
                                                            var regex = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
                                                            if (mobile == null || mobile.value.toString().length == 0) {
                                                                document.getElementById('inValidPhone').innerHTML = '';
                                                            } else {

                                                                if (mobile.value.match(regex)) {
                                                                    isValid = true;
                                                                    document.getElementById('inValidPhone').style.color = 'green';
                                                                    document.getElementById('inValidPhone').innerHTML = 'Valid';
                                                                } else {
                                                                    isValid = false;
                                                                    document.getElementById('inValidPhone').style.color = 'red';
                                                                    document.getElementById('inValidPhone').innerHTML = 'This is not mobile';
                                                                }
                                                            }
                                                        }

                                                        function checkUsername() {
                                                            var username = document.getElementById('username');
                                                            var regex = /^[a-zA-Z0-9]+$/;
                                                            if (username == null || username.value.toString().length == 0) {
                                                                document.getElementById('inValidUsername').innerHTML = '';
                                                            } else {

                                                                if (username.value.match(regex)) {
                                                                    isValid = true;
                                                                    document.getElementById('inValidUsername').style.color = 'green';
                                                                    document.getElementById('inValidUsername').innerHTML = 'Valid';
                                                                } else {
                                                                    isValid = false;
                                                                    document.getElementById('inValidUsername').style.color = 'red';
                                                                    document.getElementById('inValidUsername').innerHTML = 'Not Valid';
                                                                }
                                                            }
                                                        }
        </script>
    </body>
</html>


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
          <%@include  file="Header/Treebar.jsp" %>
            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div  class="col-lg-3"></div>
                    <div class="col-lg-6 ">
                        <section class="panel">
                            <header class="panel-heading text-center">
                                Edit User
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
                                <form action="../UserListController/Edit" >
                                    <div class="row ">
                                        <div class="form-group col-lg-12">
                                            <label for="name">ID</label>
                                            <input type="text" class="form-control" name="id"  value="${userList.id}" readonly="">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-lg-12">
                                            <label for="group">Group Code</label>
                                            <select class="form-control input-md" style="width: 200px;" name="groupUser" >
                                                <option value="${userList.group_code}">${userList.group_code}</option>
                                                <c:forEach var="group" items="${group}">
                                                    <option value="${group}" ${p.groupCode eq group ?'selected':''} >${group}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row ">
                                        <div class="form-group col-lg-12">
                                            <label for="name">User Name</label>
                                            
                                            <input type="text" class="form-control" name="username"  value="${userList.username}">
                                        </div>
                                    </div>
                                        <div class="row ">
                                        <div class="form-group col-lg-12">
                                            <label for="name">Full Name</label>
                                            <input type="text" class="form-control" name="fullname"  value="${userList.fullname}">
                                        </div>
                                    </div>
                                        <div class="row">
                                        <div class="form-group col-lg-12">
                                            <label for="Status">Gender</label><br>
                                            <c:choose>
                                                <c:when test = "${userList.gender == false}">
                                                    <input  type="radio" name="genderUser" value="1" id="rbStatus" > <span style="margin-right: 2rem;" >Male</span>
                                                    <input type="radio" name="genderUser" value="0" id="rbStatus" checked="checked"> Female
                                                </c:when>
                                                <c:otherwise>
                                                    <input  type="radio" name="genderUser" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Male</span>
                                                    <input type="radio" name="genderUser" value="0" id="rbStatus"> Female
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-lg-12">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" name="email"  value="${userList.email}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-lg-12">
                                            <label for="description">Mobile</label>
                                            <input type="text" class="form-control" name="mobile"  value="${userList.mobile}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-lg-12">
                                            <label for="Status">Status</label><br>
                                            <c:choose>
                                                <c:when test = "${listS.status == false}">
                                                    <input  type="radio" name="foo" value="1" id="rbStatus" > <span style="margin-right: 2rem;" >Active</span>
                                                    <input type="radio" name="foo" value="0" id="rbStatus" checked="checked"> Deactivate
                                                </c:when>
                                                <c:otherwise>
                                                    <input  type="radio" name="foo" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Active</span>
                                                    <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class=" form-group row col-lg-12 text-center">
                                        <button type="submit" id="submit-btn" class="btn btn-info"  >Save Change</button>
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



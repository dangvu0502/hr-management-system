<%-- 
    Document   : EditProfile
    Created on : Jan 12, 2022, 8:23:08 PM
    Author     : Egamorft
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Profile</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="css/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
        <!-- Daterange picker -->
        <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- iCheck for checkboxes and radio inputs -->
        <link href="css/iCheck/all.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="js/Director/app.js" type="text/javascript"></script>


        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
              <![endif]-->

        <style>
            img {
                border-radius: 50%;
            }

            #photo {
                height: 100%;
                width: 100%;
            }

            #file {
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="container rounded bg-white ">
            <div class="tab-content">
                <ul class="nav nav-tabs nav-justified" role="tablist">
                    <li class="active"><a data-toggle="tab" href="#profile">Profile</a></li>
                    <li><a data-toggle="tab" href="#password">Change Password</a></li>
                </ul>
                <div id="profile" class="tab-pane fade in active">

                    <form action="EditProfile" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-3">
                                <figure class="text-center">
                                    <figcaption>
                                        <h4>${sessionScope.account.username}</h4>
                                    </figcaption>
                                    <img class="img-rounded" src="../img/${sessionScope.account.avatar}" alt="avatar" style="width:200px">
                                    <input name="image" class="file-upload" type="file" accept="image/*" id="file">
                                    <!--<input type="hidden" name="image" value="">-->
                                    <button class="btn btn-primary btn-addon btn-sm"><label for="file">Choose Photo</label></button>
                                </figure>
                            </div>
                            <div class="col-md-9">
                                <div class="col-md-8 col-md-4">
                                    <input name="username" type="hidden" value="${sessionScope.account.username}">
                                    <div class="col-md"><label class="labels">Date of birth</label><input type="date" class="form-control" value="" disabled=""></div>

                                    <div class="col-md"><label class="labels">Full name</label><input name="fullname" type="text" class="form-control" value="${sessionScope.account.fullname}"></div>
                                    <div class="col-md"><label class="labels">Address</label><input type="text" class="form-control" value="" disabled=""></div>
                                    <div class="col-md"><label class="labels">Password</label><input name="password" type="password" class="form-control" value=""></div>
                                </div>
                                <div class="col-md-8 col-md-4">
                                    <div class="col-md"><label class="labels">Sex</label>
                                        <select class="form-control" disabled="">
                                            <option value="">Male</option>
                                            <option value="">Female</option>
                                        </select>
                                    </div>
                                    <div class="col-md"><label class="labels">Email</label><input type="email" class="form-control" value="" disabled=""></div>
                                    <div class="col-md"><label class="labels">Phone number</label><input type="tel" class="form-control" value="" disabled=""></div>
                                </div>

                            </div>

                        </div>
                        <c:choose>      
                            <c:when test="${error != null && error !=''}">
                                <p style="color: red ">${error}</p>
                            </c:when>
                            <c:otherwise>
                                <p style="color: green">${error}</p>
                            </c:otherwise>
                        </c:choose>  
                        <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                    </form>
                </div>
                <div id="password" class="tab-pane fade">
                    <form action="ChangePassword" method="POST">
                        <div class="row mt-5">
                            <input name="username" type="hidden" value="${sessionScope.account.username}">

                            <div class="col-md-8">
                                <div class="col-md-4 col-md-offset-7"><label class="labels">Old Password</label><input name="oldpass" type="password" class="form-control" required="" ></div>
                            </div>
                            <div class="col-md-8">
                                <div class="col-md-4 col-md-offset-7"><label class="labels">New Password</label><input name="newpass" type="password" class="form-control" required=""></div>
                            </div>
                            <div class="col-md-8">
                                <div class="col-md-4 col-md-offset-7"><label class="labels">Confirm Password</label><input name="renewpass" type="password" class="form-control"  required=""></div>
                            </div>
                        </div>
                        <input name="test" type="hidden" value="1">
                        <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Submit Change</button></div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        /***AVATAR SCRIPT***/

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var fileurl = e.target.result;
                    $('.img-rounded').attr('src', fileurl);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        $(".file-upload").on('change', function () {
            readURL(this);
        });
        $(".upload-button").on('click', function () {
            $(".file-upload").click();
        });
        /***AVATAR SCRIPT***/
    </script>
</html>

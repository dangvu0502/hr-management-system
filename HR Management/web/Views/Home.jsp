<%-- 
    Document   : Home
    Created on : Jan 14, 2022, 2:27:08 PM
    Author     : lehun
--%>
<%@page import="Models.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Context.TrippleDes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home Page</title>
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

        <!-- cdn font awsome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link rel="icon" href="<%= request.getContextPath()%>/img/HoneyBee.png" />

        <style>
            .Signup {
                border: 2px solid black;
                border-radius: 25%;
            }
            .MainContent {
                background-image: url("../img/Banner.png");
                background-repeat: no-repeat;
                background-size: cover;
                height: 900px;
                z-index: -1;
            }
            .Content {
                padding: 5rem;
                padding-right: 1100px;
            }
            .h1 {
                font-size: 3.5rem;
                font-weight: 700;
                margin-bottom: 50px;
            }
            .h2 {
                font-size: 3rem;
                font-weight: 700;
                margin-bottom: 10px;
            }
            .text-content {
                margin-top: 50px;
                font-size: 1.5rem;
                line-height: 40px;
                font-weight: 500;
                margin-bottom: 10px;
            }
            .btnSignup {
                margin-top: 20px;
                font-size: 1.5rem;
                border-radius: 10px;
                box-shadow: 2px 5px #888888;
            }
            .modal-header{
                justify-content: flex-start!important;
            }
            .close{
                margin: -1rem!important;
                font-size: 2rem;
            }
            .modal-title{
                margin-left: 20px;
            }
            .file-upload{
                margin-top: 20px;
            }
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
        <%@ include file = "Header/NavBar.jsp" %>

        <div id="success" class="error alert alert-success" style="display: none">
            <strong>Well done!</strong> You successfully read this important alert message.
        </div>
        <div id="danger" class="error alert alert-warning" style="display: none">
            <strong>Warning!</strong> Best check yo self, you're not looking too good.
        </div>
        <!-- Main content -->
        <div class="MainContent text-white">
            <div class="Content">
                <h1 class="h1">Hr Management System</h1>
                <h2 class="text-uppercase h2">We're Hiring</h2>
                <p class="text-left text-content font-italic">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Eum corporis
                    voluptates, minus adipisci fugiat similique pariatur assumenda
                    voluptatum neque libero.
                </p>
                <button class="btn btn-primary btnSignup">Sign Up</button>
            </div>
        </div>
        <!-- Main content -->
        <div class="container">
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Edit Profile</h4>
                        </div>
                        <div class="modal-body">
                            <form action="../User/EditProfile" method="POST">
                                <div class="row">
                                    <div class="col-md-3">
                                        <figure class="text-center">
                                            <figcaption>
                                                <h4>${sessionScope.account.username}</h4>
                                            </figcaption>
                                            <img class="img-rounded" src="../userimg/${sessionScope.account.avatar}" alt="avatar" style="width:200px">
                                            <input name="fileName" onchange="downloadImage()" class="file-upload" type="file" multiple accept="image/*" id="file" value="${sessionScope.account.avatar}">
                                            <button class="btn btn-primary btn-addon btn-sm"><label for="file">Choose Photo</label></button>
                                        </figure>
                                    </div>
                                    <div class="col-md-9">
                                        <div class="col-md-8">
                                            <input name="username" type="hidden" value="${sessionScope.account.username}">
                                            <div class="col-md"><label class="labels">Full name</label><input name="fullname" type="text" class="form-control" value="${sessionScope.account.fullname}" required=""></div>
                                            <div class="col-md"><label class="labels">Date of birth</label><input name="dob" type="date" class="form-control" value="${sessionScope.account.dob}" required=""></div>
                                            <div class="col-md"><label class="labels">Address</label><input name="address" type="text" class="form-control" value="${sessionScope.account.address}" required="" ></div>
                                            <div class="col-md"></br>
                                                <label class="labels" for="1">Male</label>&nbsp&nbsp<input id="1" value="true" name="gender" type="radio" ${sessionScope.account.gender == 'true'?'checked':''} >
                                                <label class="labels" for="0">Female</label>&nbsp&nbsp<input id="0" value="false" name="gender" type="radio" ${sessionScope.account.gender == 'false'?'checked':''} >

                                            </div>
                                            <div class="col-md"><label class="labels">Email</label><input name="email" type="email" class="form-control" value="${sessionScope.account.email}" disabled=""></div>
                                            <div class="col-md"><label class="labels">Phone number</label><input name="mobile" type="tel" class="form-control" value="${sessionScope.account.mobile}" required="" ></div>

                                        </div>

                                    </div>

                                </div>

                                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit" onchange="checkPassword()">Save Profile</button></div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer class="text-center text-lg-start bg-light text-muted">
            <!-- Section: Links  -->
            <section class="">
                <div class="container text-center text-md-start mt-5">
                    <!-- Grid row -->
                    <div class="row mt-3">
                        <!-- Grid column -->
                        <div class="col-lg-4 c3 mx-auto mb-4">
                            <!-- Content -->
                            <h6 class="text-uppercase mb-4">
                                <i class="fas fa-diamond mt-3"></i> HR Management
                            </h6>
                            <p class="text-left ml-3">
                                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolor
                                atque, voluptate, eius blanditiis cupiditate eos voluptatem
                                minus, pariatur quae harum repellendus. Doloremque neque labore
                                blanditiis? Sapiente nostrum dolorem ad eveniet.
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase mb-4 mt-3">Products</h6>
                            <p>
                                <a href="#" class="text-reset mt-3">Blog Page</a>
                            </p>
                            <p>
                                <a href="#" class="text-reset">Home Page</a>
                            </p>
                            <p>
                                <a href="#" class="text-reset">Password Reset</a>
                            </p>
                            <p>
                                <a href="#" class="text-reset">user Register</a>
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase mb-4 mt-3">Other</h6>
                            <p>
                                <a href="#!" class="text-reset">Time Sheet</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">Settings</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">FeedBack</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">Help</a>
                            </p>
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div
                            class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4 text-left"
                            >
                            <!-- Links -->
                            <h6 class="text-uppercase mb-4 mt-3">Contact</h6>
                            <p><i class="fas fa-home mr-3"></i> FPT HOLA</p>
                            <p>
                                <i class="fas fa-envelope mr-3"></i>
                                HRManagerment@gmail.com
                            </p>
                            <p><i class="fas fa-phone mr-3"></i>+84 123456789</p>
                            <p><i class="fas fa-print mr-3"></i>096966969</p>
                        </div>
                        <!-- Grid column -->
                    </div>
                    <!-- Grid row -->
                </div>
            </section>
            <!-- Section: Links  -->

            <!-- Copyright -->
            <div
                class="text-center p-4"
                style="background-color: rgba(0, 0, 0, 0.05)"
                >
                ?? 2021 Copyright:
                <a class="text-reset" href="#">HR Management</a>
            </div>
            <!-- Copyright -->
        </footer>
        <!-- Footer -->
    </body>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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

                                    /** HIDE ALERT**/
                                    $(document).keypress(function (e) {
                                        $('.error').hide();
                                    });
                                    /** HIDE ALERT**/

                                    /** CHECK EDIT PROFILE**/
                                    function checkPassword() {
                                        alert("Successful change your information!!");
                                    }
                                    /** CHECK EDIT PROFILE**/

                                    /** CHECK CHANGE PASSWORD**/
                                    function checkPasswordChange() {
                                        var oldpassword = document.getElementById('oldpassword').value;
                                        var oldpassword1 = document.getElementById('oldpassword1').value;
                                        var newpassword = document.getElementById('newpassword').value;
                                        var conpassword = document.getElementById('conpassword').value;
                                        if (oldpassword == oldpassword1) {
                                            if (newpassword == conpassword) {
                                                alert("Successful update your new password!!");
                                            } else {
                                                alert("Wrong confirm password");
                                            }
                                        } else {
                                            alert("Wrong old password!!");
                                        }
                                    }
                                    /** CHECK CHANGE PASSWORD**/

                                    /** DOWNLOAD IMAGE**/
                                    function downloadImage() {
                                        var file = document.getElementById('file')
                                        var bodyFormData = new FormData();
                                        bodyFormData.append('fileName', file.files[0]);
                                        axios({
                                            method: "post",
                                            url: "http://localhost:8080/HR_Management/UploadFile",
                                            data: bodyFormData,
                                            headers: {"Content-Type": "multipart/form-data"},
                                        }).then(function (response) {
                                            //handle success
                                            console.log(response);
                                        })
                                                .catch(function (response) {
                                                    //handle error
                                                    console.log(response);
                                                });
                                    }
                                    /** DOWNLOAD IMAGE**/
    </script>
</html>

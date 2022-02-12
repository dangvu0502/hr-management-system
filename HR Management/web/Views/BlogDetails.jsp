<%-- 
    Document   : BlogDetails
    Created on : Feb 11, 2022, 7:01:19 PM
    Author     : lehun
--%>

<%@page import="Models.BLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Title</title>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

        <!-- Bootstrap CSS -->
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script>
            $(window).on("load", function () {
                function fade() {
                    var animation_height = $(window).innerHeight() * 0.25;
                    var ratio = Math.round((1 / animation_height) * 10000) / 10000;

                    $(".fade").each(function () {
                        var objectTop = $(this).offset().top;
                        var windowBottom = $(window).scrollTop() + $(window).innerHeight();

                        if (objectTop < windowBottom) {
                            if (objectTop < windowBottom - animation_height) {
                                $(this).css({
                                    transition: "opacity 0.1s linear",
                                    opacity: 1,
                                });
                            } else {
                                $(this).css({
                                    transition: "opacity 0.25s linear",
                                    opacity: (windowBottom - objectTop) * ratio,
                                });
                            }
                        } else {
                            $(this).css("opacity", 0);
                        }
                    });
                }
                $(".fade").css("opacity", 0);
                fade();
                $(window).scroll(function () {
                    fade();
                });
            });
        </script>
        <style>
            body {
                margin: 0;
                padding: 0;
                font-size: 20px;
                font-family: "Times New Roman", Times, serif;
            }
            section {
                max-width: 1000px;
                margin: auto;
                height: 1000px;
            }
            .img-fluid {
                display: block;
                position: relative;
                width: 100%;
                height: 100%;
                object-fit: cover;
                z-index: 1;
                transition: opacity 0.35s ease, transform 0.35s ease;
            }
            .main_WrapperContent {
                float: left;
                overflow: hidden;
                width: 60%;
                margin-top: 40px;
            }
            .SideBar_Wrapper {
                float: right;
                width: 35%;
                top: 0; 
                padding-bottom: 10px;
                background-color: #eee;
                position: -webkit-sticky; /* Safari */
                position: sticky;
                margin-top: 40px;
                padding: 20px 20px 0px 20px;
            }
            .col-lg-8 {
                padding: 0px !important;
            }
            .Main_Tittle {
                font-weight: bold;
                font-size: 3rem;
                font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
                    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
            }
            .Main_content {
                font-family: "Times New Roman", Times, serif;
                font-size: 1rem;
            }
            .Blog_list {
                margin: 40px 0px;
            }
            .Briefs_info {
                font-size: 15px;
            }
            .Popular_Post_Text {
                background-color: black;
                text-align: center;
                color: white;
                font-weight: 700;
                font-size: 20px;
                margin-top: 20px;
                padding: 10px;
            }
            .Tittle_popular_Post {
                font-size: 17px;
            }
            .Category_popular_Post {
                font-size: 17px;
                margin-bottom: 5px;
                padding-left: 5px;
            }
            a {
                color: inherit;
            }
            a:hover {
                transition: color 0.17s;
                color: #f37879;
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        <!--navbar-->    
        <%@ include file = "Header/NavBar.jsp" %>
        <!--navbar-->
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <!-- https://1.bp.blogspot.com/-Az_2DYAbtNI/X6yyinSmerI/AAAAAAAADbw/AYAOC_dN6VoUqSl5R96JB7-3y_owsXzZwCLcBGAsYHQ/w330-h270-p-k-no-nu/m1%2B%25281%2529.jpg -->
        <!-- <div class="fade">Fade In 01</div> -->
        <section>
            <!-- Side-Bar -->
            <div class="SideBar_Wrapper">
                <!-- Search_Bar -->
                <form class="form-inline">
                    <div class="form-group mb-2"></div>
                    <div class="form-group mb-2 align-center">
                        <label for="inputPassword2" class="sr-only"></label>
                        <input
                            type="password"
                            class="form-control"
                            id="inputPassword2"
                            placeholder="Search"
                            />
                    </div>
                    <button type="submit" class="btn btn-primary mb-2">Search</button>
                </form>
                <!-- Popular-Post -->
                <div class="Popular_Post">
                    <h3 class="Popular_Post_Text">Popular Post</h3>
                    <div class="mt-2">
                        <div class="Blog_list">
                            <div class="row">
                                <div class="col-lg-4">
                                    <img
                                        class="img-fluid"
                                        src="https://1.bp.blogspot.com/-Az_2DYAbtNI/X6yyinSmerI/AAAAAAAADbw/AYAOC_dN6VoUqSl5R96JB7-3y_owsXzZwCLcBGAsYHQ/w330-h270-p-k-no-nu/m1%2B%25281%2529.jpg"
                                        alt=""
                                        />
                                </div>
                                <div class="col-lg-8">
                                    <h4 class="Tittle_popular_Post">
                                        <a href="#">
                                            Looking Better in Photos Based on the body Type
                                        </a>
                                    </h4>
                                    <p class="text-muted Category_popular_Post">CateGory:</p>
                                    <div class="d-flex Category">
                                        <span class="badge badge-pill badge-light"
                                              ><a href="#">Light</a></span
                                        ><span class="badge badge-pill badge-light"
                                               ><a href="#">Light</a></span
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />
                        <div class="Blog_list">
                            <div class="row">
                                <div class="col-lg-4">
                                    <img
                                        class="img-fluid"
                                        src="https://1.bp.blogspot.com/-Az_2DYAbtNI/X6yyinSmerI/AAAAAAAADbw/AYAOC_dN6VoUqSl5R96JB7-3y_owsXzZwCLcBGAsYHQ/w330-h270-p-k-no-nu/m1%2B%25281%2529.jpg"
                                        alt=""
                                        />
                                </div>
                                <div class="col-lg-8">
                                    <h4 class="Tittle_popular_Post">
                                        <a href="#">
                                            Looking Better in Photos Based on the body Type
                                        </a>
                                    </h4>
                                    <p class="text-muted Category_popular_Post">CateGory:</p>
                                    <div class="d-flex Category">
                                        <span class="badge badge-pill badge-light"
                                              ><a href="#">Light</a></span
                                        ><span class="badge badge-pill badge-light"
                                               ><a href="#">Light</a></span
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />
                    </div>
                </div>
            </div>
            <!-- Main-Wrapper -->
            <div class="main_WrapperContent">
                <%    BLog b =  (BLog)request.getAttribute("BlogDetails"); %>
                <h1 class="Main_Tittle">A Loving Heart is the Truest Wisdom</h1>
                <div class="Content d-flex justify-content-between">
                    <div class="Main_Author text-muted">
                        <i class="fa-solid fa-at"></i><%=b.getAuthor() %>
                    </div>
                    <div class="Main_PostDate text-muted">
                        <i class="fa-solid fa-calendar-days"></i><%=b.getPublishDate() %>
                    </div>
                </div>
                <div class="Main_Category text-muted">CateGory:  <%=b.getCategory()%></div>
                <p class="Main_brieft">
                    <%=b.getBrieft() %>
                </p>
                <img
                    src="<%=b.getThumnail_Image() %>"
                    alt="Thumnail_Image"
                    class="img-responsive Main_Thumnail_Image"
                    />
                <p class="Main_content mt-5">
                <!--https://preview.colorlib.com/theme/andrea/images/ximage_1.jpg.pagespeed.ic.bP9m1ezc08.webp-->
                <%=b.getContent() %>
                </p>
            </div>
            <!-- Main-Wrapper -->
        </section>

        <!-- boostrap -->
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
    </body>
</html>

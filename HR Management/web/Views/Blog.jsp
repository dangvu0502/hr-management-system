<%-- 
    Document   : Blog
    Created on : Feb 11, 2022, 6:58:44 PM
    Author     : lehun
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Blog</title>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

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

        <!-- cdn font awsome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link rel="icon" href="../img/HoneyBee.png" />

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
            }
            .SideBar_Wrapper {
                float: right;
                width: 35%;
                padding-bottom: 10px;
                background-color: #eee;
                position: sticky;
                margin-top: 40px;
            }
            .Tittle {
                font-weight: 700;
                font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
                    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
            }
            .Blog_list {
                margin: 40px 0px;
            }
            .Briefs_info {
                font-size: 15px;
                text-overflow: ellipsis;
                overflow: hidden; 
                width: 160px; 
                height: 1.2em; 
                white-space: nowrap;
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
                font-size: 13px;
            }
            a {
                color: inherit;
            }
            a:hover {
                transition: color 0.17s;
                color: #f37879;
                text-decoration: none;
            }
            .dropdown{

                margin-top: 40px;
            }
        </style>
    </head>

    <body>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <!-- https://1.bp.blogspot.com/-Az_2DYAbtNI/X6yyinSmerI/AAAAAAAADbw/AYAOC_dN6VoUqSl5R96JB7-3y_owsXzZwCLcBGAsYHQ/w330-h270-p-k-no-nu/m1%2B%25281%2529.jpg -->
        <!-- <div class="fade">Fade In 01</div> -->
        <!--navbar-->    
        <%@ include file = "Header/NavBar.jsp" %>
        <!--navbar-->

        <section>
            <!-- Side-Bar -->
            <div class="SideBar_Wrapper">
                <!-- Search_Bar -->
                <form action="BlogSearchController" method="post" class="form-inline" 
                      style="padding: 20px 0px 20px 30px">
                    <div class="form-group mb-2">
                        <select class="form-control" name="type" >
                            <option value="Tittle">Tittle</option>
                            <option value="Brieft">Brieft</option>
                        </select>                                            
                    </div>
                    <div class="form-group mb-2 align-center">
                        <label for="TittleSearch" class="sr-only"></label>
                        <input
                            type="text"
                            class="form-control"
                            id="text"
                            name="inputSearch"
                            placeholder="Search"
                            required=""
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
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Popular-Post -->
            </div>
            <!-- Side-Bar -->

            <!-- Main-Wrapper -->
            <div class="main_WrapperContent">
                <div class="dropdown">
                    <p>Filter by:</p>
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ALL 
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item category-title" tabindex="0" id="Funny" >Funny</a>
                        <a class="dropdown-item category-title" tabindex="0" id="Gamming" >Gamming</a>
                    </div>
                </div>


                <c:forEach items="${listE}" var="e">
                    <div class="Blog_list fade all ${e.category}">
                        <div class="row">

                            <div class="col-lg-6">
                                <img
                                    class="img-fluid"
                                    src="${e.thumnail_Image}"
                                    alt=""
                                    />
                            </div>
                            <div class="col-lg-6">
                                <p class="Category">Category ----- ${e.category}</p>
                                <h4 class="Tittle">

                                    <a href="BlogDetailsController?Slug=${e.slug}">${e.tittle}</a>
                                </h4>
                                <p class="Briefs_info text-muted">
                                    ${e.brieft}
                                </p>
                                <button class="btn btn-primary" type="button" >
                                    <a href="BlogDetailsController?Slug=${e.slug}">More Details</a>
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr/>
                </c:forEach>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${endP}" var="p">
                            <li class="page-item"><a class="page-link" href="BlogController?page=${p}">${p}</a></li>
                            </c:forEach>
                    </ul>
                </nav>
            </div>
            <!-- Main-Wrapper -->
        </section>
        <%@ include file = "Header/Footer.jsp" %>
        <!-- boostrap -->
        <script>
            const categoryTitle = document.querySelectorAll('.category-title');
            const allCategoryPosts = document.querySelectorAll('.all');

            for (let i = 0; i < categoryTitle.length; i++) {
                categoryTitle[i].addEventListener('click', filterPosts.bind(this, categoryTitle[i]));
            }

            function filterPosts(item) {
                changeActivePosition(item);
                for (let i = 0; i < allCategoryPosts.length; i++) {
                    if (allCategoryPosts[i].classList.contains(item.attributes.id.value)) {
                        allCategoryPosts[i].style.display = "block";
                    } else {
                        allCategoryPosts[i].style.display = "none";
                    }
                }
            }

            function changeActivePosition(activeItem) {
                for (let i = 0; i < categoryTitle.length; i++) {
                    categoryTitle[i].classList.remove('active');
                }
                activeItem.classList.add('active');
            }
            ;
        </script>
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

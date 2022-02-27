<%-- 
    Document   : PostDetails
    Created on : Feb 26, 2022, 4:12:23 AM
    Author     : lehun
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Director | Simple Tables</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="<%= request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" />
        <!-- font Awesome -->
        <link href="<%= request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet" />
        <!-- Ionicons -->
        <link href="<%= request.getContextPath()%>/css/ionicons.min.css" rel="stylesheet" />
        <!-- google font -->
        <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" />
        <!-- Theme style -->
        <link href="<%= request.getContextPath()%>/css/style.css" rel="stylesheet" />
        <link href="<%= request.getContextPath()%>/css/dialog.css" rel="stylesheet" type="text/css" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <style>
            .centrel{
                margin:10px 150px;
            }
        </style>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="../Views/Home.jsp" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Home
            </a>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>${sessionScope.account.fullname}</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li >
                            <a href="./Views/Home.jsp">
                                <i class="fa fa-home"></i> <span>Home Page</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/BlogController">
                                <i class="fa fa-rss"></i> <span>Blog</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/SettingController/Setting">
                                <i class="fa fa-gear"></i> <span>Setting List</span>
                            </a>
                        </li>
                        <li >
                            <a href="<%= request.getContextPath()%>/Group/GroupList">
                                <i class="fa fa-user"></i> <span>Group List</span>
                            </a>
                        </li>
                        <li class="active">
                            <a href="<%= request.getContextPath()%>/SupportTypeController/SupportType">
                                <i class="fa fa-user"></i> <span>Support Type</span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="panel">
                            <header class="panel-heading">
                                Post Add
                            </header>
                            <div class="panel-body">
                                <header class="panel-heading">
                                   Add
                                </header>
                                <div class="panel-body" style="width: 50%">
                                    <form action="../PostController/AddSubMit" method="post">

                                        <div class="row ">
                                            <div class="form-group col-lg-12">
                                                <label for="Slug">Slug</label>
                                                <input type="text" class="form-control" name="Slug" required="" >
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="form-group col-lg-12">
                                                <label for="thumnail">Thumnail</label>
                                                <img id="images" class="centrel" src="#" width="150px" height="150px" alt="No Images">
                                                <input id="IMGURL" type="text" class="form-control" name="ThumnailIMG" required="" onmousemove="Imga()">
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="form-group col-lg-12">
                                                <label for="Category">Category name</label>
                                                <input type="text" class="form-control" name="Category" required="" >
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="form-group col-lg-12">
                                                <label for="Author">Author</label>
                                                <input type="text" class="form-control" name="Author"  required="" >
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="Tittle">Tittle</label>
                                                <input type="text" class="form-control" name="Tittle" required="" >
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="Brieft">Brieft</label>
                                                <textarea type="text" class="form-control" name="Brieft" required="" rows="4"cols="50" ></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="Content" >Content</label>
                                                <textarea class="form-control" name="Content" required="" rows="4" cols="50"></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="Status">Status</label><br>
                                                <input  type="radio" name="Flag" value="1" id="rbStatus" > <span style="margin-right: 2rem;" >Active</span>
                                                <input type="radio" name="Flag" value="0" id="rbStatus" checked="checked"> Deactivate
                                            </div>
                                        </div>
                                        <div class=" form-group row col-lg-12 text-center">
                                            <button type="submit" id="submit-btn" class="btn btn-info">Add New</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="table-foot">
                        <ul class="pagination pagination-sm no-margin pull-right">

                        </ul>
                    </div>
                    </div>
                    </div>
                    </div>
                </section><!-- /.content -->
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/0.10.0/lodash.min.js"></script>
        <script type="text/javascript">
            function deleteByID(id) {
                if (confirm("Do you really want to delete profile?")) {
                    window.location = "grouplist?typef=delete" + "&id=" + id;
                }
            }
            function Imga(){
                var images = document.getElementById("images");
                images.src= document.getElementById("URLIMG").value;
            }
        </script>
    </body>
</html>
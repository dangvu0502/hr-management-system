<%-- 
    Document   : PostList
    Created on : Feb 26, 2022, 2:23:46 AM
    Author     : lehun
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Director | Simple Tables</title>
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport"
            />
        <meta name="description" content="Developed By M Abdur Rokib Promy" />
        <meta
            name="keywords"
            content="Admin, Bootstrap 3, Template, Theme, Responsive"
            />
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
                            <p>#</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li >
                            <a href="../Views/Home.jsp">
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
                            <header class="panel-heading">Post Details</header>
                            <div class="panel-body" style="width: 50%">
                                <header class="panel-heading">
                                    <form action="../PostController/Views" method="" >
                                        <div class="input-group">
                                            <div class="btn btn-md btn-default" style="width: 150px; pointer-events: none;"><span>Search by Title</span></div>
                                            <input id="SearchPost" type="text" name="SearchPost"value="${SearchPost}" class="form-control input-md" style="width: 450px;" placeholder="Enter title to search" />
                                            <button type="submit" class="btn btn-md btn-default">Search</button>
                                        </div>
                                    </form>
                                </header>
                            </div>
                            <div class="panel-body">
                                <div class="pull-right">
                                    <div class="btn btn-success">
                                        <a id="opener" href="../PostController/Add" style="color: white">Add</a>
                                    </div>
                                </div>
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="width: 10px">id</th>
                                        <th>Thumnail</th>
                                        <th>Tittle</th>
                                        <th>post Category</th>
                                        <th>author</th>       
                                        <th>Content</th>                           
                                        <th>featured</th>
                                        <th>Flag</th>
                                    </tr>

                                    <c:forEach items="${listE}" var="e">
                                        <tr>
                                            <td><span>${e.id}</span></td>
                                            <td>      <img
                                                    class="img-fluid"
                                                    src="${e.thumnail_Image}"
                                                    alt="" width="50px" height="50px"
                                                    /></td>
                                            <td><span>${e.tittle}</span></td>
                                            <td><span>${e.category}</span></td>                 
                                            <td><span>${e.author}</span></td>                          
                                            <td><span>${e.content}</span></td>   
                                            <td><span><a href="../PostDetailsController?Slug=${e.slug}">Update</a></span>/
                                                <span><a href="../BlogDetailsController?Slug=${e.slug}">View</a></span>
                                            </td>   
                                            <td><span> <c:if test = "${e.flag==1}"> <span class="badge bg-green">Active</span></c:if>
                                                    <c:if test = "${e.flag==0}"><span class="badge bg-red">Deactivate</span></c:if></span>
                                                    &nbsp;&nbsp;
                                                    <a href="../PostController/Status?Slug=${e.slug}&Flag=${e.flag}">   <span class="glyphicon glyphicon-retweet"></span></a>
                                            </td>   
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="table-foot">
                                    <ul class="pagination pagination-sm no-margin pull-right">
                                        <c:forEach begin="1" end="${endP}" var="p">
                                            <c:choose>
                                                <c:when test="${empty SearchPost }">
                                                    <li class="page-item"><a class="page-link" href="../PostController/Views?page=${p}">${p}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="../PostController/Views?page=${p}&SearchPost=${SearchPost}">${p}</a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                        </c:forEach>
                                    </ul>
                                </div>
                            </div> 
                        </div>
                    </div> 
                </section>
                <!-- /.content -->

                <div class="footer-main">Copyright &copy Director, 2014</div>
            </aside>
            <!-- /.right-side -->
        </div>
        <!-- ./wrapper -->
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <link
            rel="stylesheet"
            href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css"
            />
        <script
            type="text/javascript"
            src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/0.10.0/lodash.min.js"
        ></script>
        <script type="text/javascript"></script>
    </body>
</html>

<%-- 
    Document   : Treebar
    Created on : Feb 27, 2022, 5:47:02 PM
    Author     : lehun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <aside class="left-side sidebar-offcanvas">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="../userimg/${sessionScope.account.avatar}" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>${sessionScope.account.fullname}</p>

                        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                    </div>
                </div>
                <ul class="sidebar-menu">
                    <li >
                        <a href="<%= request.getContextPath()%>/Views/Home.jsp">
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
                    <c:if test="${sessionScope.account.role_id eq 3}">
                        <a  href="<%= request.getContextPath()%>/Project/List">Project</a>
                        <a  href="<%= request.getContextPath()%>/Contract/Details">Contract</a>
                        <a  href="<%= request.getContextPath()%>/User/NewUser">New User</a>
                    </c:if> 
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
    </body>
</html>

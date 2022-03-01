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
        <link rel="icon" href="<%= request.getContextPath()%>/img/HoneyBee.png" />
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
                    <li >
                        <a href="<%= request.getContextPath()%>/User/ChangePassword" >
                            <i class="fa fa-cogs"></i> <span>Change password</span>
                        </a>
                    </li>
                    <li > 
                        <a href="<%= request.getContextPath()%>/Timesheet/TimesheetList">
                            <i class="fa fa-tablet"></i> <span>TimeSheet List</span>
                        </a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath()%>/BlogController">
                            <i class="fa fa-rss"></i> <span>Blog</span>
                        </a>
                    </li>
                    <c:if test="${sessionScope.account.role_id eq 1}"> 
                        <li> 
                            <a href="<%= request.getContextPath()%>/SettingController/Setting">
                                <i class="fa fa-folder-o"></i> <span>Setting List</span>
                            </a>
                        </li>

                        <li> 
                            <a href="<%= request.getContextPath()%>/SupportTypeController/SupportType">
                                <i class="fa fa-folder-o"></i> <span>Support Type</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account.role_id eq 2}"> 
                        <li> 
                            <a href="<%= request.getContextPath()%>/Project/List">
                                <i class="fa fa-folder-o"></i> <span>Project List</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/Project/Add">
                                <i class="fa fa-folder-o"></i> <span>Project Add</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/UserListController/UserList">
                                <i class="fa fa-folder-o"></i> <span>User List</span>
                            </a>
                        </li>

                    </c:if>
                    <c:if test="${sessionScope.account.role_id eq 3}">
                        <li> 
                            <a href="<%= request.getContextPath()%>/Project/List">
                                <i class="fa fa-folder-o"></i> <span>Project List</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/User/NewUser">
                                <i class="fa fa-plus-square"></i> <span>New User</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/PostController/Views">
                                <i class="fa fa-tablet"></i> <span>Post List</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/Contract/Details">
                                <i class="fa fa-glass"></i> <span>Contract Details</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/Contract/Add">
                                <i class="fa fa-glass"></i> <span>Contract Add</span>
                            </a>
                        </li>
                    </c:if> 
                    <c:if test="${sessionScope.account.role_id eq 4}">

                        <li> 
                            <a href="<%= request.getContextPath()%>/Project/List">
                                <i class="fa fa-tablet"></i> <span>Project List</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/Project/Add">
                                <i class="fa fa-tablet"></i> <span>Project Add</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/Group/GroupList">
                                <i class="fa fa-folder-o"></i> <span>Group List</span>
                            </a>
                        </li>
                        <li> 
                            <a href="<%= request.getContextPath()%>/Request/RequestList">
                                <i class="fa fa-tablet"></i> <span>Request List</span>
                            </a>
                        </li>


                    </c:if>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
    </body>
</html>

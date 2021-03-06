<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Director | Simple Tables</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- bootstrap 3.0.2 -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" />
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' />
        <!-- Theme style -->
        <link href="../css/style.css" rel="stylesheet"/>
        <link href="../css/dialog.css" rel="stylesheet" type="text/css"/>
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
            <a href="Views/Home.jsp" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Home
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <!--            <nav class="navbar navbar-static-top" role="navigation">
                             Sidebar toggle button
                            <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <div class="navbar-right">
                                <ul class="nav navbar-nav">
                                     Messages: style can be found in dropdown.less
                                    <li class="dropdown messages-menu">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-envelope"></i>
                                            <span class="label label-success">4</span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li class="header">You have 4 messages</li>
                                            <li>
                                                 inner menu: contains the actual data 
                                                <ul class="menu">
                                                    <li> start message 
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar3.png" class="img-circle" alt="User Image"/>
                                                            </div>
                                                            <h4>
                                                                Support Team
                                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li> end message 
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar2.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Director Design Team
                                                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Developers
                                                                <small><i class="fa fa-clock-o"></i> Today</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar2.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Sales Department
                                                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Reviewers
                                                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li class="footer"><a href="#">See All Messages</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown tasks-menu">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-tasks"></i>
                                            <span class="label label-danger">9</span>
                                        </a>
                                        <ul class="dropdown-menu">
            
                                            <li class="header">You have 9 tasks</li>
                                            <li>
                                                 inner menu: contains the actual data 
                                                <ul class="menu">
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Design some buttons
                                                                <small class="pull-right">20%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-success" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">20% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Create a nice theme
                                                                <small class="pull-right">40%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-danger" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">40% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Some task I need to do
                                                                <small class="pull-right">60%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-info" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">60% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Make beautiful transitions
                                                                <small class="pull-right">80%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-warning" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">80% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                </ul>
                                            </li>
                                            <li class="footer">
                                                <a href="#">View all tasks</a>
                                            </li>
            
                                        </ul>
                                    </li>
                                     User Account: style can be found in dropdown.less 
                                    <li class="dropdown user user-menu">
            
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-user"></i>
                                            <span>Jane Doe <i class="caret"></i></span>
                                        </a>
                                        <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                            <li class="dropdown-header text-center">Account</li>
            
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-clock-o fa-fw pull-right"></i>
                                                    <span class="badge badge-success pull-right">10</span> Updates</a>
                                                <a href="#">
                                                    <i class="fa fa-envelope-o fa-fw pull-right"></i>
                                                    <span class="badge badge-danger pull-right">5</span> Messages</a>
                                                <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                                                    <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                                                <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span class=
                                                                                                                  "badge pull-right">11</span> FAQ</a>
                                            </li>
            
                                            <li class="divider"></li>
            
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-user fa-fw pull-right"></i>
                                                    Profile
                                                </a>
                                                <a data-toggle="modal" href="#modal-user-settings">
                                                    <i class="fa fa-cog fa-fw pull-right"></i>
                                                    Settings
                                                </a>
                                            </li>
            
                                            <li class="divider"></li>
            
                                            <li>
                                                <a href="#"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                                            </li>
                                        </ul>
            
                                    </li>
                                </ul>
                            </div>
                        </nav>-->
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
                    <!-- search form -->
                    <!--                    <form action="#" method="get" class="sidebar-form">
                                            <div class="input-group">
                                                <input type="text" name="q" class="form-control" placeholder="Search..."/>
                                                <span class="input-group-btn">
                                                    <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                                                </span>
                                            </div>
                                        </form>-->
                    <!--                    <ul class="sidebar-menu">
                                            <li>
                                                <a href="index.html">
                                                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="general.html">
                                                    <i class="fa fa-gavel"></i> <span>General</span>
                                                </a>
                                            </li>
                    
                                            <li>
                                                <a href="basic_form.html">
                                                    <i class="fa fa-globe"></i> <span>Basic Elements</span>
                                                </a>
                                            </li>
                    
                                            <li class="active">
                                                <a href="simple.html">
                                                    <i class="fa fa-glass"></i> <span>Simple tables</span>
                                                </a>
                                            </li>
                    
                                        </ul>-->
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
                                Absence Request List
                            </header>
                            <div class="panel-body table-responsive">
                                <div class="box-tools m-b-15">
                                    <div class="row">
                                        <div class="col-lg-8">
                                            <form action="" method="post" >
                                                <div class="input-group">
                                                    <div class="btn btn-md btn-default" style="width: 150px; pointer-events: none;" onclick="dateHideShow()"><span>Search by Title</span></div>
                                                    <input id="title" type="text" name="title" class="form-control input-md" style="width: 450px;" placeholder="Enter title to search" onclick="dateHideShow()"/>
                                                    <br>
                                                    <div id="advanced" style="display: none;">
                                                        <br>
                                                        <div class="row">
                                                            <div class="col-lg-2"></div>
                                                            <div class="col-lg-8">
                                                                <div class="col-md-1"></div>
                                                                <div class="col-md-7">
                                                                    <label class="text-left" for="from" style="width: 150px;">From</label><br>
                                                                    <input type="date" class="form-control" id="fromDate" style="width: 200px;" name="fromDate">
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <label class="text-left" for="to" style="width: 150px;">To</label><br>
                                                                    <input type="date" class="form-control" id="toDate" style="width: 200px;" name="toDate">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="row">
                                                            <div class="col-lg-2"></div>
                                                            <div class="col-lg-8">
                                                                <div class="col-md-1"></div>
                                                                <div class="col-md-7">
                                                                    <label class="text-left" for="type" style="width: 150px;">Type</label><br>
                                                                    <select class="form-control input-md" style="width: 200px;" name="type" id="type">
                                                                        <option value="">Choose Type</option>
                                                                        <c:forEach var="type" items="${AbsenceType}">
                                                                            <option value="${type.key}">${type.value}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <label class="text-left" for="status" style="width: 150px;">Status</label><br>
                                                                    <select class="form-control input-md" style="width: 200px;" name="status" id="status">
                                                                        <option value="">Choose Status</option>
                                                                        <c:forEach var="status" items="${AbsenceStatus}">
                                                                            <option value="${status.key}">${status.value}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-2">
                                            <button onclick="window.open('http://localhost:8080/HR_Management/Timesheet/NewTimesheet', '_blank')" class="btn btn-md btn-primary" style="width: 150px; ">Add new timesheet</button>
                                        </div>
                                    </div>
                                </div>

                                <table class="table table-bordered" id="absenceTable">
                                    <tr>
                                        <th>Request Date</th>
                                        <th>Type</th>
                                        <th style="width: 300px;">Title</th>
                                        <th>From</th>
                                        <th>To</th>
                                        <th>Duration</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                    <c:forEach items="${listA}" var="a">
                                        <tr>
                                            <td>${a.request_date}</td>
                                            <td>${AbsenceType[a.type]}</td>
                                            <td>${a.title}</td>
                                            <td>${a.from}</td>
                                            <td>${a.to}</td>
                                            <td>${a.duration}h</td>
                                            <td>
                                                <c:if test = "${a.status == 1}">
                                                    <span class="badge bg-aqua">${AbsenceStatus[a.status]}</span>
                                                </c:if>
                                                <c:if test = "${a.status == 2}">
                                                    <span class="badge bg-green">${AbsenceStatus[a.status]}</span>
                                                </c:if>
                                                <c:if test = "${a.status == 3}">
                                                    <span class="badge bg-red">${AbsenceStatus[a.status]}</span>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test= "${a.status == 1}">
                                                    <a id="delete" onclick="deleteAbsence(${a.id})" href="#"><span class="glyphicon glyphicon-trash"></span></a>
                                                    &nbsp;&nbsp;    
                                                </c:if>
                                                <a class="edit" href="../SettingController/EditView?id=${s.id}&type=${s.type}&value=${s.value}&order=${s.order}&status=${s.status}&note=${s.note}" return false;"><span class="glyphicon glyphicon-edit"></span></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="table-foot">
                                <ul class="pagination pagination-sm no-margin pull-right">
                                    <c:forEach begin="1" end="${endP}" var="p">
                                        <li><a id="page-active" href="#" onclick="page(${p})">${p}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div>
                </section><!-- /.content -->
                <!-- /.Dialog -->
                <!--                <div id="dialog">
                                    <div style="display: flex; justify-content: center;  margin: 2rem;"><img style=" width: 100px; height: 100px;" src="https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png" alt="Avatar" > </div>
                                    <div style="display: flex; justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>ID</label></div>
                                        <div style="margin-left: 4rem;"><input  disabled="true" style="border-radius:8px; " type="text" id="txtId"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>UserName</label></div>
                                        <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtUserName"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>FullName</label></div>
                                        <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtFullName"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>PassWord</label></div>
                                        <div style="margin-left: 4rem;"><input style="border-radius:8px; " type="password" id="txtPassWord"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>Gmail</label></div>
                                        <div style="margin-left: 4rem;" ><input style="border-radius:8px; " type="text" id="txtGmail"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>Status</label></div>
                                        <div style="padding-right: 2rem;" >
                                            <input  type="radio" name="foo" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Active</span>
                                            <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                                        </div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>Type</label></div>
                                        <div style="margin-left: 4rem;" >
                                            <select name="cbbType" id="cbbType" style="width: 215px;height: 30px;border-radius: 8px;">
                                                <option value="0">Admin</option>
                                                <option value="1">HR</option>
                                                <option value="2">Manager</option>
                                                <option value="3">Staff</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>-->
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/0.10.0/lodash.min.js"></script>
        <script type="text/javascript"></script>
        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>

        <script src="../js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="../js/Director/app.js" type="text/javascript"></script>
        <script>
                                                        function dateHideShow() {
                                                            var x = document.getElementById("advanced");
                                                            if (x.style.display === "none") {
                                                                x.style.display = "block";
                                                            } else {
                                                                x.style.display = "none";
                                                            }
                                                        }
                                                        
                                                        const replaceWhitespace = (str = '') => {
                                                            let res = '';
                                                            const {length} = str;
                                                            for (let i = 0; i < length; i++) {
                                                                const char = str[i];
                                                                if (!(char === ' ')) {
                                                                    res += char;
                                                                } else {
                                                                    res += '_';
                                                                }
                                                                ;
                                                            }
                                                            ;
                                                            return res;
                                                        };

                                                        function page(number) {
                                                            var pageNumber = number;
                                                            var fromDate = document.getElementById('fromDate').value;
                                                            var toDate = document.getElementById('toDate').value;
                                                            var status = document.getElementById('status').value;
                                                            var type = document.getElementById('type').value;
                                                            var title = document.getElementById('title').value;
                                                            var link = "http://localhost:8080/HR_Management/AbsencesController/Absence?";
                                                            link += "page=" + pageNumber;
                                                            link += "&";
                                                            link += "fromDate=" + fromDate;
                                                            link += "&";
                                                            link += "toDate=" + toDate;
                                                            link += "&";
                                                            link += "status=" + status;
                                                            link += "&";
                                                            link += "type=" + type;
                                                            link += "&";
                                                            link += "title=" + replaceWhitespace(title);
                                                            $('#absenceTable').load(link + " " + "#absenceTable");
                                                        }

                                                        function deleteAbsence(id) {
                                                            var cf = confirm("Are you sure to delete?");
                                                            var pageNumber = document.getElementById('page-active').innerHTML;
                                                            var fromDate = document.getElementById('fromDate').value;
                                                            var toDate = document.getElementById('toDate').value;
                                                            var status = document.getElementById('status').value;
                                                            var type = document.getElementById('type').value;
                                                            var title = document.getElementById('title').value;
                                                            if (cf) {
                                                                //Logic to delete the item
                                                                $.ajax({

                                                                    type: "POST",

                                                                    url: "http://localhost:8080/HR_Management/AbsencesController/Delete",

                                                                    data: {id: id,
                                                                        fromDate: fromDate,
                                                                        toDate: toDate,
                                                                        status: status,
                                                                        type: type,
                                                                        title: title,
                                                                        page: pageNumber},

                                                                    success: function (number) {
                                                                        page(number);
                                                                    }

                                                                });


                                                            }
                                                        }


                                                        $(document).ready(function () {
                                                            $('#fromDate').change(function () {
                                                                page(1);
                                                            });
                                                            $('#toDate').change(function () {
                                                                page(1);
                                                            });
                                                            $('#status').change(function () {
                                                                page(1);
                                                            });
                                                            $('#type').change(function () {
                                                                page(1);
                                                            });
                                                            $('#title').keyup(function () {
                                                                page(1);
                                                            });

//                                                                setInterval(function () {
//                                                                    var number = document.getElementById('page-active').innerHTML;
//                                                                    console.log(number);
//                                                                    page(number);
//                                                                }, 10000);
                                                        });




        </script>
    </body>
</html>

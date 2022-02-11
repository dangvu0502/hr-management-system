<%@page import="Models.User"%>
<%@page import="Context.TrippleDes"%>
<!-- NavBar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img
        class="navbar-brand"
        src="../img/Honey Bee.png"
        alt=""
        height="150"
        width="150"
        />
    <a class="navbar-brand" href="#"> HR Management</a>
    <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup"
        aria-expanded="false"
        aria-label="Toggle navigation"
        >
        <span class="navbar-toggler-icon"></span>
    </button>
    <% Models.User acc = (User) session.getAttribute("account");
        TrippleDes td = new TrippleDes();
    %>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <!-- If User Log-in -->
        <div class="navbar-nav ml-auto mr-auto">
            <a class="nav-item nav-link active" href="#"
               >Home <span class="sr-only">(current)</span></a
            >
            <c:if test="${sessionScope.account.role_id == 1}"> 
                <a class="nav-item nav-link" href="../SettingListController">Setting List</a>
                <a class="nav-item nav-link" href="../grouplist">Group List</a>
                <a class="nav-item nav-link" href="../User/NewUser">New User</a>
            </c:if>
            <c:if test="${sessionScope.account.role_id == 2}"> 
                <a class="nav-item nav-link" href="../Contract/Details">Contract</a>
            </c:if>
            <a class="nav-item nav-link" href="#">Our Team</a>

        </div>
        <!-- If User Log-in -->
        <%                    if (acc == null) {
        %>
        <div class="navbar-nav ml-auto">
            <a class="nav-item nav-link" href="../Account/Login">Login</a>
            <a class="nav-item nav-link " href="../Account/Register">Sign Up</a>
        </div>
        <%}%>
        <!--       If User Loggin Successfully-->
        <%
            if (acc != null) {
        %>
        <div class="navbar-nav ml-auto">
            <a href="../User/ChangePassword" class="nav-item nav-link" >Change password</a>
            <a href="../EditProfile" data-toggle="modal" data-target="#myModal">  <img
                    src="../userimg/${sessionScope.account.avatar}"
                    alt="Avatar" width="50" height="50"
                    /></a>
            <p class="nav-item nav-link text-center"> Hello ${sessionScope.account.username}</p>     
            <a href="../Logout" class="nav-item nav-link">Log Out</a>
        </div>
        <%}%>
    </div>
</nav>
<!-- Navbar -->
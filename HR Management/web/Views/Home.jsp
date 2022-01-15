<%-- 
    Document   : Home
    Created on : Jan 14, 2022, 2:27:08 PM
    Author     : lehun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
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
    <link rel="icon" href="../img/Honey Bee.png" />
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
        padding: 250px;
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
   
    </style>
  </head>
  <body>
    <!-- Nav Bar -->
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
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
       <!-- If User Log-in -->
        <div class="navbar-nav ml-auto mr-auto">
          <a class="nav-item nav-link active" href="#"
            >Home <span class="sr-only">(current)</span></a
          >
          <a class="nav-item nav-link" href="#">Setting List</a>
          <a class="nav-item nav-link" href="#">User Profile</a>
        </div>
       <!-- If User Log-in -->

        <div class="navbar-nav ml-auto">
          <a class="nav-item nav-link" href="#">Login</a>
          <a class="nav-item nav-link " href="#">Sign Up</a>
        </div>
      </div>
    </nav>
    <!-- Nav bar -->
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
        © 2021 Copyright:
        <a class="text-reset" href="#">HR Management</a>
      </div>
      <!-- Copyright -->
    </footer>
    <!-- Footer -->
  </body>
</html>

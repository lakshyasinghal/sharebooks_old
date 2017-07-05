<%@page import="java.util.*"%>

<html>

<head>
	<title>Sign into sharebooks</title>

	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/login/css/loginPage.css">
	<script src="<%=request.getContextPath()%>/view/login/js/loginPage.js"></script>
	
</head>

<body>

	<% Object obj = null; %>

	<div class="container">

		<div class="row full-height">

				<div id="signIn" class="col-sm-9 col-md-9">
					<div class="row margin-top-100 full-height">
						<div class="col-sm-4 col-md-4"></div>
						<div class="col-sm-5 col-md-5">
							<form class="form-signin">
								<h2 class="form-signin-heading">Please sign in</h2>
								<input type="email" id="signIn_username" class="form-control margin-top-10" name="username" placeholder="Username" required autofocus autocomplete="off">
								<input type="password" id="signIn_password" class="form-control margin-top-5" name="password" placeholder="Password" required autocomplete="off">
								<button type="button" id="signInButton" class="btn btn-lg btn-primary btn-block margin-top-20">Sign In</button>
								<h4 id="signInMessageContainer"></h4>
				
							</form>
						</div>
					</div>
				</div>



				<div id="signUp" class="col-sm-3 col-md-3 component-border-left">
					<div class="row margin-top-50 full-height">
						<div class="col-sm-1 col-md-1"></div>
						<div class="col-sm-11 col-md-11">
							<form class="form-signin">
								<h2 class="form-signin-heading">Create a new account</h2>
								
								<input type="email" id="username" class="form-control margin-top-10" name="username" placeholder="Username" required autocomplete="off">

								<input type="password" id="password" class="form-control margin-top-10" name="password" placeholder="Password" required autocomplete="off">

								<input type="text" id="name" class="form-control margin-top-10" name="name" placeholder="Name" required>

								<input type="text" id="birthday" class="form-control margin-top-10" name="birthday" placeholder="dd/mm/yyyy" required>

								<input type="text" id="address" class="form-control margin-top-10" name="address" placeholder="address" required autofocus>

								<input type="text" id="city" class="form-control margin-top-10" name="city" placeholder="City" required autofocus>

								<input type="text" id="state" class="form-control margin-top-10" name="state" placeholder="State" required autofocus>

								<input type="text" id="pincode" class="form-control margin-top-10" name="pincode" placeholder="Pincode" required autofocus>

								<input type="number" id="mobileNo" class="form-control margin-top-10" name="mobileNo" placeholder="Mobile Number" required>

								<button type="button" id="registerUserButton" class="btn btn-lg btn-danger btn-block margin-top-20">Register Me</button>

								<h4 id="registerUserMessageContainer"></h4>
							</form>
						</div>
						<!-- <div class="col-sm-1 col-md-1"></div> -->
					</div>
				</div>

		</div>


	</div>

</body>

</html>
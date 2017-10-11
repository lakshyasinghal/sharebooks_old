<html>

<head>
	<title>Cover Page</title>


	<%@include file="../../include/lib.jsp"%>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/cover/css/coverPage.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/cover/js/coverPage.js"></script>

</head>

<body>

	<div id="header">
		<span id="title">SHAREBOOKS</span>	

		<!-- <button type="button" id="signInButton" class="btn btn-lg btn-primary btn-block margin-top-20">Sign In</button> -->
		<div id="signUpButton">
			<span>Sign Up</span>
		</div>
	</div>


	<div id="signInPanel" class="panel">
		<span class="heading">Login Credentials</span>
		<input type="email" id="signIn_username" class="form-control margin-top-10 first" name="username" placeholder="Username" required autofocus autocomplete="off">
		<input type="password" id="signIn_password" class="form-control margin-top-5" name="password" placeholder="Password" required autocomplete="off">

	<!-- <div id="signInButton">
			<span>Sign In</span>
		</div> -->

		<button type="button" id="signInButton" class="btn btn-lg btn-danger btn-block margin-top-20">Sign In</button>
		
		<h4 id="signInMessageContainer"></h4>
	</div>




	<div id="signUpPanel" class="panel">
		<div class="container">
		
			<h3 class="form-signin-heading">Create a new account</h3>

			<input type="text" id="id" class="form-control hidden" name="username" placeholder="Username" value="-1" required autocomplete="off">

			<input type="email" id="username" class="form-control" name="username" placeholder="Username" required autocomplete="off">

			<input type="password" id="password" class="form-control" name="password" placeholder="Password" required autocomplete="off">
			<input type="text" id="name" class="form-control" name="name" placeholder="Name" required>
			<input type="text" id="birthday" class="form-control" name="birthday" placeholder="dd/mm/yyyy" required>
			<input type="text" id="address" class="form-control" name="address" placeholder="address" required autofocus>
			<input type="text" id="city" class="form-control" name="city" placeholder="City" required autofocus>
			<input type="text" id="state" class="form-control" name="state" placeholder="State" required autofocus>
			<input type="text" id="pincode" class="form-control" name="pincode" placeholder="Pincode" required autofocus>
			<input type="number" id="mobileNo" class="form-control" name="mobileNo" placeholder="Mobile Number" required>
			<button type="button" id="userSignUpButton" class="btn btn-lg btn-danger btn-block">Register Me</button>
			<h4 id="userRegistrationMessageContainer"></h4>

		</div>
			
	</div>


</body>
</html>
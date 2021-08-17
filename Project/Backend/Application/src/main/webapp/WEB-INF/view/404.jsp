<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en"> 
<head>
    <title>Error Page</title>
    
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- FontAwesome JS-->
    <script defer src="<c:url value="/resources/plugins/fontawesome/js/all.min.js"/>"></script>
    
    <!-- App CSS -->  
    <link id="theme-style" rel="stylesheet" href="<c:url value="/resources/css/portal.css"/>">

</head> 

<body class="app app-404-page">   	
   
    <div class="container mb-5">
	    <div class="row">
		    <div class="col-12 col-md-11 col-lg-7 col-xl-6 mx-auto">
			    <div class="app-branding text-center mb-5">
		            <a class="app-logo" href="./"><img class="logo-icon me-2" src="<c:url value="/resources/images/app-logo.svg"/>" alt="logo"><span class="logo-text">PORTAL</span></a>
	
		        </div><!--//app-branding-->  
			    <div class="app-card p-5 text-center shadow-sm">
				    <h1 class="page-title mb-4">404<br><span class="font-weight-light">Page Not Found</span></h1>
				    <div class="mb-4">
					    Sorry, we can't find the page you're looking for. 
				    </div>
				    <a class="btn app-btn-primary" href="./">Go to home page</a>
			    </div>
		    </div><!--//col-->
	    </div><!--//row-->
    </div><!--//container-->
   
</body>
</html> 


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><spring:message code="label.newsList"/></title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css"
		href="<c:url value="/resources/css/bootstrap.min.css"/>" />
		
    <link rel="stylesheet" type="text/css"
		href="<c:url value="/resources/css/shop-item.css"/>" />
		
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  <script type="text/javascript">
	function update(){
		var change = confirm('<spring:message code="label.really"/>');
		if (change == true){
			
		}else{
 			document.write('<spring:message code="label.canceled"/>');
			window.history.back();
		}
	}
</script>
</head>

<body>


   <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="list"><spring:message code="label.header"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li>
              <a class="nav-link" a href="?lang=en"><spring:message code="label.english"/>
              </a>
            </li>
            <li>
              <a class="nav-link" href="?lang=ru"><spring:message code="label.russian"/>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <div class="col-lg-3">
          <h2 class="my-4"><spring:message code="label.news"/></h2>
          <div class="list-group">
          	<form method="get" action="list" >
				<button type="submit" class="list-group-item active"><spring:message code="label.newsList"/></button>
			</form>
   						
			<form method="get" action="add">
				<button type="submit" class="list-group-item"><spring:message code="label.addNews"/></button>
			</form>
          </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

          <div class="card card-outline-secondary my-4">
            <div class="card-header">
              <spring:message code="label.news"/> >> <spring:message code="label.newsList"/>
            </div>
            
            
            <form:form method="post" action="delete">
            <c:forEach items="${newsForm.newsList}" var="news">

			<c:url var="view" value="/news/view">
				<c:param name="newsId" value="${news.newsId}" />
			</c:url>
			<c:url var="edit" value="/news/edit">
				<c:param name="newsId" value="${news.newsId}" />
			</c:url>
			
            <div class="card-body">
            <div class="row">
				<div class="col-md-11">
              <p>${news.title}</p>
              <hr>
              <p>${news.brief}</p>
             <hr>
             </div>
             <div class="col-md-1">
             <input type="checkbox" name="newsId" value="${news.newsId}">
             </div>
             </div>

             <div class="row">
				<div class="col-md-7">
            		<small class="text-muted"><fmt:formatDate pattern = "MM/dd/yyyy" value = "${news.date}" /></small>
                </div>
                <div class="col-md-5">

              		<div class="btn-toolbar">
  						<div class="btn-group btn-group-xs">
  							<a href="${view}" class="btn btn-success"><spring:message code="label.view"/></a>
			   				<a href="${edit}" class="btn btn-warning"><spring:message code="label.edit"/></a>
						</div>
					</div>
				</div>
			</div>
			
			</div>
            </c:forEach>

		<button type="submit" class="btn btn-danger" onclick="update()"><spring:message code="label.delete"/></button>
		</form:form>



          </div>
          <!-- /.card -->

        </div>
        <!-- /.col-lg-9 -->

      </div>

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white"><spring:message code="label.copyright"/> &copy; <spring:message code="label.footer"/></p>
      </div>
      <!-- /.container -->
    </footer>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.js"></script>
</body>
</html>
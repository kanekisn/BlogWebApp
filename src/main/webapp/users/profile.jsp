<%@ page import="org.blogapp.models.UserEntity" %>
<%@ page import="org.blogapp.models.ArticleEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class = "bg-dark">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand text-white" href="">Hibernate</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="${pageContext.request.contextPath}/">Articles</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="${pageContext.request.contextPath}/user/profile/<%=request.getSession(false).getAttribute("username")%>">Profile</a>
                </li>
            </ul>
            <span class="navbar-text text-white">
                <%UserEntity user = (UserEntity) request.getAttribute("user");%>
                Hello, <%=user.getUsername()%>
            </span>
            <div class = "d-flex justify-content-right" style="margin-left: 20px;">
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/logout" type="submit">Log-out</a>
            </div>
        </div>
    </div>
</nav>

<div class = "container">
    <h3 class = "mt-5 mb-4 text-white">Profile</h3>
    <hr style="color: white; margin-bottom: 50px;">
    <div class="mb-3 row d-flex justify-content-center">
        <p class="col-sm-2 text-white">User name</p>
        <div class="col-sm-4">
            <p class = "text-white col-sm-4"><%=user.getUsername()%></p>
        </div>
    </div>
    <div class="mb-3 row d-flex justify-content-center">
        <p class="col-sm-2 text-white">Access</p>
        <div class="col-sm-4">
            <p class = "text-white"><%=user.getUserType()%></p>
        </div>
    </div>
    <div class="mb-3 row d-flex justify-content-center">
        <p class="col-sm-2 text-white">Date joined</p>
        <div class="col-sm-4">
            <p class = "text-white"><%=user.getJoined_date()%></p>
        </div>
    </div>
    <div class="mb-3 row d-flex justify-content-center">
        <p class="col-sm-2 text-white">Articles count</p>
        <div class="col-sm-4">
            <p class = "text-white"><%=user.getArticles().size()%></p>
        </div>
    </div>
</div>
<div class="container">

    <footer class="bg-dark text-center text-lg-start text-white">
        <div class="text-center p-3 fixed-bottom">
            © 2024 Copyright:
            <a class="text-white" href="">Hibernate</a>
        </div>
    </footer>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
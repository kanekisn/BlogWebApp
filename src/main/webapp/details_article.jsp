<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.blogapp.models.ArticleEntity" %>
<%@ page import="org.blogapp.utils.UserAccess" %>
<%ArticleEntity article = (ArticleEntity) request.getAttribute("article");%>
<html>
    <head>
        <title>Articles</title>
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
                    <%if(UserAccess.loggedIn(request)) {%>
                    <li class="nav-item">
                        <a class="nav-link active text-white" aria-current="page" href="${pageContext.request.contextPath}/user/profile/<%=request.getSession(false).getAttribute("username")%>">Profile</a>
                    </li>
                    <%}%>
                </ul>
                <span class="navbar-text text-white">
                    <%if(!UserAccess.loggedIn(request)) {%>
                        Hello, Guest!
                    <%} else{%>
                        Hello, <%=request.getSession(false).getAttribute("username")%>
                    <%}%>
                </span>
                <div class = "d-flex justify-content-right" style="margin-left: 20px;">
                    <%if(!UserAccess.loggedIn(request)) {%>
                    <a class="btn btn-outline-info" href="${pageContext.request.contextPath}/login" type="submit">Log-in</a>
                    <%} else{%>
                    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/logout" type="submit">Log-out</a>
                    <%}%>
                </div>
            </div>
        </div>
    </nav>

        <div class="container mt-3">
            <h4 class = "mt-5 mb-3 text-center text-white"><%=article.getTitle()%></h4>
            <hr>
            <p class = "text-center text-white"><%=article.getContent()%></p>
            <div class = "row">
                <div class = "col-10"></div>
                <div class = "col-2">
                    <footer class="blockquote-footer mt-3">Published: <cite title="Source Title"><%=article.getPublishDate()%></cite></footer>
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

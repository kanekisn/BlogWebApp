<%@ page import="java.util.List" %>
<%@ page import="org.blogapp.models.ArticleEntity" %>
<%@ page import="org.blogapp.models.UserEntity" %>
<%@ page import="org.blogapp.utils.UserAccess" %>
<%@ page import="org.blogapp.utils.Pagination" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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
    <div class="container mt-4">
        <h3 class = "mt-5 mb-4 text-white">Articles</h3>
        <%if(UserAccess.hasAccess(request)){%>
            <div class = "d-flex justify-content-end">
                <a href="${pageContext.request.contextPath}/article/create" style="text-decoration: none; color:#1d8ab1"><i class="bi bi-check"></i> Add </a>
            </div>
        <%}%>
        <% if(request.getAttribute("articles") != null) { %>
            <% for (ArticleEntity article : (List<ArticleEntity>) request.getAttribute("articles")) { %>
                <div class="text-bg-dark card border-secondary text-center mt-3">
                    <div class="card-body">
                        <p class = "text-white d-flex justify-content-end">Author - <a style ="text-decoration: none; color:deepskyblue" href="${pageContext.request.contextPath}/user/profile/<%=article.getUser().getUsername()%>"><%=article.getUser().getUsername()%></a></p>
                        <h5 class="card-title"><%=article.getTitle()%></h5>
                        <hr>
                        <%if(article.getContent().length() > 150){%>
                            <p class="card-text"><%=article.getContent().substring(0, 150)%>...</p>
                        <%}else{%>
                            <p class="card-text"><%=article.getContent()%></p>
                        <%}%>
                        <a href="/article/<%=article.getSlug()%>" class="btn btn-outline-secondary mt-3">Read</a>
                        <%if(UserAccess.hasAccess(request)){%>
                            <div class = "d-flex justify-content-end">
                                <a href="${pageContext.request.contextPath}/article/update/<%=article.getSlug()%>" class="me-3" style="text-decoration: none; color:#1d8ab1"><i class="bi bi-pencil-square"></i> Edit </a>
                                <a href="${pageContext.request.contextPath}/article/delete/<%=article.getSlug()%>" class="" style="text-decoration: none; color:#1d8ab1"><i class="bi bi-file-excel"></i> Delete</a>
                            </div>
                        <%}%>
                    </div>
                </div>
                <i class="bi bi-wrench-adjustable-circle-fill"></i>
            <%}%>
        <%}else{%>
            <h3 class = "mt-5 text-white">No articles!</h3>
        <%}%>
    </div>

    <div class="container">
        <footer class="bg-dark text-center text-lg-start text-white position-static">
            <div class="d-flex justify-content-center mb-5">
                <nav aria-label="Page navigation example bg-dark">
                    <%Pagination pg = (Pagination) request.getAttribute("pagination");%>
                    <%int currentPage = 1;%>
                    <%if(request.getParameter("page") != null){%>
                        <%currentPage = Integer.parseInt(request.getParameter("page"));%>
                    <%}%>
                    <%int totalPages = pg.getTotalPages();%>
                    <%if(totalPages > 1){%>
                    <ul class="pagination">
                        <li class="page-item <%if(pg.isFirstPage(currentPage)){%>disabled<%}%>">
                            <a class="page-link bg-dark text-white" href="?page=<%=currentPage - 1%>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <%if(totalPages > 5) totalPages = 5;%>
                        <%for(int i = 1; i <= totalPages; i++){%>
                            <li class="page-item <%if(currentPage == i){%>active<%}%>"><a class="page-link bg-dark text-white" href="?page=<%=i%>"><%=i%></a></li>
                        <%}%>
                        <li class="page-item <%if(pg.isLastPage(currentPage)){%>disabled<%}%>">
                            <a class="page-link bg-dark text-white" href="?page=<%=currentPage + 1%>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                    <%}%>
                </nav>
            </div>
            <div class="text-center p-3">
                © 2024 Copyright:
                <a class="text-white" href="">Hibernate</a>
            </div>
        </footer>

    </div>
    <!-- End of .container -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

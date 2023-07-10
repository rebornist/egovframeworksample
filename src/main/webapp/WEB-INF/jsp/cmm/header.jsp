<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 헤더 영역 --%>
<header class="p-3 text-bg-dark">
  <div class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
      <a href="<c:url value="main.do" />" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
        <img src="<c:url value="/img/free-sticker-sheep-4193251.png" />" alt="로고이미지" height="42" class="bi me-2" />
      </a>

      <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
<%--        <li><a href="<c:url value="main.do" />" class="nav-link px-2 text-secondary">Home</a></li>--%>
      </ul>

      <div class="text-end">
        <button type="button" class="btn btn-outline-light me-2">Login</button>
        <button type="button" class="btn btn-warning">Sign-up</button>
      </div>
    </div>
  </div>
</header>

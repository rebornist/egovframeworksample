<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Language" content="ko" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>용어사전등록</title>

        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" type="text/css" />
        <style>
            @font-face {
                font-family: 'Nanum Gothic Coding Regular';
                src: url('<c:url value='/font/NanumGothicCoding-Regular.ttf'/>') format('truetype');
            }

            @font-face {
                font-family: 'Nanum Gothic Coding Bold';
                src: url('<c:url value='/font/NanumGothicCoding-Bold.ttf'/>') format('truetype');
            }

            body {
                font-family: 'Nanum Gothic Coding Regular';
            }

            .font-nanum {
                font-family: 'Nanum Gothic Coding Regular';
            }

            .font-nanum-bold {
                font-family: 'Nanum Gothic Coding Bold';
            }
        </style>
        <script type="text/javascript" src="<c:url value='/js/bootstrap.bundle.js'/>"></script>
    </head>

    <body>
        <noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

        <%@include file="cmm/header.jsp" %>

        <main class="container">

            <form name="frm" id="frm" action="<c:url value="/api/keyword/search.do" />" class="m-1 p-1 bg-light-subtle" method="post">
                <div class="mt-3">
                    <label for="keyword" class="form-label">텍스트 입력</label>
                    <textarea class="form-control" id="keyword" name="keyword" rows="3"></textarea>
                </div>
                <div class="d-grid gap-2 my-3">
                    <input type="submit" class="btn btn-primary" value="<spring:message code="button.search" />" />
                </div>
            </form>

            <%@ include file="cmm/footer.jsp" %>
        </main>

        <%@include file="mainJS.jsp" %>
    </body>
</html>
<%@ page import="com.example.dhlee128.entity.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메인</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>

<script type="text/javascript">
</script>

<%
    session = request.getSession();
    Member member = (Member)session.getAttribute("sessionMember");
%>

<body>
<div class="mainBox">
    <div class="titleBox">
        <div class="title"><a><% if(member != null) { %><%=member.getUserName()%>님, <% } %>메인화면</a></div>
        <% if(member == null) { %>
        <div class="main"><a href="/login">로그인</a></div>
        <% } else { %>
        <div class="main"><a href="/logout">로그아웃</a></div>
        <% } %>
    </div>
    <hr/>
    <div class="buttonBox">
        <input type="button" class="btn_sub" value="상품권 교환소" onClick="<% if(member == null) { %>alert('로그인 후 이용가능합니다. 로그인창으로 이동합니다.');<%}%>location.href='/coupon';"/>
        <% if(member == null) { %>
        <input type="button" class="btn_sub" value="회원가입" onClick="location.href='/join'"/>
        <% } else { %>
        <input type="button" class="btn_sub" value="회원정보" onClick="location.href='/memberInfo'"/>
        <% } %>
    </div>
</div>
</html>

<%@ page import="com.example.dhlee128.entity.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>

<script src="js/commonUtil.js"></script>
<script type="text/javascript">
  function save() {

    if(!validationForm()) return;

    var formData = $("#memberInfoForm").serialize();

    $.ajax({
      type: "POST",
      url: "/memberInfoEdit",
      data: formData,
      dataType: "text"
    }).success(function (res) {
        if(res=="ok") {
            alert("수정 되었습니다. 다시 로그인 해주세요.");
            location.href="/logout";
        } else if(res=="fail_pwd") {
            alert("비밀번호가 일치하지 않습니다.");
        } else {
            alert("수정 실패하였습니다.");
        }
    }).fail(function(err){
      alert("수정 실패하였습니다.");
      console.log(JSON.stringify(err));
    })
  }
</script>

<%
    Member memberInfo = (Member)request.getSession().getAttribute("sessionMember");
%>

<body>
<div class="mainBox">
    <div class="titleBox">
        <div class="title"><a>회원정보</a></div>
        <div class="main"><a href="/">메인화면</a><a>&nbsp;|&nbsp;</a><a href="/logout">로그아웃</a></div>
    </div>
    <hr/>
    <form id="memberInfoForm">
        <div class="detailBox">
            <span class="list">이름</span><input type="text" name="name" readonly value="<%=memberInfo.getUserName()%>"/>
        </div>
        <div class="detailBox">
            <span class="list">아이디</span><input type="text" name="userId" readonly value="<%=memberInfo.getUserId()%>"/>
        </div>
        <div class="detailBox">
            <span class="list">기존 비밀번호</span><span class="req">*</span><input type="password" name="userPwdBefore" maxlength="14" placeholder="기존 비밀번호를 입력해 주세요."/><br/>
        </div>
        <div class="detailBox">
            <span class="list">새 비밀번호</span><span class="req">*</span><input type="password" name="userPwd" onchange="chk_pwd()" maxlength="14" placeholder="새 비밀번호를 입력해 주세요."/><br/>
        </div>
        <div class="detailBox">
            <span class="list">비밀번호 확인</span><input type="password" name="userPwdChk" onchange="chk_pwd()" maxlength="14" placeholder="새 비밀번호를 입력해 주세요."/><br/>
        </div>
        <div id="checkPwd" class="infoTxt" style="text-align: right;"></div>
        <div class="detailBox">
            <span class="list">휴대폰 번호</span><input type="text" name="phone" maxlength="11" placeholder="'-' 없이 입력해 주세요." value="<%=memberInfo.getPhone()%>"><br/>
        </div>
        <div class="buttonBox">
            <input type="button" class="btn_submit" value="수정하기" onclick='save()'>
        </div>
    </form>
</div>
</body>

</html>

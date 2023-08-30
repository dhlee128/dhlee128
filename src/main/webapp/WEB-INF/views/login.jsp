<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>로그인</title>
  <link rel="stylesheet" href="css/style.css" type="text/css">
  <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>

<%
  String redirectURL = request.getParameter("redirectURL");

  String formURL = redirectURL==null?"/":redirectURL;
%>

<script src="js/commonUtil.js"></script>
<script type="text/javascript">

  function save() {

    if(!validationForm()) return;

    var formData = $("#loginForm").serialize();

    $.ajax({
      type: "POST",
      url: "/login",
      data: formData,
      dataType: "text"
    }).success(function (res) {
      if(res=="ok") {
        alert("로그인 되었습니다.");
        location.href="<%=formURL%>";
      } else {
        alert("아이디 또는 비밀번호를 확인해주세요.");
      }
    }).fail(function(err){
      alert("로그인이 실패하였습니다.");
      console.log(JSON.stringify(err));
    })
  }
</script>

<body>
<div class="mainBox">
  <div class="titleBox">
    <div class="title"><a>로그인</a></div>
    <div class="main"><a href="/">메인화면</a></div>
  </div>
  <hr/>

  <form id="loginForm">
    <div class="detailBox">
      <span class="list">아이디</span><input type="text" name="loginId" value="" maxlength="14" placeholder="아이디를 입력해 주세요."/>
    </div>
    <div class="detailBox">
      <span class="list">비밀번호</span><input type="password" name="loginPwd" value="" maxlength="14" placeholder="비밀번호를 입력해 주세요."/>
    </div>
    <div class="buttonBox">
      <input type="button" class="btn_submit" onclick="save();" value="로그인"/>
    </div>
  </form>
  <hr/>
  <div class="buttonBox">
    <input type="button" class="btn_sub" value="회원가입" onClick="location.href='/join'"/>
  </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>

<script src="js/commonUtil.js"></script>
<script type="text/javascript">
  function save() {

    if(!validationForm()) return;

    var formData = $("#joinForm").serialize();

    $.ajax({
      type: "POST",
      url: "/join",
      data: formData,
      dataType: "text"
    }).success(function (res) {
        if(res=="ok") {
            alert("회원가입 되었습니다.");
            location.href="/";
        }else{
            alert("이미 존재하는 아이디 입니다.");
        }
    }).fail(function(err){
      alert("회원가입이 실패하였습니다.");
      console.log(JSON.stringify(err));
    })
  }
</script>

<body>
<div class="mainBox">
    <div class="titleBox">
        <div class="title"><a>회원가입</a></div>
        <div class="main"><a href="/">메인화면</a></div>
    </div>
    <hr/>
    <form id="joinForm">
        <div class="detailBox">
            <span class="list">이름</span><span class="req">*</span><input type="text" name="name" class="koOnly" maxlength="9" placeholder="이름을 입력해 주세요."/>
        </div>
        <div class="detailBox">
            <span class="list">아이디</span><span class="req">*</span><input type="text" name="userId" class="alphaNumOnly" maxlength="14" placeholder="아이디를 입력해 주세요."/>
        </div>
        <div class="detailBox">
            <span class="list">비밀번호</span><span class="req">*</span><input type="password" name="userPwd" onchange="chk_pwd()" maxlength="14" placeholder="비밀번호를 입력해 주세요."/><br/>
        </div>
        <div class="detailBox">
            <span class="list">비밀번호 확인</span><input type="password" name="userPwdChk" onchange="chk_pwd()" maxlength="14" placeholder="비밀번호를 입력해 주세요."/><br/>
        </div>
        <div id="checkPwd" class="infoTxt" style="text-align: right;"></div>
        <div class="detailBox">
            <span class="list">휴대폰 번호</span><input type="text" name="phone" class="numberOnly" maxlength="11" placeholder="'-' 없이 입력해 주세요." ><br/>
        </div>
        <div class="buttonBox">
            <input type="button" class="btn_submit" value="가입" onclick="save();">
        </div>
    </form>
    <hr/>
    <div class="buttonBox">
        <input type="button" class="btn_sub" value="로그인" onClick="location.href='/login'"/>
    </div>
</div>
</body>

</html>

function chk_pwd() {
    var pw1 = $('input[name=userPwd]').val();
    var pw2 = $('input[name=userPwdChk]').val();

    if(pw2.length>0 && pw1!=pw2) {
        $("#checkPwd").html('비밀번호가 일치하지 않습니다.');
        $("#checkPwd").css("color","red");
    } else {
        $("#checkPwd").html('');
    }
}

function validationForm(){

    if($('input[name="loginId"]').length && $('input[name=loginId]').val().length==0) {
        alert("아이디를 입력하세요");
        $('input[name="loginId"]').focus();
        return false;
    }
    if($('input[name="loginPwd"]').length && $('input[name=loginPwd]').val().length==0) {
        alert("비밀번호를 입력하세요");
        $('input[name="loginPwd"]').focus();
        return false;
    }
    if($('input[name="name"]').length && ($('input[name=name]').val().length < 2 || $('input[name=name]').val().length >= 10)) {
        alert("이름은 2자 이상, 10자 미만으로 입력하세요");
        $('input[name="name"]').focus();
        return false;
    }
    if($('input[name="userId"]').length && ($('input[name=userId]').val().length < 5 || $('input[name=userId]').val().length >= 15)) {
        alert("아이디는 5자 이상, 15자 미만으로 입력하세요");
        $('input[name="userId"]').focus();
        return false;
    }
    if($('input[name="userPwdBefore"]').length && ($('input[name=userPwdBefore]').val().length < 5 || $('input[name=userPwdBefore]').val().length >= 15)) {
        alert("비밀번호는 5자 이상, 15자 미만으로 입력하세요");
        $('input[name="userPwdBefore"]').focus();
        return false;
    }
    if($('input[name="userPwd"]').length && ($('input[name=userPwd]').val().length < 5 || $('input[name=userPwd]').val().length >= 15)) {
        alert("비밀번호는 5자 이상, 15자 미만으로 입력하세요");
        $('input[name=userPwd]').focus();
        return false;
    }
    if($('input[name="userPwd"]').length && ($('input[name="userPwd"]').val() != $('input[name="userPwdChk"]').val())) {
        alert("비밀번호를 다시 확인하세요");
        $('input[name=userPwd]').focus();
        return false;
    }
    if($('input[name="phone"]').length && $('input[name=phone]').val().length>0 && $('input[name=phone]').val().length != 11) {
        alert("휴대폰 번호를 다시 확인하세요");
        $('input[name=phone]').focus();
        return false;
    }

    return true;
}

function getDate() {
    var today = new Date();

    var year = today.getFullYear();

    var month = today.getMonth() + 1;

    var date = today.getDate();

    var hour = today.getHours();

    var minute = today.getMinutes();

    var second = today.getSeconds();

    month = month < 10 ? '0' + month : month;

    minute = minute < 10 ? '0' + minute : minute;

    second = second < 10 ? '0' + second : second;

    var now = year + "" + month + "" + date + "" + hour + "" + minute + "" + second;

    return now;
}

$(document).on("keyup", "input[class=numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
$(document).on("keyup", "input[class=koOnly]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣]/gi,"") );})
$(document).on("keyup", "input[class=alphaNumOnly]", function() {$(this).val( $(this).val().replace(/[^a-zA-Z0-9]/gi,"") );})

<%@ page import="com.example.dhlee128.entity.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품권 교환소</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>

<%
    session = request.getSession();
    Member member = (Member)session.getAttribute("sessionMember");
%>

<script src="js/commonUtil.js"></script>
<script type="text/javascript">

    function getGiftCouponInfo() {
        if($('#phoneNo').length && $('#phoneNo').val().length==0) {
            alert("휴대폰번호를 입력하세요");
            $('#phoneNo').focus();
            return false;
        }
        if($('#couponNo').length && $('#couponNo').val().length==0) {
            alert("교환권번호를 입력하세요");
            $('#couponNo').focus();
            return false;
        }

    let data = {
      phoneNo: $('#phoneNo').val(),
      couponNo: $('#couponNo').val()
    };

        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/giftCoupon",
            data: data,
        }).success(function (res) {
            console.log(res);
        }).fail(function(err){
            console.log(err);
        });
    }

    function getSmileCouponInfo() {

        $(".couponBox .couponDetailBox:eq(2)").empty();

        if($('#couponNo').length && $('#couponNo').val().length==0) {
          alert("교환권번호를 입력하세요");
          $('#couponNo').focus();
          return false;
        }

      $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/smileCoupon/"+$('#couponNo').val(),
      }).success(function (res) {
          if(res.result_code=='S0000' || res.result_code=='E0103') {// S0000: 성공,E0103: 기사용
              var htmlString = "";
              htmlString+="<p class='infoTxt'>상품금액 : "+ res.goods_price +"원 | 충전가능금액 : "+res.order_balance+"원</p>";
              htmlString+="<input type='hidden' id='barcode_num' value='"+res.barcode_num+"'/>";
              htmlString+="<input type='hidden' id='security_key' value='"+res.security_key+"'/>";
              htmlString+="<input type='hidden' id='order_balance' value='"+res.order_balance+"'/>";
              htmlString+="<div class='couponInDetailBox'>";
              htmlString+="  <div class='couponSubmits'>";
              if(res.result_code=='E0103') {// E0103: 기사용
                  showMsg(res.result_code, res.result_msg);
                  if(res.isCancel=='Y') {
                    htmlString += "    <input type='button' class='btn_main' value='교환 취소' onClick='cancelSmileCoupon();'>";
                    htmlString += "    <input type='hidden' id='barcode_num' value='"+res.exchange_num+"'/>";
                  }
              }
              if(res.result_code=='S0000') {// S0000: 성공
                  htmlString += "    <input type='button' class='btn_main' value='교환' onClick='changeSmileCoupon();'>";
              }
              htmlString+="  </div>";
              htmlString+="</div>";

              $(".couponBox .couponDetailBox:eq(2)").append(htmlString);

          } else {
              showMsg(res.result_code, res.result_msg);
          }
      }).fail(function(err){
          console.log(err);
      });
    }

    function changeSmileCoupon() {

      var data = {
          "couponNo" : $('#barcode_num').val(),
          "security_key" : $('#security_key').val(),
          "use_balance" : $('#order_balance').val()
      };

      $.ajax({
          type: "POST",
          dataType: "json",
          contentType: "application/json; charset=utf-8",
          url: "/changeSmileCoupon",
          data: JSON.stringify(data),
      }).success(function (res) {

          console.log(res);

          if(res.result_code=='S0000') {
              alert('교환 완료되었습니다. 교환내역 페이지로 이동합니다.');
          } else {
              showMsg(res.result_code, res.result_msg);
          }
      }).fail(function(err){
          console.log(err);
      });
    }

    function cancelSmileCoupon() {
        var data = {
              "couponNo" : $('#barcode_num').val(),
              "exchange_num" : $('#exchange_num').val(),
              "security_key" : $('#security_key').val()
          };

          $.ajax({
              type: "POST",
              dataType: "json",
              contentType: "application/json; charset=utf-8",
              url: "/cancelSmileCoupon",
              data: JSON.stringify(data),
          }).success(function (res) {

              console.log(res);

            if(res.result_code=='S0000') {
                alert('충전 완료되었습니다. 교환내역 페이지로 이동합니다.');
            } else {
                showMsg(res.result_code, res.result_msg);
            }

          }).fail(function(err){
              console.log(err);
          });

    }

    function selectCoupon(coupon) {

        var htmlString = "";
        $(".couponInDetailBox > img").removeClass("select");

        if(coupon=='gift') {
            htmlString+="<p class='infoTxt'>교환권번호(쿠폰번호/바코드번호)를 정확하게 입력하세요.</p>";
            htmlString+="<div class='couponInDetailBox'>";
            htmlString+="  <input type='text' id='phoneNo' class='numberOnly' placeholder='기프티콘 수신 휴대폰번호를 입력해 주세요.' maxlength='11'>";
            htmlString+="  <input type='text' id='couponNo' placeholder='바코드번호를 입력해 주세요.' style='margin-top: 7px;' maxlength='12'>";
            htmlString+="  <div class='couponSubmits'>";
            htmlString+="    <input type='button' class='btn_sub' value='교환내역 조회'>";
            htmlString+="    <input type='button' class='btn_main' value='확인' onClick='getGiftCouponInfo();'>";
            htmlString+="  </div>";
            htmlString+="</div>";

            $(".couponInDetailBox > img:eq(0)").addClass("select");
        } else if (coupon=='smile') {
            htmlString+="<p class='infoTxt'>교환권번호(바코드번호)를 정확하게 입력하세요.</p>";
            htmlString+="<div class='couponInDetailBox'>";
            htmlString+="  <input type='text' id='couponNo' placeholder='바코드번호를 입력해 주세요.' maxlength='12'>";
            htmlString+="  <div class='couponSubmits'>";
            htmlString+="    <input type='button' class='btn_sub' value='교환내역 조회'>";
            htmlString+="    <input type='button' class='btn_main' value='확인' onClick='getSmileCouponInfo();'>";
            htmlString+="  </div>";
            htmlString+="  <div class='couponInfo'></div>";
            htmlString+="</div>";

            $(".couponInDetailBox > img:eq(1)").addClass("select");
        }
        $(".couponBox .couponDetailBox:eq(1)").empty().append(htmlString);
    }

    function showMsg(code, msg) {
      alert("["+code+"] "+msg);
    }

</script>

<body>
<div class="mainBox">
    <div class="titleBox">
        <div class="title"><a>상품권 교환소</a></div>
        <div class="main"><a href="/">메인화면</a><a>&nbsp;|&nbsp;</a><a href="/logout">로그아웃</a></div>
    </div>
    <hr/>
    <p class="infoTxt" style="text-align: center;">다양한 교환권을 해피캐시로 충전하거나 해피머니온라인상품권으로 교환하실 수 있습니다.</p>

    <div class="couponBox area1">
        <h3>1. 교환권 선택</h3>
        <div class="couponDetailBox">
            <p class="infoTxt">교환권 종류를 선택하세요.</p>
            <div class="couponInDetailBox">
                <img src="images/coupon_1.png" onclick="selectCoupon('gift');" alt="기프티콘">
                <img src="images/coupon_2.png" onclick="selectCoupon('smile');" alt="스마일콘">
            </div>
        </div>
    </div>

    <div class="couponBox area2">
        <h3>2. 교환권번호 입력</h3>
        <div class="couponDetailBox">
            <p class="infoTxt emptyTxt">교환권종류를 선택하세요.</p>
        </div>
    </div>

    <div class="couponBox area3">
        <h3>3. 해피캐시 충전</h3>
        <div class="couponDetailBox">
            <p class="infoTxt emptyTxt">교환권번호를 선택하세요.</p>
        </div>
    </div>
</div>

</body>
</html>

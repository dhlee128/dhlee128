<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품권 교환소</title>
    <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>
<body>
    <h2>상품권 교환소</h2>

    <div>
        <p>다양한 교환권을 해피캐시로 충전하거나 해피머니온라인상품권으로 교환하실 수 있습니다.</p>
        <h3>1 교환권 선택</h3>
        <div>
            <h4>교환권 종류를 선택하세요.</h4>
            <ul>
                <li>
                    <a href="javascript:void(0);">
                        <img src="images/coupon_1.png" alt="기프티콘">
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        <img src="images/coupon_2.png" alt="스마일콘">
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div>
        <h3>2 교환권번호 입력</h3>
        <div>
            <h4>교환권번호(쿠폰번호/바코드번호)를 정확하게 입력하세요</h4>
                <div>
                    <input type="text" id="couponNo" title="쿠폰번호/바코드번호 입력" style="width:323px" maxlength="12">
                    <div>
                        <input type="button" value="확인1" onclick="exchangeQuery();">
                        <input type="button" value="확인2" onclick="exchangeQuery2();">
                        <input type="button" value="확인3" onclick="exchangeQuery3();">
                        <input type="button" value="확인4" onclick="exchangeQuery4();">
                        <input type="button" value="확인5" onclick="exchangeQuery5();">
                    </div>
                </div>
        </div>
    </div>


    <script>
        function getCurrentDate() {
            let date = new Date();

            let year = date.getFullYear().toString();

            let mon = date.getMonth() + 1;
            mon = mon<10?'0'+mon.toString():mon.toString();

            let day = date.getDate();
            day = day<10? '0'+day.toString():day.toString();

            let hour = date.getHours();
            hour = hour<10?'0'+hour.toString():hour.toString();

            let min = date.getMinutes();
            min = min<10?'0'+min.toString():min.toString();

            let sec = date.getSeconds();
            sec = sec<10?'0'+sec.toString():sec.toString();

            return year+mon+day+hour+min+sec
        }


        function exchangeQuery() {
            console.log('exchangeQuery::'+getCurrentDate());
            $.ajax({
                type: "GET",
                url: "http://tcorp.zlgoon.com/lssend/exchangeQuery.do",
                data: {
                    "version": "1.43",
                    "barcode_num": "922335240221",
                    "rcompany_id": "EC2581",
                    "exedate": getCurrentDate(),
                    "site_user_id": "zan03082",
                    "branch_code": "zlgoon1"
                },
                dataType: "json",
                success: function(result) {

                    console.log(result);

                    let str = JSON.stringify(result);
                    console.log(str);

                    console.log(result.version);
                    console.log(result.result_code);
                    console.log(result.result_msg);
                    console.log(JSON.stringify(result.result_data));
                    console.log(result.result_data.esult_code);
                },
                error: function(a,b,c) {
                    console.log(JSON.stringify(a)+','+b+','+c);

                    console.log(a.readyState);
                }
            });
        }


        function exchangeQuery2() {
            console.log('exchangeQuery2::'+getCurrentDate());
            $.ajax({
                type: "GET",
                url: "http://tcorp.zlgoon.com/lssend/exchangeQuery.do",
                data: JSON.stringify({
                    "version": "1.43",
                    "barcode_num": "922335240221",
                    "rcompany_id": "EC2581",
                    "exedate": getCurrentDate(),
                    "site_user_id": "zan03082",
                    "branch_code": "zlgoon1"
                }),
                dataType: "json",
                success: function(result) {

                    console.log(result);

                    let str = JSON.stringify(result);
                    console.log(str);

                    console.log(result.version);
                    console.log(result.result_code);
                    console.log(result.result_msg);
                    console.log(JSON.stringify(result.result_data));
                    console.log(result.result_data.esult_code);
                },
                error: function(a,b,c) {
                    console.log(JSON.stringify(a)+','+b+','+c);
                }
            });
        }

        function exchangeQuery3() {
            console.log('exchangeQuery3::'+getCurrentDate());
            $.ajax({
                type: "GET",
                url: "http://tcorp.zlgoon.com/lssend/exchangeQuery.do",
                data: JSON.stringify({
                    "version": "1.43",
                    "barcode_num": "922335240221",
                    "rcompany_id": "EC2581",
                    "exedate": getCurrentDate(),
                    "site_user_id": "zan03082",
                    "branch_code": "zlgoon1"
                }),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function(result) {

                    console.log(result);

                    let str = JSON.stringify(result);
                    console.log(str);

                    console.log(result.version);
                    console.log(result.result_code);
                    console.log(result.result_msg);
                    console.log(JSON.stringify(result.result_data));
                    console.log(result.result_data.esult_code);
                },
                error: function(a,b,c) {
                    console.log(JSON.stringify(a)+','+b+','+c);
                }
            });
        }


        function exchangeQuery4() {
            console.log('exchangeQuery4::'+getCurrentDate());
            $.ajax({
                type: "POST",
                url: "http://tcorp.zlgoon.com/lssend/exchangeQuery.do",
                data: JSON.stringify({
                    "version": "1.43",
                    "barcode_num": "922335240221",
                    "rcompany_id": "EC2581",
                    "exedate": getCurrentDate(),
                    "site_user_id": "zan03082",
                    "branch_code": "zlgoon1"
                }),
                dataType: "json",
                success: function(result) {

                    console.log(result);

                    let str = JSON.stringify(result);
                    console.log(str);

                    console.log(result.version);
                    console.log(result.result_code);
                    console.log(result.result_msg);
                    console.log(JSON.stringify(result.result_data));
                    console.log(result.result_data.esult_code);
                },
                error: function(a,b,c) {
                    console.log(JSON.stringify(a)+','+b+','+c);
                }
            });
        }

        function exchangeQuery5() {
            console.log('exchangeQuery5::'+getCurrentDate());
            $.ajax({
                type: "POST",
                url: "http://tcorp.zlgoon.com/lssend/exchangeQuery.do",
                data: JSON.stringify({
                    "version": "1.43",
                    "barcode_num": "922335240221",
                    "rcompany_id": "EC2581",
                    "exedate": getCurrentDate(),
                    "site_user_id": "zan03082",
                    "branch_code": "zlgoon1"
                }),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function(result) {

                    console.log(result);

                    let str = JSON.stringify(result);
                    console.log(str);

                    console.log(result.version);
                    console.log(result.result_code);
                    console.log(result.result_msg);
                    console.log(JSON.stringify(result.result_data));
                    console.log(result.result_data.esult_code);
                },
                error: function(a,b,c) {
                    console.log(JSON.stringify(a)+','+b+','+c);
                }
            });
        }

    </script>
</body>
</html>

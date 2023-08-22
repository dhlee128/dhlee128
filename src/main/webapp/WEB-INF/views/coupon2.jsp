<%@ page contentType="text/html;charset=euc-kr" %>
<% request.setCharacterEncoding("euc-kr"); %>
<% response.setContentType("text/html;charset=euc-kr"); %>
<html>
<head>
    <title>��ǰ�� ��ȯ��</title>
    <script type="text/javascript" src="js/jquery/jquery-2.1.1.min.js"></script>
</head>
<body>
    <h2>��ǰ�� ��ȯ��</h2>

    <div>
        <p>�پ��� ��ȯ���� ����ĳ�÷� �����ϰų� ���ǸӴϿ¶��λ�ǰ������ ��ȯ�Ͻ� �� �ֽ��ϴ�.</p>
        <h3>1 ��ȯ�� ����</h3>
        <div>
            <h4>��ȯ�� ������ �����ϼ���.</h4>
            <ul>
                <li>
                    <a href="javascript:void(0);">
                        <img src="images/coupon_1.png" alt="����Ƽ��">
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        <img src="images/coupon_2.png" alt="��������">
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div>
        <h3>2 ��ȯ�ǹ�ȣ �Է�</h3>
        <div>
            <h4>��ȯ�ǹ�ȣ(������ȣ/���ڵ��ȣ)�� ��Ȯ�ϰ� �Է��ϼ���</h4>
                <div>
                    <input type="text" id="couponNo" title="������ȣ/���ڵ��ȣ �Է�" style="width:323px" maxlength="12">
                    <div>
                        <input type="button" value="Ȯ��1" onclick="exchangeQuery();">
                        <input type="button" value="Ȯ��2" onclick="exchangeQuery2();">
                        <input type="button" value="Ȯ��3" onclick="exchangeQuery3();">
                        <input type="button" value="Ȯ��4" onclick="exchangeQuery4();">
                        <input type="button" value="Ȯ��5" onclick="exchangeQuery5();">
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
                contentType: "application/json; charset=euc-kr",
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
                contentType: "application/json;",
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

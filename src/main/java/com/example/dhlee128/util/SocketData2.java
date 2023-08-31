package com.example.dhlee128.util;
import java.nio.charset.StandardCharsets;

public class SocketData2 {

    String msg = "";

    public String getMsg() {
        return msg;
    }
    public SocketData2(String con1, String con2, String con3, String con4, String con5, String con6, String con7, String con8, String con9, String con10, String con11
            , String data12, String data13, String data14, String data15, String data16, String data17, String data18, String data19, String data20, String data21, String data22) {

        StringBuffer sb = new StringBuffer();

        // Control(54)
        sb.append(fillRight(4, con1));
        sb.append(fillRight(2, con2));
        sb.append(fillRight(2, con3));
        sb.append(fillRight(4, con4));
        sb.append(fillRight(8, con5));
        sb.append(fillRight(6, con6));
        sb.append(fillRight(10, con7));
        sb.append(fillLeftZero(4, con8));
        sb.append(fillRight(2, con9));
        sb.append(fillRight(2, con10));
        sb.append(fillRight(10, con11));

        // Data(209)
        sb.append(fillRight(6, data12));
        sb.append(fillRight(10, data13));
        sb.append(fillRight(80, data14));
        sb.append(fillRight(16, data15));
        sb.append(fillRight(8, data16));
        sb.append(fillRight(6, data17));
        sb.append(fillRight(20, data18));
        sb.append(fillRight(1, data19));
        sb.append(fillRight(1, data20));
        sb.append(fillRight(11, data21));
        sb.append(fillRight(50, data22));

        msg = sb.toString();
    }

    public String fillRight(int length, String str) {
        StringBuffer sb = new StringBuffer(str);
        while(sb.toString().getBytes(StandardCharsets.UTF_8).length < length) {
            sb.append(" ");
        }

        return sb.toString();
    }

    public String fillLeftZero(int length, String str) {
        StringBuffer sb = new StringBuffer();
        while(sb.length()<length-str.getBytes(StandardCharsets.UTF_8).length) {
            sb.append('0');
        }
        sb.append(str);

        return sb.toString();
    }


}








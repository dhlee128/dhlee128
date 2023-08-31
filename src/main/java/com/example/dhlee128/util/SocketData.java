package com.example.dhlee128.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SocketData {

    // Control(54)
    byte[] con1   = new byte[4];
    byte[] con2 = new byte[2];
    byte[] con3  = new byte[2];
    byte[] con4  = new byte[4];
    byte[] con5  = new byte[8];
    byte[] con6  = new byte[6];
    byte[] con7  = new byte[10];
    byte[] con8  = new byte[4];
    byte[] con9  = new byte[2];
    byte[] con10 = new byte[2];
    byte[] con11  = new byte[10];

    // Data(209)
    byte[] data12  = new byte[6];
    byte[] data13  = new byte[10];
    byte[] data14  = new byte[80];
    byte[] data15  = new byte[16];
    byte[] data16  = new byte[8];
    byte[] data17  = new byte[6];
    byte[] data18  = new byte[20];
    byte[] data19  = new byte[1];
    byte[] data20  = new byte[1];
    byte[] data21  = new byte[11];
    byte[] data22  = new byte[50];

    public SocketData() {}

    public SocketData(String con1, String con2, String con3, String con4, String con5, String con6, String con7, String con8, String con9, String con10, String con11
        , String data12, String data13, String data14, String data15, String data16, String data17, String data18, String data19, String data20, String data21, String data22) {

        // Control(54)
        setData(this.con1, con1);
        setData(this.con2, con2);
        setData(this.con3, con3);
        setData(this.con4, con4);
        setData(this.con5, con5);
        setData(this.con6, con6);
        setData(this.con7, con7);
        setData(this.con8, con8);
        setData(this.con9, con9);
        setData(this.con10, con10);
        setData(this.con11, con11);

        // Data(209)
        setData(this.data12, data12);
        setData(this.data13, data13);
        setData(this.data14, data14);
        setData(this.data15, data15);
        setData(this.data16, data16);
        setData(this.data17, data17);
        setData(this.data18, data18);
        setData(this.data19, data19);
        setData(this.data20, data20);
        setData(this.data21, data21);
        setData(this.data22, data22);
    }

    private String getData(byte[] byteArray) {

        if (byteArray == null) return "";

        String str = null;

        try {
            str = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void setData(byte[] byteArray, String str) {

        if (str == null) str = "";

        byte[] bytes = null;

        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int endIdx = 0;

        if (byteArray.length >= bytes.length) {
            endIdx = bytes.length;
        } else {
            endIdx = byteArray.length;
        }

        for (int i = 0; i < endIdx; i++) {
            byteArray[i] = bytes[i];
        }

        for (int j = endIdx; j < byteArray.length; j++) {
            byteArray[j] = ' ';
        }
    }

    public void print() {

        System.out.println("con1: " + getData(con1) + "\tSize:" + con1.length);
        System.out.println("con2: " + getData(con2) + "\tSize:" + con2.length);
        System.out.println("con3: " + getData(con3) + "\tSize:" + con3.length);
        System.out.println("con4: " + getData(con4) + "\tSize:" + con4.length);
        System.out.println("con5: " + getData(con5) + "\tSize:" + con5.length);
        System.out.println("con6: " + getData(con6) + "\tSize:" + con6.length);
        System.out.println("con7: " + getData(con7) + "\tSize:" + con7.length);
        System.out.println("con8: " + getData(con8) + "\tSize:" + con8.length);
        System.out.println("con9: " + getData(con9) + "\tSize:" + con9.length);
        System.out.println("con10: " + getData(con10) + "\tSize:" + con10.length);
        System.out.println("con11: " + getData(con11) + "\tSize:" + con11.length);
        System.out.println();

        System.out.println("data12: " + getData(data12) + "\tSize:" + data12.length);
        System.out.println("data13: " + getData(data13) + "\tSize:" + data13.length);
        System.out.println("data14: " + getData(data14) + "\tSize:" + data14.length);
        System.out.println("data15: " + getData(data15) + "\tSize:" + data15.length);
        System.out.println("data16: " + getData(data16) + "\tSize:" + data16.length);
        System.out.println("data17: " + getData(data17) + "\tSize:" + data17.length);
        System.out.println("data18: " + getData(data18) + "\tSize:" + data18.length);
        System.out.println("data19: " + getData(data19) + "\tSize:" + data19.length);
        System.out.println("data20: " + getData(data20) + "\tSize:" + data20.length);
        System.out.println("data21: " + getData(data21) + "\tSize:" + data21.length);
        System.out.println("data22: " + getData(data22) + "\tSize:" + data22.length);
        System.out.println();
    }

    public void writeDataExternal(DataOutputStream stream) {

        try {
            stream.write(con1);
            stream.write(con2);
            stream.write(con3);
            stream.write(con4);
            stream.write(con5);
            stream.write(con6);
            stream.write(con7);
            stream.write(con8);
            stream.write(con9);
            stream.write(con10);
            stream.write(con11);
            stream.write(data12);
            stream.write(data13);
            stream.write(data14);
            stream.write(data15);
            stream.write(data16);
            stream.write(data17);
            stream.write(data18);
            stream.write(data19);
            stream.write(data20);
            stream.write(data21);
            stream.write(data22);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}








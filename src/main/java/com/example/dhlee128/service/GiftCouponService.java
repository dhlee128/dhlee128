package com.example.dhlee128.service;

import com.example.dhlee128.dto.CouponDto;
import com.example.dhlee128.dto.CouponResVo;
import com.example.dhlee128.repository.CouponHisRepository;
import com.example.dhlee128.repository.GiftCouponRepository;
import com.example.dhlee128.service.CouponService;
import com.example.dhlee128.util.SocketData;
import com.example.dhlee128.util.SocketData2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GiftCouponService extends CouponService {

    private final CouponHisRepository couponHisRepository;

    private final GiftCouponRepository giftCouponRepository;

    @Override
    public CouponResVo getCoupon(CouponDto dto) {

        Socket socket = null;

        try{
            socket = new Socket();
            log.info("::::getGiftCoupon Request::::[s]");
            socket.connect(new InetSocketAddress("113.217.246.45", 9091));
            log.info("::::getGiftCoupon Request::::[e]");

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            String yyyymmdd = getDate().substring(0, 8);
            String hhmmss= getDate().substring(8);

            SocketData2 data = new SocketData2(
                    "P100","05","10","",yyyymmdd,hhmmss,"1","263","","","",
                    "RC0074","happyMoney","(주)해피머니아이엔씨","000000",yyyymmdd,hhmmss, dto.getCouponNo(),"1","1","01082220836",""
            );

            String message = data.getMsg();
            log.info("::::getGiftCoupon Data Send[s]::::"+message);
            byte[] bytes = message.getBytes("UTF-8");

            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            dos.flush();

            log.info("::::getGiftCoupon Data Send[e]::::"+message);

            /*InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            int len = dis.readInt();

            log.info("::::getGiftCoupon Data Len::::"+len);

            if(len>0) {
                byte input[] = new byte[len];
                dis.readFully(input, 0, len);

                message = new String(input, 0, len, "UTF-8");
                log.info("::::getGiftCoupon Data Received::::"+message);
            }*/

            log.info("::::changeSmileCoupon returnMsg[1]::::");
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            log.info("::::changeSmileCoupon returnMsg[2]::::");
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            StringBuffer sb = new StringBuffer();
            String readLine;
            log.info("::::changeSmileCoupon returnMsg[3]::::");
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            log.info("::::changeSmileCoupon returnMsg[4]::::");
            String returnMsg = sb.toString();

            log.info("::::changeSmileCoupon returnMsg::::"+returnMsg);

            br.close();
            log.info("::::getGiftCoupon br Closed::::");
            dis.close();
            log.info("::::getGiftCoupon dis Closed::::");
            is.close();
            log.info("::::getGiftCoupon is Closed::::");
            dos.close();
            log.info("::::getGiftCoupon dos Closed::::");
            os.close();
            log.info("::::getGiftCoupon os Closed::::");
            socket.close();
            log.info("::::getGiftCoupon Socket Closed::::");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!socket.isClosed()) {
            try{
                socket.close();
                log.info("::::getGiftCoupon Socket Closed::::");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public CouponResVo changeCoupon(CouponDto dto) {

        return null;
    }

    @Override
    @Transactional
    public CouponResVo cancelCoupon(CouponDto dto) {

        return null;
    }
}

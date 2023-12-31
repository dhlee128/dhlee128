package com.example.dhlee128.service;

import com.example.dhlee128.dto.CouponDto;
import com.example.dhlee128.dto.CouponResVo;
import com.example.dhlee128.entity.CouponHis;
import com.example.dhlee128.entity.SmileCoupon;
import com.example.dhlee128.repository.CouponHisRepository;
import com.example.dhlee128.repository.SmileCouponRepository;
import com.example.dhlee128.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SmileCouponService extends CouponService {

    private final CouponHisRepository couponHisRepository;

    private final SmileCouponRepository smileCouponRepository;

    @Value("${smileCoupon.hostName}")
    private String hostName;

    @Value("${smileCoupon.version}")
    private String version;

    @Value("${smileCoupon.rcompanyId}")
    private String rcompanyId;

    @Value("${smileCoupon.branchCode}")
    private String branchCode;

    @Value("${smileCoupon.branchName}")
    private String branchName;

    @Override
    public CouponResVo getCoupon(CouponDto dto) {

        String path = hostName+"/lssend/exchangeQuery.do";

        JSONObject param = new JSONObject();
        param.put("version", version);
        param.put("barcode_num", dto.getCouponNo());
        param.put("rcompany_id", rcompanyId);
        param.put("exedate", getDate());
        param.put("site_user_id", dto.getUserId());
        param.put("branch_code", branchCode);

        JSONArray paramArr = new JSONArray();
        paramArr.put(param);

        log.info("::::getSmileCoupon param::::"+paramArr.toString());

        CouponResVo coupon = null;

        try {

           HttpURLConnection con = null;

            URL url = new URL(path);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(paramArr.toString());
            bw.flush();
            bw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String readLine;

            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();

            String returnMsg = sb.toString();
            log.info("::::getSmileCoupon returnMsg::::"+returnMsg);

            JSONObject jObj = new JSONObject(returnMsg);
            JSONObject jObjData = (JSONObject)((JSONArray)jObj.get("result_data")).get(0);

            String result_code = (String) jObjData.get("result_code"); //응답코드(S0000)
            String result_msg = (String) jObjData.get("result_msg"); //응답메시지
            String barcode_num = (String) jObjData.get("barcode_num"); //상품권번호
            String order_balance = String.valueOf(jObjData.get("order_balance")); //잔액, 교환권일 경우 상품가격
            String goods_price = String.valueOf(jObjData.get("goods_price")); //상품가격
            String security_key = (String) jObjData.get("security_key"); //거래키

            coupon = CouponResVo.builder()
                    .result_code(result_code)
                    .result_msg(URLDecoder.decode(result_msg, "euc-kr"))
                    .barcode_num(barcode_num)
                    .order_balance(order_balance)
                    .goods_price(goods_price)
                    .security_key(security_key)
                    .build();

            if("E0103".equals(result_code)) { //기사용
                SmileCoupon findSmileCoupon = smileCouponRepository.findByUserIdAndBarcodeNum(dto.getUserId(), dto.getCouponNo());

                coupon.setGoods_price(findSmileCoupon.getGoodsPrice());

                if(findSmileCoupon == null) { //취소여부
                    coupon.setIsCancel("N");
                } else {
                    coupon.setIsCancel("Y");
                    coupon.setSecurity_key(findSmileCoupon.getSecurityKey());
                    coupon.setExchange_num(findSmileCoupon.getExchangeNum());
                }
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coupon;
    }

    @Override
    @Transactional
    public CouponResVo changeCoupon(CouponDto dto) {

        String path = hostName+"/lssend/exchangeBarcode.do";

        JSONObject param = new JSONObject();
        param.put("version", version);
        param.put("barcode_num", dto.getCouponNo());
        param.put("rcompany_id", rcompanyId);
        param.put("branch_code", branchCode);
        param.put("branch_name", branchName);
        param.put("use_balance", dto.getUse_balance());
        param.put("security_key", dto.getSecurity_key());
        param.put("exedate", getDate());
        param.put("site_user_id", dto.getUserId());

        JSONArray paramArr = new JSONArray();
        paramArr.put(param);

        log.info("::::changeSmileCoupon param::::"+paramArr.toString());

        CouponResVo coupon = null;

        try {

            HttpURLConnection con = null;

            URL url = new URL(path);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(paramArr.toString());
            bw.flush();
            bw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String readLine;

            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();

            String returnMsg = sb.toString();
            log.info("::::changeSmileCoupon returnMsg::::"+returnMsg);

            JSONObject jObj = new JSONObject(returnMsg);
            JSONObject jObjData = (JSONObject)((JSONArray)jObj.get("result_data")).get(0);

            String result_code = (String) jObjData.get("result_code"); //응답코드(S0000)
            String result_msg = (String) jObjData.get("result_msg"); //응답메시지
            String barcode_num = (String) jObjData.get("barcode_num"); //상품권번호
            String goods_price = String.valueOf(jObjData.get("goods_price")); //상품가격
            String order_balance = String.valueOf(jObjData.get("order_balance")); //주문 후 잔액
            String use_balance = String.valueOf(jObjData.get("use_balance")); //사용한 금액
            String exchange_num = (String) jObjData.get("exchange_num"); //승인번호
            String security_key = (String) jObjData.get("security_key"); //거래키
            String exedate = (String) jObjData.get("exedate"); //요청일시
            String site_user_id  = (String) jObjData.get("site_user_id"); //사용자아이디

            coupon = CouponResVo.builder()
                    .result_code(result_code)
                    .result_msg(URLDecoder.decode(result_msg, "euc-kr"))
                    .barcode_num(barcode_num)
                    .goods_price(goods_price)
                    .order_balance(order_balance)
                    .use_balance(use_balance)
                    .exchange_num(exchange_num)
                    .security_key(security_key)
                    .exedate(exedate)
                    .site_user_id(site_user_id)
                    .build();

            if("S0000".equals(result_code)) { //승인 성공

                SmileCoupon couponEntity = coupon.toCouponEntity();
                smileCouponRepository.save(couponEntity);

                CouponHis hisEntity = coupon.toHisEntity();
                hisEntity.setProcType("USED"); //승인
                hisEntity.setCouponType("S"); //스마일콘
                couponHisRepository.save(hisEntity);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coupon;
    }

    @Override
    @Transactional
    public CouponResVo cancelCoupon(CouponDto dto) {

        String path = hostName+"/lssend/exchangeCancel.do";

        JSONObject param = new JSONObject();
        param.put("version", version);
        param.put("barcode_num", dto.getCouponNo());
        param.put("rcompany_id", rcompanyId);
        param.put("branch_code", branchCode);
        param.put("branch_name", branchName);
        param.put("exchange_num", dto.getExchange_num());
        param.put("security_key", dto.getSecurity_key());
        param.put("exedate", getDate());
        param.put("site_user_id", dto.getUserId());

        JSONArray paramArr = new JSONArray();
        paramArr.put(param);

        log.info("::::cancelSmileCoupon param::::"+paramArr.toString());

        CouponResVo coupon = null;

        try {

            HttpURLConnection con = null;

            URL url = new URL(path);
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(paramArr.toString());
            bw.flush();
            bw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String readLine;

            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();

            String returnMsg = sb.toString();
            log.info("::::cancelSmileCoupon returnMsg::::"+returnMsg);

            JSONObject jObj = new JSONObject(returnMsg);
            JSONObject jObjData = (JSONObject)((JSONArray)jObj.get("result_data")).get(0);

            String result_code = (String) jObjData.get("result_code"); //응답코드(S0000)
            String result_msg = (String) jObjData.get("result_msg"); //응답메시지
            String barcode_num = (String) jObjData.get("barcode_num"); //상품권번호
            String goods_price = String.valueOf(jObjData.get("goods_price")); //상품가격
            String order_balance = String.valueOf(jObjData.get("order_balance")); //주문 후 잔액
            String use_balance = String.valueOf(jObjData.get("use_balance")); //사용한 금액
            String exchange_num = (String) jObjData.get("exchange_num"); //승인번호
            String security_key = (String) jObjData.get("security_key"); //거래키
            String exedate = (String) jObjData.get("exedate"); //요청일시
            String site_user_id  = (String) jObjData.get("site_user_id"); //사용자아이디

            coupon = CouponResVo.builder()
                    .result_code(result_code)
                    .result_msg(URLDecoder.decode(result_msg, "euc-kr"))
                    .barcode_num(barcode_num)
                    .goods_price(goods_price)
                    .order_balance(order_balance)
                    .use_balance(use_balance)
                    .exchange_num(exchange_num)
                    .security_key(security_key)
                    .exedate(exedate)
                    .site_user_id(site_user_id)
                    .build();

            if("S0000".equals(result_code)) { //취소 성공

                SmileCoupon findSmileCoupon = smileCouponRepository.findByUserIdAndBarcodeNum(dto.getUserId(), dto.getCouponNo());
                if(findSmileCoupon!=null) {
                    smileCouponRepository.delete(findSmileCoupon);

                    CouponHis hisEntity = coupon.toHisEntity();
                    hisEntity.setProcType("CANCELED"); //취소
                    hisEntity.setCouponType("S"); //스마일콘

                    couponHisRepository.save(hisEntity);
                }
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coupon;
    }
}

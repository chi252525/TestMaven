package com.web.apiService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pos.app.DetailVO;
import com.pos.app.MasterVO;
import com.welljoint.entity.OrderPhoneDetailVO;
import com.welljoint.entity.OrdersPhoneVO;
import com.welljoint.entity.StoreInformationVO;
import com.welljoint.service.OrderPhoneDetailService;
import com.welljoint.service.OrdersPhoneService;
import com.welljoint.service.StoreInformationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PosApiService {
    private String ordersNum;
    private Integer processStatus;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String url = null;
    private static String token = null;

    static {
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            url = (String) env.lookup("api-url");
            //System.out.println("api-url : " + url);
            token = (String) env.lookup("api-token");
            //System.out.println("api-token : " + token);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public PosApiService(String ordersNum, Integer processStatus) {
        super();
        this.ordersNum = ordersNum;
        this.processStatus = processStatus;
    }

    public PosApiService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getOrdersNum() {
        return ordersNum;
    }

    public void setOrdersNum(String ordersNum) {
        this.ordersNum = ordersNum;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public void sendOrderAPIwithDetail() throws Exception {
        OrdersPhoneService oSvc = new OrdersPhoneService();
        OrdersPhoneVO vo = oSvc.findByPrimaryKey(ordersNum);
        MasterVO mVo = new MasterVO();
        JSONObject mVojsonObj = new JSONObject();
        List<DetailVO> dList = new ArrayList<DetailVO>();
        if(vo != null) {
            mVo.setInvoicesNum(ordersNum);
            StoreInformationService siSvc = new StoreInformationService();
            List<StoreInformationVO> siList = siSvc.getAll();
            String name = null;
            String store = null;
            String value = null;
            if(siList != null) {
                name = siList.get(0).getName(); //公司
                store = siList.get(0).getStore(); //店家
                value = siList.get(0).getValue(); //統編
            }
            mVo.setName(name);
            mVo.setStore(store);
            String invoicePeriod = null;
            mVo.setInvoicePeriod(invoicePeriod);
            java.util.Date utilDate = new java.util.Date(vo.getOrderDate().getTime());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String invoicesDate = dateFormat.format(utilDate);
            System.out.println("invoicesDate : " + invoicesDate);
            mVo.setInvoicesDate(invoicesDate);
            Double invoicesTotalDouble = vo.getInvoicesTotal();
            String invoicesTotal = String.valueOf(invoicesTotalDouble.doubleValue());
            mVo.setInvoicesTotal(invoicesTotal);
            mVo.setValue(value);
            String note = vo.getNote();
            mVo.setNote(note);
            String customerValue = vo.getCustomerValue();
            mVo.setCustomerValue(customerValue);
            String orderStatus = vo.getOrderStatus();
            mVo.setOrderStatus(orderStatus);
            String orderStatusKey = vo.getOrderStatusKey();
            mVo.setOrderStatusKey(orderStatusKey);
            Boolean statusBoolean = vo.getStatus();
            String status = String.valueOf(statusBoolean.booleanValue());
            mVo.setStatus(status);
            String toolCode = vo.getToolCode();
            mVo.setToolCode(toolCode);
            String loveCode = vo.getLoveCode();
            mVo.setLoveCode(loveCode);
            String tax = vo.getTax();
            mVo.setTax(tax);
            String payBy = vo.getPayBy();
            mVo.setPayBy(payBy);
            String cancel = ""; //不可為null
            mVo.setCancel(cancel);
            String internalNumber = vo.getInternalNumber();
            mVo.setInternalNumber(internalNumber);
            String reprintNumber = vo.getReprintNumber();
            mVo.setReprintNumber(reprintNumber);
            java.util.Date takeTimeDate = new java.util.Date(vo.getTakeTime());
            String takeTime = dateFormat.format(takeTimeDate);
            mVo.setTakeTime(takeTime);
            String orderBy = vo.getOrderBy();
            mVo.setOrderBy(orderBy);
            String randomNum = vo.getRandomNum();
            mVo.setRandomNum(randomNum);
            Integer mOrderStatusInteger = 1; //1.儲存 2.完成 3.取消
            String mOrderStatus = String.valueOf(mOrderStatusInteger.intValue());
            mVo.setMOrderStatus(mOrderStatus);
            String sell_no = null; //set null
            mVo.setSell_no(sell_no);
            String mealNum = String.valueOf(vo.getMealNum().intValue());
            mVo.setMealNum(mealNum);

            // handle null values for JSON object
            mVojsonObj.put("InvoicesNum", ordersNum == null ? JSONObject.NULL : ordersNum);
            mVojsonObj.put("Name", name == null ? JSONObject.NULL : name);
            mVojsonObj.put("Store", store == null ? JSONObject.NULL : store);
            mVojsonObj.put("InvoicePeriod", invoicePeriod == null ? JSONObject.NULL : invoicePeriod);
            mVojsonObj.put("InvoicesDate", invoicesDate == null ? JSONObject.NULL : invoicesDate);
            mVojsonObj.put("InvoicesTotal", invoicesTotal == null ? JSONObject.NULL : invoicesTotal);
            mVojsonObj.put("Value", value == null ? JSONObject.NULL : value);
            mVojsonObj.put("Note", note == null ? JSONObject.NULL : note);
            mVojsonObj.put("CustomerValue", customerValue == null ? JSONObject.NULL : customerValue);
            mVojsonObj.put("orderStatus", orderStatus == null ? JSONObject.NULL : orderStatus);
            mVojsonObj.put("orderStatusKey", orderStatusKey == null ? JSONObject.NULL : orderStatusKey);
            mVojsonObj.put("Status", status == null ? JSONObject.NULL : status);
            //System.out.println("status : " + status);
            mVojsonObj.put("ToolCode", toolCode == null ? JSONObject.NULL : toolCode);
            mVojsonObj.put("LoveCode", loveCode == null ? JSONObject.NULL : loveCode);
            mVojsonObj.put("Tax", tax == null ? JSONObject.NULL : tax);
            mVojsonObj.put("PayBy", payBy == null ? JSONObject.NULL : payBy);
            mVojsonObj.put("Cancel", cancel == null ? JSONObject.NULL : cancel);
            mVojsonObj.put("InternalNumber", internalNumber == null ? JSONObject.NULL : internalNumber);
            mVojsonObj.put("ReprintNumber", reprintNumber == null ? JSONObject.NULL : reprintNumber);
            mVojsonObj.put("TakeTime", takeTime == null ? JSONObject.NULL : takeTime);
            mVojsonObj.put("OrderBy", orderBy == null ? JSONObject.NULL : orderBy);
            mVojsonObj.put("RandomNum", randomNum == null ? JSONObject.NULL : randomNum);
            mVojsonObj.put("mOrderStatus", mOrderStatus == null ? JSONObject.NULL : mOrderStatus);
            mVojsonObj.put("sell_no", sell_no == null ? JSONObject.NULL : sell_no);
            mVojsonObj.put("MealNum", mealNum == null ? JSONObject.NULL : mealNum);
        }

        OrderPhoneDetailService dSvc = new OrderPhoneDetailService();
        List<OrderPhoneDetailVO> opdList = dSvc.findByOrderNum(ordersNum);
        fillDetailVOToList(opdList, dList);

        JSONObject dataJsonObj = new JSONObject();
        JSONArray ja = new JSONArray(dList);
        dataJsonObj.put("Master", mVojsonObj);
        dataJsonObj.put("detail", ja);

        /* //Do not convert map to JSONObject (Unable to put null values in JSON object)
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("Master", mVo); //mVo轉換成json時, 會缺少 "值是null" 的欄位, 所以不用map轉成JSONObject
        map.put("detail", dList);
        JSONObject json = new JSONObject(map);
        String jsonStr = gson.toJson(map);
        */
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String jsonStr = dataJsonObj.toString();
        System.out.println("jsonStr \n" + jsonStr);
        logger.info(jsonStr);
        //String jsonified = gson.toJson(jsonStr);
        //System.out.println("jsonified \n" + jsonified);
        //jsonified = jsonified.substring(1, jsonified.length() - 1);
        //System.out.println("jsonified.substring \n" + jsonified);
        JSONObject jsonObj = new JSONObject();
        //dataJsonObj.put("Token", "A5B4C5D9E8F5G8H8");
        jsonObj.put("Token", token);
        jsonObj.put("cmd", "M001"); //儲存訂單
        //dataJsonObj.put("Data", jsonified);
        jsonObj.put("Data", jsonStr);
        jsonObj.put("repStatus", false);

        Document doc = Jsoup.connect(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                //.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Connection", "keep-alive")
                .requestBody(jsonObj.toString())
                .timeout(1000 * 10)
                .ignoreContentType(true).post();

        Element body = doc.body();
        System.out.println("body.text()\n" + body.text());
        //System.out.println("body.toString()\n" + body.toString());
        logger.info(ordersNum + " " + body.text());
    }

    protected void fillDetailVOToList(List<OrderPhoneDetailVO> opdList, List<DetailVO> dList) {
        for(OrderPhoneDetailVO opdVo : opdList) {
            DetailVO dVo = new DetailVO();
            Long idLong = System.currentTimeMillis() / 1000; //milliseconds to seconds
            String id = String.valueOf(idLong.longValue());
            dVo.setId(id);
            dVo.setInvoicesNum(opdVo.getOrdersnum()); //訂單號碼
            dVo.setProductClass(opdVo.getProductclass());
            dVo.setProductClassKey(opdVo.getProductclasskey());
            dVo.setProductName(opdVo.getProductname());
            //dVo.setQty(opdVo.getQty());
            dVo.setQty(String.valueOf(opdVo.getQty().intValue()));
            //dVo.setPrice(opdVo.getPrice());
            dVo.setPrice(String.valueOf(opdVo.getPrice().intValue()));
            //dVo.setTotalPrice(opdVo.getTotalprice());
            dVo.setTotalPrice(String.valueOf(opdVo.getTotalprice().doubleValue()));
            dVo.setNote(opdVo.getNote() == null ? "" : opdVo.getNote());
            dVo.setTax_Type(opdVo.getTax_type());
            //dVo.setTax(opdVo.getTax());
            dVo.setTax(String.valueOf(opdVo.getTax().doubleValue()));
            dList.add(dVo);
        }
    }

    public void sendOrderAPI() throws Exception {
        String cmd = null;
        switch(processStatus.intValue()) {
            case 4:
                cmd = "M002"; //完成訂單
                break;
            case 5:
                cmd = "M003"; //取消訂單
                break;
            default:
                throw new RuntimeException("非合法可處理的訂單狀態");
                //break;
        }
        JSONObject jsonObj = new JSONObject();
        String dataStr = "\"" + ordersNum + "\""; //for api parser
        jsonObj.put("Token", token);
        jsonObj.put("cmd", cmd);
        //jsonObj.put("Data", ordersNum);
        jsonObj.put("Data", dataStr);
        jsonObj.put("repStatus", false);
        System.out.println("jsonObj.toString \n" + jsonObj.toString());
        //logger.info(jsonObj.toString());
        Document doc = Jsoup.connect(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                //.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Connection", "keep-alive")
                .requestBody(jsonObj.toString())
                .timeout(1000 * 10)
                .ignoreContentType(true).post();

        Element body = doc.body();
        System.out.println("body.text()\n" + body.text());
        //System.out.println("body.toString()\n" + body.toString());
        logger.info(ordersNum + " " + body.text());
    }

    protected java.util.Date LocalToUTC(java.util.Date date){
        return new java.util.Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
    }
}

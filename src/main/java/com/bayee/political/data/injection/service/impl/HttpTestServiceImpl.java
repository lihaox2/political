package com.bayee.political.data.injection.service.impl;

import com.alibaba.csb.sdk.HttpCaller;
import com.alibaba.csb.sdk.HttpParameters;
import com.bayee.political.data.injection.config.PoliceComprehensiveConfig;
import com.bayee.political.data.injection.config.PoliceHttpsConfig;
import com.bayee.political.data.injection.domain.param.PoliceDepartmentParam;
import com.bayee.political.data.injection.domain.result.PoliceDepartmentResult;
import com.bayee.political.data.injection.service.HttpTestService;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.XmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lichenghu
 * @Title: 获取部门信息业务层
 * @Description: )
 * @date 2021/11/17 10:55
 */
@Service
public class HttpTestServiceImpl implements HttpTestService {

    /**
     * 获取部门信息
     * @param param
     * @return
     */
    @Override
    public List<PoliceDepartmentResult> readDepartment(PoliceDepartmentParam param) {
        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATAS><REQUESTID>"+PoliceComprehensiveConfig.requestId+"</REQUESTID><BEGINID>"+param.getBEGINID()+"</BEGINID><MAXROWS>"+param.getMAXROWS()+"</MAXROWS></DATAS>";

        HttpParameters.Builder hp = HttpParameters.newBuilder();
        hp.requestURL(PoliceHttpsConfig.userUrl);
        hp.api(PoliceHttpsConfig.userDepUrl).version("1.0.0");
        hp.accessKey(PoliceComprehensiveConfig.userAppId).secretKey(PoliceComprehensiveConfig.userAppSecret);
        hp.method("get");
        hp.putParamsMap("inxml", xml);
        String result = null;

        try {
            String invoke = HttpCaller.invoke(hp.build());
//            result = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
            result=invoke;
            System.out.println(result);
            Document document;
            document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            XmlUtil xmlUtil = new XmlUtil();
            Map<String,String> map=xmlUtil.map;
//            if(map.get("")){
//
//            }
            PoliceDepartmentResult dep = new PoliceDepartmentResult();
//            dep.setBEGINID();
            xmlUtil.listNodes(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonResult<?> list() {
        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATAS><REQUESTID>"+PoliceComprehensiveConfig.requestId+"</REQUESTID><BEGINID>1</BEGINID><MAXROWS>100</MAXROWS><BMCX></BMCX></DATAS>";
        HttpParameters.Builder hp = HttpParameters.newBuilder();
        hp.requestURL(PoliceHttpsConfig.userUrl);
        hp.api(PoliceHttpsConfig.userUserUrl).version("1.0.0");
        hp.accessKey(PoliceComprehensiveConfig.userAppId).secretKey(PoliceComprehensiveConfig.userAppSecret);
        hp.method("get");
        hp.putParamsMap("inxml", xml);
        String result = null;
        try {
            String invoke = HttpCaller.invoke(hp.build());
            result = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
            System.out.println(result);
            return JsonResult.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonResult<?> deps() {
        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATAS><REQUESTID>"+PoliceComprehensiveConfig.requestId+"</REQUESTID><BEGINID>"+1+"</BEGINID><MAXROWS>"+100+"</MAXROWS></DATAS>";
        HttpParameters.Builder hp = HttpParameters.newBuilder();
        hp.requestURL(PoliceHttpsConfig.userUrl);
        hp.api(PoliceHttpsConfig.userDepUrl).version("1.0.0");
        hp.accessKey(PoliceComprehensiveConfig.userAppId).secretKey(PoliceComprehensiveConfig.userAppSecret);
        hp.method("get");
        hp.putParamsMap("inxml", xml);
        String result = null;

        try {
            String invoke = HttpCaller.invoke(hp.build());
            result = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
            System.out.println(result);
            return JsonResult.ok(result);
//            Document document;
//            document = DocumentHelper.parseText(result);
//            Element root = document.getRootElement();
//            XmlUtil xmlUtil = new XmlUtil();
//            PoliceDepartmentResult dep = new PoliceDepartmentResult();
////            dep.setBEGINID();
//            xmlUtil.listNodes(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 认证接口
     * @param requestid 请求码
     * @param token 动态token
     * @param fhlx
     * @return
     */
    @Override
    public String authentication(String requestid, String token, String fhlx) {
            String requestId="******";
            String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATAS><REQUESTID>"+requestId+"</REQUESTID><TOKEN>TOKEN</TOKEN></DATAS>";
            HttpParameters.Builder hp = HttpParameters.newBuilder();
            hp.requestURL(PoliceHttpsConfig.userUrl);
            hp.api("ST_JWZH_TYRZ_001").version("1.0.0");
            hp.accessKey(PoliceComprehensiveConfig.userAppId).secretKey(PoliceComprehensiveConfig.userAppSecret);
            hp.method("get");
            hp.putParamsMap("inxml", xml);
            String result = null;
            try {
                String invoke = HttpCaller.invoke(hp.build());
//                result = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
                Document document;
                document = DocumentHelper.parseText(invoke);
                Element root = document.getRootElement();
                XmlUtil xmlUtil = new XmlUtil();
                xmlUtil.listNodes(root);

                Map<String, String> rsp_map = xmlUtil.map;
                String SFZH = rsp_map.get("SFZH");
                System.out.println(SFZH);
                return SFZH;

            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }

    public void testST_JWZH_JYGL_001() {
        String requestId="******";
        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATAS><REQUESTID>"+PoliceComprehensiveConfig.requestId+"</REQUESTID><BEGINID>1427700</BEGINID><MAXROWS>100</MAXROWS><BMCX></BMCX></DATAS>";
        HttpParameters.Builder hp = HttpParameters.newBuilder();
        hp.requestURL(PoliceHttpsConfig.userUrl);
        hp.api(PoliceHttpsConfig.userUserUrl).version("1.0.0");
        hp.accessKey(PoliceComprehensiveConfig.userAppId).secretKey(PoliceComprehensiveConfig.userAppSecret);
        hp.method("get");
        hp.putParamsMap("inxml", xml);
        String result = null;
        try {
            String invoke = HttpCaller.invoke(hp.build());
            result = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

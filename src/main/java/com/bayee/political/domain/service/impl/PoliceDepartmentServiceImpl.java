package com.bayee.political.domain.service.impl;

import com.bayee.political.domain.domain.param.PoliceDepartmentParam;
import com.bayee.political.domain.domain.result.PoliceDepartmentResult;
import com.bayee.political.domain.service.PoliceDepartmentService;
import org.springframework.stereotype.Service;

/**
 * @author lichenghu
 * @Title: 获取部门信息业务层
 * @Description: )
 * @date 2021/11/17 10:55
 */
@Service
public class PoliceDepartmentServiceImpl implements PoliceDepartmentService {

    /**
     * 获取部门信息
     * @param param
     * @return
     */
    @Override
    public PoliceDepartmentResult readDepartment(PoliceDepartmentParam param) {

        return null;
    }

    public void ST_JWZH_DEPT_002() {
        String requestId="******";
        int pageno=1;
        int pagesize=100;
        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><DATAS><REQUESTID>"+requestId+"</REQUESTID><BEGINID>"+pageno+"</BEGINID><MAXROWS>"+pagesize+"</MAXROWS></DATAS>";
//        HttpParameters.Builder hp = HttpParameters.newBuilder();
//        hp.requestURL("url");
//        hp.api("ST_JWZH_DEPT_001").version("1.0.0");
//        hp.accessKey("appKey").secretKey("secrectKey");
//        hp.method("get");
//        hp.putParamsMap("inxml", xml);
//        String result = null;
//        try {
//            String invoke = HttpCaller.invoke(hp.build());
//            result = new String(invoke.getBytes("iso-8859-1"), "UTF-8");
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}

package com.bayee.political.data.injection.task;

import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.data.injection.domain.PoliceSituationInfo;
import com.bayee.political.data.injection.service.PoliceSituationInfoService;
import com.dragonsoft.node.adapter.comm.RbspCall;
import com.dragonsoft.node.adapter.comm.RbspConsts;
import com.dragonsoft.node.adapter.comm.RbspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 二期执法警情数据拉取任务
 *
 * @author xxl
 * @date 2021/12/8
 */
@Component
@EnableScheduling
@Controller
public class PoliceSituationTask {

    @Autowired
    PoliceSituationInfoService policeSituationInfoService;

    private final static String SENDER_ID = "C33-00006094";
    private final static String SERVICE_ID = "S33-00000453";
    private final static String DATA_OBJECT_CODE = "ZJ197_330000";

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void dataRenew() {
        String field = "BJDH_PG,BJNR_PG,BJR_XM_PG,CJSJ_PG,CJZZFK_PG,CLDW_BH_PG,CLDW_MC_PG,CLDW_XTBH_PG,CLR_SFZH_PG," +
                "CLR_XM_PG,CZYJ_PG,DATA_FLAG_PG,DHDWJD_PG,DHDWWD_PG,DSDM_PG,GXQYDM_PG,ID_PG,ISDEL_PG,JBSJ_PG,JJDH_PG," +
                "JQBS_PG,JQDD_PG,JQLB_PG,JQLY_PG,JQMC_PG,JQZT_PG,LRDW_BH_PG,LRDW_DSDM_PG,LRDW_QYDM_PG,LRDW_XTBH_PG," +
                "LRR_SFZH_PG,LRSJ_PG,SCSHR_SFZH_PG,SCSHR_XM_PG,SCSHSJ_PG,SFSAJQ_PG,SFXC_PG,SFYCS_PG,SFZDGZ_PG,SHZT_PG," +
                "SJDWDM_PG,SJLY_PG,SJSJ_PG,XGR_SFZH_PG,XGSJ_PG,ZGQK_PG";

        String[] fieldUpperArr = field.toUpperCase(Locale.ROOT).split(",");

        PoliceSituationTask task = new PoliceSituationTask();

        List<JSONObject> jsbList = task.parserXML(fieldUpperArr);
        List<PoliceSituationInfo> infoList = new ArrayList<>();
        for (JSONObject jsb : jsbList) {
            if (policeSituationInfoService.checkIdPGExists(jsb.getString("ID_PG"))) {
                continue;
            }
            PoliceSituationInfo info = new PoliceSituationInfo();
            info.setBjrnPg(jsb.getString("BJNR_PG"));
            info.setCjzzfkPg(jsb.getString("CJZZFK_PG"));
            info.setCzyjPg(jsb.getString("CZYJ_PG"));
            info.setBjdhPg(jsb.getString("BJDH_PG"));
            info.setBjrXmPg(jsb.getString("BJR_XM_PG"));
            info.setCjsjPg(jsb.getString("CJSJ_PG"));
            info.setCldwBhPg(jsb.getString("CLDW_BH_PG"));
            info.setCldwMcPg(jsb.getString("CLDW_MC_PG"));
            info.setCldwXtbhPg(jsb.getString("CLDW_XTBH_PG"));
            info.setClrSfzhPg(jsb.getString("CLR_SFZH_PG"));
            info.setClrXmPg(jsb.getString("CLR_XM_PG"));
            info.setDataFlagPg(jsb.getString("DATA_FLAG_PG"));
            info.setDhdwjdPg(jsb.getString("DHDWJD_PG"));
            info.setDhdwwdPg(jsb.getString("DHDWWD_PG"));
            info.setDsdmPg(jsb.getString("DSDM_PG"));
            info.setGxqydmPg(jsb.getString("GXQYDM_PG"));
            info.setIdPg(jsb.getString("ID_PG"));
            info.setIsdelPg(jsb.getString("ISDEL_PG"));
            info.setJbsjPg(jsb.getString("JBSJ_PG"));
            info.setJjdhPg(jsb.getString("JJDH_PG"));
            info.setJqbsPg(jsb.getString("JQBS_PG"));
            info.setJqddPg(jsb.getString("JQDD_PG"));
            info.setJqlbPg(jsb.getString("JQLB_PG"));
            info.setJqlyPg(jsb.getString("JQLY_PG"));
            info.setJqmcPg(jsb.getString("JQMC_PG"));
            info.setJqztPg(jsb.getString("JQZT_PG"));
            info.setLrdwBhPg(jsb.getString("LRDW_BH_PG"));
            info.setLrdwDsdmPg(jsb.getString("LRDW_DSDM_PG"));
            info.setLrdwQydmPg(jsb.getString("LRDW_QYDM_PG"));
            info.setLrdwXtbhPg(jsb.getString("LRDW_XTBH_PG"));
            info.setLrrSfzhPg(jsb.getString("LRR_SFZH_PG"));
            info.setLrsjPg(jsb.getString("LRSJ_PG"));
            info.setScshrSfzhPg(jsb.getString("SCSHR_SFZH_PG"));
            info.setScshrXmPg(jsb.getString("SCSHR_XM_PG"));
            info.setScshsjPg(jsb.getString("SCSHSJ_PG"));
            info.setSfsajqPg(jsb.getString("SFSAJQ_PG"));
            info.setSfxcPg(jsb.getString("SFXC_PG"));
            info.setSfycsPg(jsb.getString("SFYCS_PG"));
            info.setSfzdgzPg(jsb.getString("SFZDGZ_PG"));
            info.setShztPg(jsb.getString("SHZT_PG"));
            info.setSjdwdmPg(jsb.getString("SJDWDM_PG"));
            info.setSjlyPg(jsb.getString("SJLY_PG"));
            info.setSjsjPg(jsb.getString("SJSJ_PG"));
            info.setXgrSfzhPg(jsb.getString("XGR_SFZH_PG"));
            info.setXgsjPg(jsb.getString("XGSJ_PG"));
            info.setZgqkPg(jsb.getString("ZGQK_PG"));
            infoList.add(info);
        }

        policeSituationInfoService.insertPoliceSituationInfoList(infoList);
    }

    public List<JSONObject> parserXML(String[] requiredItems) {
        List<JSONObject> result = new ArrayList<>();
        RbspService service = new RbspService(SENDER_ID,SERVICE_ID);
        service.setUserCardId("330481198701132616");
        service.setUserDept("330499320000");
        service.setUserName("范海华");

        RbspCall call = service.createCall();
        call.setUrl("http://10.118.7.51:8080/node");
        call.setMethod(RbspConsts.METHOD_QUERY);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("DataObjectCode", DATA_OBJECT_CODE);
        //信息代码输出模式，不用动
        params.put("InfoCodeMode", "1");
        //查询条件
        params.put("Condition", "");
        params.put("RequiredItems", requiredItems);

        String xmlStr = call.invoke(params);

        //解析xml报文
        Document doc = XmlUtil.parseXml(xmlStr);
        NodeList nodeList = doc.getElementsByTagName("Row");
        if(doc.getElementsByTagName("Data").item(0).getFirstChild().getNodeValue().equals("000")) {
            //根据查看到的报文格式，第一个<Row>里面全是空值，第二个<Row>是字段，所以解析数据从第三个下标开始
            if(nodeList.getLength() > 2) {
                for (int i = 2; i < nodeList.getLength(); i++) {
                    JSONObject jsb = new JSONObject();
                    Node item = nodeList.item(i);

                    int j = 0;
                    //解析<Row>里面的每个<Data>
                    for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
                        //NodeType == Node.ELEMENT_NODE 判断节点是否是一个 Element
                        if(node.getNodeType() == Node.ELEMENT_NODE) {
                            Node firstChild = node.getFirstChild();

                            if (firstChild != null) {
                                jsb.put(requiredItems[j], firstChild.getNodeValue());
                            } else {
                                jsb.put(requiredItems[j], "");
                            }
                            j++;
                        }
                    }

                    result.add(jsb);
                }
            }
        }
        return result;
    }

}

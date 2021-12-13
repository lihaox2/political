package com.bayee.political.data.injection.task;

import com.bayee.political.data.injection.service.TrafficViolationService;
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
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 全国机动车违法违章数据拉取任务
 *
 * @author xxl
 * @date 2021/12/8
 */
@Component
@EnableScheduling
@Controller
public class TrafficViolationTask {

    @Autowired
    TrafficViolationService trafficViolationService;

    private final static String SENDER_ID = "C33-00006094";
    private final static String SERVICE_ID = "S10-10003348";

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void dataRenew() {
        try {
            RbspService service = new RbspService(SENDER_ID, SERVICE_ID);
            service.setUserCardId("330481198701132616");
            service.setUserDept("330499320000");
            service.setUserName("范海华");

            RbspCall call = service.createCall();
            call.setUrl("http://10.118.7.51:8080/node");
            call.setMethod(RbspConsts.METHOD_QUERY);
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("DataObjectCode", "ZJ192_330000");
            //params.put("DataObjectCode", "共享数据项集");
            params.put("InfoCodeMode", "1");//信息代码输出模式，不用动
            //查询条件见接口信息-字段说明-输入项  and WSMC_PG like '%行政处'                    立案决定书 （立案告知书） 受案登记表  退还保证金决定通知书


            //立案告知书  JABH_PG = 'A3304995400002021035052'  and WSMC_PG like '%受案登记表'
            params.put("Condition", "JABH_PG = 'A3304995400002021035052'"); //
            //结果信息见接口信息-字段说明-输出项                  主键    警案编号       文书名称   主办人身份证号     主办人姓名      开具结果       批准人姓名      批准时间    添加修改时间
            // params.put("RequiredItems", new String[]{"ID_PG","JABH_PG","WSMC_PG","ZBR_SFZH_PG","ZBR_XM_PG","KJJG_PG","PZR_XM_PG" ,"PZSJ_PG","TJXGSJ_PG", "LRSJ_PG"});
            params.put("RequiredItems", new String[]{}); //"WSMC_PG","LRSJ_PG"
            String result = call.invoke(params);
            //System.err.println(result);
            StringReader sr = new StringReader(result);
            InputSource is = new InputSource(sr);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder= factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            NodeList nl = doc.getElementsByTagName("Row");
            Map<String,Object> map=new HashMap<String, Object>();
            if(doc.getElementsByTagName("Data").item(0).getFirstChild().getNodeValue().equals("000")  ) {
                System.out.println(nl.getLength());
//				      String nodeValue = doc.getElementsByTagName("Data").item(1).getFirstChild().getNodeValue();
//					 System.err.println(nodeValue);

                if( nl.getLength() > 2) {
                    for (int i = 2; i < nl.getLength(); i++) {
                        System.out.println(i);
                        Node item = nl.item(i);
                        String wsmc=null;
                        int tem=0;
                        for (Node node= item.getFirstChild() ; node != null ;node =node.getNextSibling()) {


                            if( node.getNodeType() == Node.ELEMENT_NODE) {

                                String nodeName = node.getNodeName();
                                String nodeValue = node.getFirstChild().getNodeValue();
                                System.err.println(nodeName + "   :   "+nodeValue);
                                if( tem == 0) {
                                    wsmc=node.getFirstChild().getNodeValue();
                                    tem++;
                                    map.put(node.getFirstChild().getNodeValue(), null);
                                } else {
                                    map.put(wsmc, node.getFirstChild().getNodeValue());
                                }

                            }

                        }
                    }
                }



            }
            System.err.println(map.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //浙江省信息共享-执法办案立案决定书查询服务方   S33-00000270
    //浙江省信息共享-执法办案已有文书信息查询服务方
    // 446  浙江省浙江信息共享-执法办案已有文书记录信息(执法二期)服务方   S33-00000446   ZJ192_330000
    //298 浙江省信息共享-执法办案警情信息服务方   S330-00000298   ZJ104_330000
    public static void main(String[] args) {
        try {
            RbspService service = new RbspService("C33-00006094","S33-00000446");
            service.setUserCardId("330481198701132616");////设置用户身份编号
            service.setUserDept("330499320000"); ////设置用户单位
            service.setUserName("范海华"); //设置用户名
//				service.setPkiId("");
            RbspCall call = service.createCall();
            call.setUrl("http://10.118.7.51:8080/node");
            call.setMethod(RbspConsts.METHOD_QUERY);
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("DataObjectCode", "ZJ192_330000");
            //params.put("DataObjectCode", "共享数据项集");
            params.put("InfoCodeMode", "1");//信息代码输出模式，不用动
            //查询条件见接口信息-字段说明-输入项  and WSMC_PG like '%行政处'                    立案决定书 （立案告知书） 受案登记表  退还保证金决定通知书


            //立案告知书  JABH_PG = 'A3304995400002021035052'  and WSMC_PG like '%受案登记表'
            params.put("Condition", "JABH_PG = 'A3304995400002021035052'"); //
            //结果信息见接口信息-字段说明-输出项                  主键    警案编号       文书名称   主办人身份证号     主办人姓名      开具结果       批准人姓名      批准时间    添加修改时间
            // params.put("RequiredItems", new String[]{"ID_PG","JABH_PG","WSMC_PG","ZBR_SFZH_PG","ZBR_XM_PG","KJJG_PG","PZR_XM_PG" ,"PZSJ_PG","TJXGSJ_PG", "LRSJ_PG"});
            params.put("RequiredItems", new String[]{}); //"WSMC_PG","LRSJ_PG"
            String result = call.invoke(params);
            //System.err.println(result);
            StringReader sr = new StringReader(result);
            InputSource is = new InputSource(sr);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder= factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            NodeList nl = doc.getElementsByTagName("Row");
            Map<String,Object> map=new HashMap<String, Object>();
            if(doc.getElementsByTagName("Data").item(0).getFirstChild().getNodeValue().equals("000")  ) {
                System.out.println(nl.getLength());
//				      String nodeValue = doc.getElementsByTagName("Data").item(1).getFirstChild().getNodeValue();
//					 System.err.println(nodeValue);

                if( nl.getLength() > 2) {
                    for (int i = 2; i < nl.getLength(); i++) {
                        System.out.println(i);
                        Node item = nl.item(i);
                        String wsmc=null;
                        int tem=0;
                        for (Node node= item.getFirstChild() ; node != null ;node =node.getNextSibling()) {


                            if( node.getNodeType() == Node.ELEMENT_NODE) {

                                String nodeName = node.getNodeName();
                                String nodeValue = node.getFirstChild().getNodeValue();
                                System.err.println(nodeName + "   :   "+nodeValue);
                                if( tem == 0) {
                                    wsmc=node.getFirstChild().getNodeValue();
                                    tem++;
                                    map.put(node.getFirstChild().getNodeValue(), null);
                                } else {
                                    map.put(wsmc, node.getFirstChild().getNodeValue());
                                }

                            }

                        }
                    }
                }



            }
            System.err.println(map.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

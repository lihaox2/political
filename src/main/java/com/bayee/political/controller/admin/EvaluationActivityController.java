package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.RecodeUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tlt
 * @date 2021/11/15
 */
@RestController
@RequestMapping("/evaluationActivity")
public class EvaluationActivityController {

    @Autowired
    private EvaluationActivityService evaluationActivityService;

    @Autowired
    private EvaluationTopicService evaluationTopicService;

    @Autowired
    private EvaluationObjectService evaluationObjectService;

    @Autowired
    private EvaluationInfoService evaluationService;

    @Autowired
    private UserService userService;

    /**
     * 评价活动-新增活动与题目
     * @param saveParam
     * @return
     */
    @PostMapping("/activity/add")
    public ResponseEntity<?> addEvaluationActivity(@RequestBody EvaluationActivitySaveParam saveParam){
        EvaluationActivity evaluationActivity = new EvaluationActivity();
        evaluationActivity.setActivityName(saveParam.getActivityName());
        evaluationActivity.setBelongMonth(DateUtils.parseDate(saveParam.getBelongMonth(), "yyyy-MM"));
        evaluationActivity.setActivityStatus(1);
        evaluationActivity.setCreationDate(new Date());
        String idNum = DateUtils.formatDate(new Date(), "yyyyMMdd") + new Date().getTime();
        evaluationActivity.setIdNum(idNum);
        evaluationActivity.setPoliceId(saveParam.getPoliceId());

        evaluationActivityService.addEvaluationActivity(evaluationActivity);

        for (EvaluationTopicSaveParam topicSaveParam : saveParam.getTopicSaveParamsList()){
            EvaluationTopic evaluationTopic = new EvaluationTopic();
            evaluationTopic.setIsChoose(0);
            evaluationTopic.setUserId(0);
            evaluationTopic.setCreationDate(new Date());
            evaluationTopic.setTopicName(topicSaveParam.getTopicName());
            evaluationTopic.setActivityId(evaluationActivity.getId());
            evaluationTopicService.addEvaluationTopic(evaluationTopic);
        }

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 评价活动-删除活动
     * @param id
     * @return
     */
    @DeleteMapping("/activity/delete")
    public ResponseEntity<?> deleteActivity(Integer id) {
        evaluationActivityService.deleteEvaluationActivity(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 评价活动-活动详情
     * @param id
     * @return
     */
    @GetMapping("/activity/details")
    public ResponseEntity<?> activityDetails(@RequestParam("id") Integer id){
        EvaluationActivity evaluationActivity = evaluationActivityService.findById(id);
        User user = userService.findByPoliceId(evaluationActivity.getPoliceId());

        ActivityDetailsResult result = new ActivityDetailsResult();
        result.setId(evaluationActivity.getId());
        result.setIdNum(evaluationActivity.getIdNum());
        result.setActivityName(evaluationActivity.getActivityName());
        result.setBelongMonth(DateUtils.formatDate(evaluationActivity.getBelongMonth(), "yyyy-MM"));
        result.setActivityStatus(evaluationActivity.getActivityStatus());
        result.setCreationDate(DateUtils.formatDate(evaluationActivity.getCreationDate(), "yyyy-MM-dd HH:mm:ss"));
        result.setTopicCount(evaluationTopicService.countEvaluationTopic(id));
        result.setHaveEvaluationCount(evaluationService.countHaveEvaluation(id));
        result.setName(user.getName());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 评价活动-活动列表
     * @param queryParam
     * @return
     */
    @GetMapping("/activity/page")
    public ResponseEntity<?> EvaluationActivityPage
            (ActivityPageQueryParam queryParam){
        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", evaluationActivityService.activityPage(queryParam));
        result.put("totalCount", evaluationActivityService.activityPageCount(queryParam));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 评价活动-结束活动
     * @param id
     * @return
     */
    @GetMapping("/activity/overActivity")
    public ResponseEntity<?> overActivity(@RequestParam("id") Integer id){
        evaluationActivityService.overActivity(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 评价活动-一键导出
     * @param response
     * @param queryParam
     */
    @GetMapping("/activity/exportExcel")
    public void exportExcel(HttpServletResponse response, ActivityPageQueryParam queryParam){
        evaluationActivityService.exportExcel(response, "评价活动", queryParam);
    }

    /**
     *评价活动-生成二维码
     * @param response
     * @param url
     */
    @GetMapping("/activity/QRCode")
    public void QRCode(HttpServletResponse response, String url){
        RecodeUtil.creatRrCode(url, 180,180,response);
    }

    /**
     * 评价活动-已开始活动
     * @param userId
     * @return
     */
    @GetMapping("/activity/startedSatus")
    public ResponseEntity<?> startedSatusActivity(@RequestParam("userId") Integer userId){
        return new ResponseEntity<>(DataListReturn.ok(evaluationActivityService.findStartedSatusActivity(userId)),
                HttpStatus.OK);
    }

    /**
     *下载二维码
     * @param request
     * @param response
     * @param url
     * @throws Exception
     */
    @GetMapping("/activity/dowanloadQRCode")
    public void dowanloadQRCode(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        Integer id = Integer.valueOf(url.substring(url.indexOf("=")+1));

        EvaluationActivity activity = evaluationActivityService.findById(id);
        String fileName = activity.getActivityName() + "_" + activity.getIdNum() + ".png";

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 200, 200, hints);
        //设置请求头
        response.setHeader("Content-Type","application/octet-stream");
        //response.setHeader("Content-Disposition", "attachment;filename=" + "二维码.png");
        response.setHeader("Content-disposition", "attachment;filename=" +
                new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     *评价活动-新增评价
     * @param saveParam
     * @return
     */
    @PostMapping("/evaluation/add")
    public ResponseEntity<?> addEvaluation(@RequestBody EvaluationSaveParam saveParam){
        evaluationService.addEvaluation(saveParam);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 评价活动-评价列表
     * @param queryParam
     * @return
     */
    @GetMapping("/evaluation/page")
    public ResponseEntity<?> evaluationPage(EvaluationPageQueryParam queryParam){
        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", evaluationService.evaluationPage(queryParam));
        result.put("totalCount", evaluationService.evaluationPageCount(queryParam));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     *评价活动-评价数据
     * @param activityId
     * @return
     */
    @GetMapping("/evaluation/pageCount")
    public ResponseEntity<?> evaluationCount(@RequestParam("activityId") Integer activityId){
        return new ResponseEntity<>(DataListReturn.ok(evaluationService.evaluationCount(activityId)), HttpStatus.OK);
    }

    /**
     * 评价活动-评价详情
     * @param id
     * @return
     */
    @GetMapping("/evaluation/details")
    public ResponseEntity<?> evaluationDetails(@RequestParam("id") Integer id){
        EvaluationInfo evaluation = evaluationService.findById(id);
       /* EvaluationInfoUser user = userService.findById(evaluation.getUserId());
        EvaluationObject evaluationObject = evaluationObjectService.findByPoliceId(user.getFamilyPoliceId());*/
        EvaluationTopic evaluationTopic = new EvaluationTopic();
        evaluationTopic.setActivityId(evaluation.getActivityId());
        evaluationTopic.setUserId(evaluation.getUserId());

        List<EvaluationTopic> evaluationTopicList =
                evaluationTopicService.findEvaluationTopicList(evaluationTopic);

        EvaluationDetailsResult result = new EvaluationDetailsResult();
        /*result.setName(user.getName());*/
        result.setActivityName(evaluationActivityService.findById(evaluation.getActivityId()).getActivityName());
        /*result.setObjectName(evaluationObject.getObjectName());
        result.setPoliceId(evaluationObject.getPoliceId());*/
        result.setTotalScore(evaluation.getTotalScore());
        result.setBusinessTime(DateUtils.formatDate(evaluation.getCreationDate(), "yyyy-MM-dd HH:mm:ss"));
        result.setTopicList(evaluationTopicList);
        if (evaluationTopicList != null && evaluationTopicList.size() > 0) {
            result.setTopicCount(evaluationTopicList.size());
        } else {
            result.setTopicCount(0);
        }

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     *评价活动-开始评价
     * @param startParam
     * @return
     */
    @PostMapping("/evaluation/start")
    public ResponseEntity<?> startEvaluation(@RequestBody EvaluationStartParam startParam){
        return new ResponseEntity<>(DataListReturn.ok(evaluationService.startEvaluation(startParam)), HttpStatus.OK);
    }

    /**
     *评价活动-一键导出
     * @param response
     * @param queryParam
     */
    @GetMapping("/evaluation/exportExcel")
    public void exportExcel(HttpServletResponse response, EvaluationPageQueryParam queryParam){
        evaluationService.exportExcel(response, queryParam);
    }
}

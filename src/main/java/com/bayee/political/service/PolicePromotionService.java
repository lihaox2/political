package com.bayee.political.service;

import com.bayee.political.domain.PolicePromotionRecordInfo;
import com.bayee.political.json.LinkageResult;
import com.bayee.political.json.PolicePromotionInfoResult;
import com.bayee.political.json.PolicePromotionPageListParam;
import com.bayee.political.utils.JsonResult;
import org.apache.poi.ss.formula.functions.T;

import java.text.ParseException;
import java.util.List;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/16 18:31
 */
public interface PolicePromotionService {

    JsonResult<T> pageList(PolicePromotionPageListParam param) throws ParseException;

    JsonResult<List<LinkageResult>> rankList();

    JsonResult<PolicePromotionInfoResult> info(String policeId);

    JsonResult<List<LinkageResult>>postList();

    JsonResult<List<LinkageResult>> depList();

    JsonResult<T> export(PolicePromotionPageListParam param);

    JsonResult<T> add(String policeId,Integer type) throws ParseException;

    JsonResult<T> adds() throws ParseException;

}

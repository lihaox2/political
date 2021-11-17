package com.bayee.political.service;

import com.bayee.political.domain.PolicePromotionRecordInfo;
import com.bayee.political.json.LinkageResult;
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

    JsonResult<PolicePromotionRecordInfo> info(Integer id);
}

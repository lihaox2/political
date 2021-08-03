package com.bayee.political.controller.admin;


import com.bayee.political.json.TrainCensusResult;
import com.bayee.political.service.PoliceSkillsService;
import com.bayee.political.service.TrainProjectService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zouya
 */
@RestController
@RequestMapping("/train/v2")
public class PoliceSkillsController {

    @Autowired
    TrainProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    PoliceSkillsService policeSkillsService;

    /**
     * 统计：训练总数、本月训练数、参与总人数、总合格率
     * @return
     */
    @GetMapping("/census")
    public ResponseEntity<?> census(){
        TrainCensusResult result=policeSkillsService.census();
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 警员总体合格率top10、综合体能合格率top10、枪械合格率top10
     * @return
     */
    @GetMapping("/rankData")
    public ResponseEntity<?> rankData(){
        return new ResponseEntity<>(DataListReturn.ok(policeSkillsService.rankData()),HttpStatus.OK);
    }


    /**
     * 近6个月不同训练类型合格情况趋势
     * @return
     */
    @GetMapping("/near6Month")
    public ResponseEntity<?> near6Month(){
        return new ResponseEntity<>(DataListReturn.ok(policeSkillsService.near6Month()),HttpStatus.OK);
    }

    /**
     * 不同类型训练数量占比
     * @return
     */
    @GetMapping("/trainingQuantity")
    public ResponseEntity<?> trainingQuantity(){
        return new ResponseEntity<>(DataListReturn.ok(policeSkillsService.trainingQuantity()),HttpStatus.OK);
    }

}

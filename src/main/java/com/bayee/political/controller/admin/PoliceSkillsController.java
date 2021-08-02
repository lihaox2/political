package com.bayee.political.controller.admin;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.domain.*;
import com.bayee.political.service.TrainProjectService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.ExcelUtil;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


}

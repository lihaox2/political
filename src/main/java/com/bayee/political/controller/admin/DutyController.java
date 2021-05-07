package com.bayee.political.controller.admin;

import com.bayee.political.pojo.json.DutyPageResult;
import com.bayee.political.pojo.json.DutySaveParam;
import com.bayee.political.utils.DataListReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/5/7
 */
@RestController
public class DutyController {

    /**
     * 接警执勤
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/duty/page")
    public ResponseEntity<?> dutyPage(@RequestParam("pageIndex") Integer pageIndex,
                                        @RequestParam("pageSize") Integer pageSize) {
        DutyPageResult pageResult = new DutyPageResult();

        Map<String, Object> result = new HashMap<>();
        result.put("data", pageResult);
        result.put("totalCount", 100);
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/duty")
    public ResponseEntity<?> addDuty(@RequestBody DutySaveParam saveParam) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/duty")
    public ResponseEntity<?> updateDuty(@RequestParam("id") Integer id, @RequestBody DutySaveParam saveParam) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/duty/details")
    public ResponseEntity<?> dutyDetails(@RequestParam("id") Integer id) {
        DutyPageResult result = new DutyPageResult();

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/duty")
    public ResponseEntity<?> deleteDuty(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

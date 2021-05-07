package com.bayee.political.controller.admin;

import com.bayee.political.pojo.json.HealthPageResult;
import com.bayee.political.utils.DataListReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康
 *
 * @author xxl
 * @date 2021/5/7
 */
@RestController
public class HealthController {

    /**
     * 健康管理
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/health/page")
    public ResponseEntity<?> healthPage(@RequestParam("pageIndex") Integer pageIndex,
                                        @RequestParam("pageSize") Integer pageSize) {
        HealthPageResult pageResult = new HealthPageResult();

        Map<String, Object> result = new HashMap<>();
        result.put("data", pageResult);
        result.put("totalCount", 100);
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/health")
    public ResponseEntity<?> addHealth() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/health")
    public ResponseEntity<?> updateHealth(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/health/details")
    public ResponseEntity<?> healthDetails(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/health")
    public ResponseEntity<?> deleteHealth(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

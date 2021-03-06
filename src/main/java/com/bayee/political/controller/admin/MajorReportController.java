package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.Word2Pdf;
import org.apache.commons.io.FileUtils;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @author tlt
 * @date 2021/11/4
 */
@RestController
@RequestMapping("/major")
public class MajorReportController {

    @Autowired
    private MajorReportService reportService;

    @Autowired
    private MajorAccessoryService accessoryService;

    @Autowired
    private MajorAuditService auditService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PolicePositionService positionService;

    @Autowired
    private MajorReportRecordService reportRecordService;

    @Value("${path.storagePath}")
    private String storagePath;

    @Value("${path.openOffice4path}")
    private String openOffice4path;

    private String tempPath=System.getProperty("java.io.tmpdir");

    @PostMapping("/report/add")
    public ResponseEntity<?> addMajorReport(@RequestBody MajorReportSaveParam saveParam){
        MajorReport report = new MajorReport();

        report.setPoliceId(saveParam.getPoliceId());
        report.setReportDescription(saveParam.getReportDescription());
        report.setReportContent(saveParam.getReportContent());
        report.setIsRisk(saveParam.getIsRisk());
        report.setReportCommitment(saveParam.getReportCommitment());
        report.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime(), "yyyy-MM-dd"));
        report.setCreationDate(new Date());
        report.setReportStatus(saveParam.getReportStatus());
        report.setDeptStatus(0);
        report.setBureauStatus(0);
        report.setIsAgain(0);
        reportService.addReport(report);

        List<MajorAccessory> accessoryList = saveParam.getAccessoryList();
        if (accessoryList != null && accessoryList.size() > 0) {
            for (MajorAccessory accessory : accessoryList) {
                MajorAccessory majorAccessory = new MajorAccessory();
                majorAccessory.setId(accessory.getId());
                majorAccessory.setUploadPath(accessory.getFileName());
                majorAccessory.setReportId(report.getId());
                Integer fileNameIndex = majorAccessory.getUploadPath().lastIndexOf("/") + 1;
                String fileNameStr = majorAccessory.getUploadPath().substring(fileNameIndex);
                majorAccessory.setFileName(fileNameStr);
                majorAccessory.setCreationDate(new Date());
                accessoryService.updateAccessory(majorAccessory);
            }
        }

        if (saveParam.getReportStatus() == 1){
            MajorReportRecord reportRecord = new MajorReportRecord();
            reportRecord.setReportId(report.getId());
            reportRecord.setCreationDate(new Date());
            reportRecord.setReportDate(new Date());
            reportRecordService.insert(reportRecord);
        }

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/report/update")
    public ResponseEntity<?> updateMajorReport(@RequestBody MajorReportSaveParam saveParam){
        MajorReport report = reportService.findById(saveParam.getId());
        report.setReportDescription(saveParam.getReportDescription());
        report.setReportContent(saveParam.getReportContent());
        report.setIsRisk(saveParam.getIsRisk());
        report.setReportCommitment(saveParam.getReportCommitment());
        report.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime(), "yyyy-MM-dd"));
        report.setUpdateDate(new Date());
        report.setReportStatus(saveParam.getReportStatus());
        report.setDeptStatus(0);
        report.setBureauStatus(0);
        report.setIsAgain(saveParam.getIsAgain());

        if (report.getReportStatus() == 1) {
            MajorReportRecord reportRecord = new MajorReportRecord();
            reportRecord.setReportId(saveParam.getId());
            reportRecord.setCreationDate(new Date());
            reportRecord.setReportDate(new Date());
            reportRecordService.insert(reportRecord);
        }

        reportService.updateReport(report);

        List<MajorAccessory> accessoryList = saveParam.getAccessoryList();
        if (accessoryList != null && accessoryList.size() > 0) {
            for (MajorAccessory accessory : accessoryList) {
                accessory.setReportId(saveParam.getId());
                accessoryService.updateAccessory(accessory);
            }
        }

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/report/details")
    public ResponseEntity<?> MajorReportDetails(@RequestParam("id") Integer id) {
        MajorReport report = reportService.findById(id);
        MajorReportDetailsResult result = new MajorReportDetailsResult();
        List<MajorReportAndAuditRecord> recordList = new ArrayList<>();
            // ???????????????????????????????????????
            List<MajorAuditDetailsResult> auditList = auditService.findByReportIdAndStatus(id);
            List<ReportRecordResult> reportRecordList = reportRecordService.findAll(id);

            // ????????????????????????
            if (auditList.size() != 0) {
            for (ReportRecordResult record : reportRecordList) {
                MajorReportAndAuditRecord majorReportAndAuditRecord = new MajorReportAndAuditRecord();
                List<MajorAuditDetailsResult> detailsResultList = new ArrayList<>();
                majorReportAndAuditRecord.setRecord(record);
                for (MajorAuditDetailsResult detailsResult : auditList) {
                    if (record.getId() == detailsResult.getRecordId()) {
                        detailsResultList.add(detailsResult);
                    }
                }
                majorReportAndAuditRecord.setResultList(detailsResultList);
                recordList.add(majorReportAndAuditRecord);
             }
            } else {
                if (reportRecordList.size() != 0) {
                    MajorReportAndAuditRecord majorReportAndAuditRecord = new MajorReportAndAuditRecord();
                    majorReportAndAuditRecord.setRecord(reportRecordList.get(0));
                    recordList.add(majorReportAndAuditRecord);
                }
            }

        if (recordList.size() > 0) {

        // ????????????????????????
        MajorReportAndAuditRecord reportAndAuditRecord = recordList.get(0);
        List<MajorAuditDetailsResult> majorAuditDetailsResultList = reportAndAuditRecord.getResultList();

        // ?????????????????????
        if (majorAuditDetailsResultList != null) {
            // ????????????????????????
            if (report.getReportStatus() != 2) {
                MajorAuditDetailsResult detailsResult = new MajorAuditDetailsResult();
                // ?????????????????????
                if (report.getDeptStatus() == 0) {
                    detailsResult.setIsAudit(1);
                } else {
                    detailsResult.setIsAudit(2);
                }
                majorAuditDetailsResultList.add(0, detailsResult);
                reportAndAuditRecord.setResultList(majorAuditDetailsResultList);
            }
        } else {
            List<MajorAuditDetailsResult> tempDetailsResultList = new ArrayList<>();
            MajorAuditDetailsResult detailsResult = new MajorAuditDetailsResult();
            detailsResult.setIsAudit(1);
            tempDetailsResultList.add(detailsResult);
            reportAndAuditRecord.setResultList(tempDetailsResultList);
        }

        recordList.add(0, reportAndAuditRecord);

        recordList.remove(0+1);

        }

        result.setMajorReportAndAuditRecords(recordList);

        User user = userService.findByPoliceId(report.getPoliceId());
        PolicePosition position = userService.getPolicePositionByPoliceId(report.getPoliceId());
        Department dept = departmentService.findById(user.getDepartmentId());
        List<MajorAccessory> accessoryList = accessoryService.findByReportId(id);

        result.setId(report.getId());
        result.setPoliceName(user.getName());
        result.setDepartmentName(dept.getName());
        result.setPositionName(position.getPositionName());
        result.setBusinessTime(DateUtils.formatDate(report.getBusinessTime(), "yyyy-MM-dd"));
        result.setReportDescription(report.getReportDescription());
        result.setReportContent(report.getReportContent());
        result.setIsRisk(report.getIsRisk());
        result.setReportCommitment(report.getReportCommitment());
        result.setAccessoryList(accessoryList);

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/report/page")
    public ResponseEntity<?> majorReportPage(MajorReportPageQueryParam queryParam){
        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", reportService.majorReportPage(queryParam));
        result.put("totalCount", reportService.majorReportPageCount(queryParam));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/audit/page")
    public ResponseEntity<?> majorAuditPage(MajorAuditPageQueryParam queryParam){
        User user = userService.findByPoliceId(queryParam.getPoliceId());
            // ????????????????????????
        PolicePosition position = userService.getPolicePositionByPoliceId(queryParam.getPoliceId());
        queryParam.setUserPositionId(position.getId());

        if (user.getIsUnitLeader() == 0) {
            return new ResponseEntity<>(DataListReturn.error("???????????????????????????"), HttpStatus.OK);
        }

        if (queryParam.getDeptId() == null) {
            // ????????????????????????
            Department department = departmentService.findById(user.getDepartmentId());
            if (position.getId() != 1) {
                queryParam.setDeptId(department.getId());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", auditService.majorReportPage(queryParam));
        result.put("totalCount", auditService.majorReportPageCount(queryParam));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/history/page")
    public ResponseEntity<?> historyPage(HistoryPageQueryParam queryParam){
        User user = userService.findByPoliceId(queryParam.getPoliceId());
        // ????????????????????????
        PolicePosition position = userService.getPolicePositionByPoliceId(queryParam.getPoliceId());
        queryParam.setUserPositionId(position.getId());

        if (user.getIsUnitLeader() == 0) {
            return new ResponseEntity<>(DataListReturn.error("???????????????????????????"), HttpStatus.OK);
        }

        if (queryParam.getDeptId() == null) {
            // ????????????????????????
            Department department = departmentService.findById(user.getDepartmentId());
            if (position.getId() != 1) {
                queryParam.setDeptId(department.getId());
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", auditService.historyPage(queryParam));
        result.put("totalCount", auditService.historyPageCount(queryParam));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/report/delete")
    public ResponseEntity<?> deleteMajorReport(@RequestParam("id") Integer id){
        reportService.deleteReport(id);
        accessoryService.deleteByReportId(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/report/data")
    public ResponseEntity<?> majorReportData(@RequestParam("policeId") String policeId){
        User user = userService.findByPoliceId(policeId);

        MajorReportData data = new MajorReportData();
        data.setPoliceName(user.getName());
        data.setPosition(userService.getPolicePositionByPoliceId(policeId).getPositionName());
        data.setDeptName(departmentService.findById(user.getDepartmentId()).getName());
        return new ResponseEntity<>(DataListReturn.ok(data), HttpStatus.OK);
    }

    @PostMapping("/audit/add")
    public ResponseEntity<?> addMajorAudit(@RequestBody MajorAuditSaveParam saveParam){
        PolicePosition position = userService.getPolicePositionByPoliceId(saveParam.getPoliceId());
        MajorReport report = reportService.findById(saveParam.getReportId());
        // ?????????????????????
        if (position.getId() == 1 && saveParam.getAuditResult() == 1) {
            report.setBureauStatus(1);
            report.setReportStatus(2);
            saveParam.setAuditStatus(2);
        // ??????????????????
        } else if (position.getId() == 1 && saveParam.getAuditResult() == 2) {
            report.setBureauStatus(2);
            report.setReportStatus(2);
            saveParam.setAuditStatus(2);
        // ??????????????????
        } else if ((position.getId() == 2 || position.getId() == 3) && saveParam.getAuditResult() == 1) {
            report.setDeptStatus(1);
            saveParam.setAuditStatus(1);
        // ???????????????
        } else if ((position.getId() == 2 || position.getId() == 3) && saveParam.getAuditResult() == 2) {
            report.setDeptStatus(2);
            report.setReportStatus(2);
            saveParam.setAuditStatus(1);
        }
        reportService.updateReport(report);

        MajorReportRecord record = reportRecordService.findByMaxCreationDate(report.getId());
        MajorAudit audit = new MajorAudit();
        audit.setPoliceId(saveParam.getPoliceId());
        audit.setReportId(saveParam.getReportId());
        audit.setOpinion(saveParam.getOpinion());
        audit.setAuditStatus(saveParam.getAuditStatus());
        audit.setAuditResult(saveParam.getAuditResult());
        audit.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime(), "yyyy-MM-dd HH:mm"));
        audit.setCreationDate(new Date());
        audit.setRecordId(record.getId());

        auditService.addMajorAudit(audit);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/audit/deptData")
    public ResponseEntity<?> deptData() {
        List<Department> departmentList = departmentService.findAll();
        return new ResponseEntity<>(DataListReturn.ok(departmentList), HttpStatus.OK);
    }

    @GetMapping("/audit/positionData")
    public ResponseEntity<?> positionData() {
        List<PolicePosition> positionList = positionService.findAll();
        return new ResponseEntity<>(DataListReturn.ok(positionList), HttpStatus.OK);
    }

    @GetMapping("/accessory/details")
    public ResponseEntity<?> pdfStreamHandler(HttpServletRequest request, HttpServletResponse response, Integer id, String url) throws IOException {

        MajorAccessory accessory = accessoryService.findById(id);

        String [] fileNames = accessory.getFileName().split("\\.");

        if (fileNames[1].equals("jpg") || fileNames[1].equals("png") || fileNames[1].equals("pdf")) {
            return new ResponseEntity<>(DataListReturn.ok(accessory.getUploadPath()), HttpStatus.OK);
        }

        if (fileNames[1].equals("docx")) {
            String inputFile = url + accessory.getUploadPath();
            String pdfFile = accessory.getUploadPath().substring(0, accessory.getUploadPath().indexOf(".")) + ".pdf";
         //   String inputFile = "/mnt/qiantang/img/????????????API.docx";
         //   String pdfFile = "/mnt/qiantang/img/????????????API.pdf";
            return new ResponseEntity<>(DataListReturn.ok(Word2Pdf.doc2pdf(inputFile, pdfFile, url)), HttpStatus.OK);
        }

        return null;
    }

    @GetMapping("/readFile")
    public void readFile(@RequestParam(value = "uri",required = true) String uri,HttpServletResponse response) {
        try{
            File file=null;
            //????????????
//            String uri="http://8.136.146.186:8099/static/img/";
//            String uri="http://195.195.0.164/xsmms/xsmms/1629601685566.docx?Expires=1944961677&OSSAccessKeyId=BoKEoPHgPCzc47bT&Signature=zceDeZqLAk3mXIsL33qaxdO7qf8%3D";
            //??????????????????????
            String fileName=uri.substring(uri.lastIndexOf("/")+1);
            //??????FileUtils????????????????????????????????????????????????
            URL url=new URL(uri);
            FileUtils.copyURLToFile(url,new File(tempPath+File.separator+fileName));
            //??????????????????
            file = new File(tempPath + File.separator + fileName);

            //???????????????
//            file=new File("C:\\Users\\BAIYI\\Desktop\\??????\\????????????oss.docx");
            InputStream inputStream=null;
            OutputStream outputStream=null;
            //??????????????????????????????pdf??????word??????excel?????????pdf??????????????????pdf??????
            String fileSuffix=fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!fileSuffix.equals("pdf")){
                file=openOfficeToPDF(fileName,file);
            }
            //???????????????
//            file=openOfficeToPDF("????????????oss.docx",file);
            //?????????????????????
            if (file != null) {
                inputStream = new FileInputStream(file);
            }
            response.setContentType("application/pdf");
            outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(b)) != -1) {
                outputStream.write(b);
            }
            inputStream.close();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/accessory/add")
    public ResponseEntity<?> accessoryAdd(String fileName) throws IOException {
        MajorAccessory majorAccessory = new MajorAccessory();
        Integer fileNameIndex = fileName.lastIndexOf("/") + 1;
        String fileNameStr = fileName.substring(fileNameIndex);
        majorAccessory.setFileName(fileNameStr);
        majorAccessory.setUploadPath(fileName);
        majorAccessory.setCreationDate(new Date());
        accessoryService.addAccessory(majorAccessory);

        return new ResponseEntity<>(DataListReturn.ok(majorAccessory.getId()), HttpStatus.OK);
    }

    @GetMapping("/accessory/delete")
    public ResponseEntity<?> accessoryMajorReport(HttpServletRequest request, HttpServletResponse response, Integer id, String url){

        MajorAccessory accessory = accessoryService.findById(id);
        String fileUrl = url + accessory.getUploadPath();
        File file = new File(fileUrl);
        if (file.exists()){
            file.delete();
        }

        accessoryService.deleteAccessory(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    public File openOfficeToPDF(String fileName, File file) {
        try {
            //????????????????????????
            String sourceFileName = fileName.substring(0, fileName.lastIndexOf("."));
            //??????openOffice
            DefaultOfficeManagerBuilder builder = new DefaultOfficeManagerBuilder();
            //???????????????pdf????????????????????????????????????????????????????????????????????????
            del(storagePath);
            //?????????????????????file??????????????????????????????????????????PDF??????
            String after_convert_file_path = storagePath + sourceFileName + ".pdf";
            File outputFile = new File(after_convert_file_path);
            if (!outputFile.getParentFile().exists()) {
                //??????????????????????????????????????????
                outputFile.getParentFile().mkdirs();
            }
            //??????openOffice4????????????????????????
//            String openOffice4path = "E:/ToolApp/openoffice";
            builder.setOfficeHome(openOffice4path);
            OfficeManager officeManager = builder.build();
//          officeManager.start();
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            //????????????PDF
            converter.convert(file, outputFile);
            officeManager.stop();
            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void del(String filePath)throws IOException {
        File f=new File(filePath);//??????????????????
        //???????????????????????????
        if(f.exists()&&f.isDirectory())
        {
            if(f.listFiles().length==0)
            {
                //???????????????????????????????????????
                f.delete();
            }
            else
            {
                //????????????????????????????????????????????????????????????
                File[] delFile=f.listFiles();
                for(int j=0;j<delFile.length;j++)
                {
                    if(delFile[j].isDirectory())
                    {
                        //????????????del??????????????????????????????
                        del(delFile[j].getAbsolutePath());
                    }
                    delFile[j].delete();//????????????
                }
            }
            del(filePath);//????????????
        }
    }
}

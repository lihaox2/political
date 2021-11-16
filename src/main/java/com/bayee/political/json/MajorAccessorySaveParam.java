package com.bayee.political.json;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author tlt
 * @date 2021/11/11
 */
public class MajorAccessorySaveParam {

    /**
     * 文件名称
     */
    private String fileName;

    private MultipartFile[] fileArr;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile[] getFileArr() {
        return fileArr;
    }

    public void setFileArr(MultipartFile[] fileArr) {
        this.fileArr = fileArr;
    }
}

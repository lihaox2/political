package com.bayee.political.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lichenghu
 * @Title: 警员晋升分页传参
 * @Description: )
 * @date 2021/11/16 17:22
 */
@Data
@ApiModel("警员晋升分页传参")
public class PolicePromotionPageListParam {

    @ApiModelProperty("当前页数")
    private Integer pageIndex;

    @ApiModelProperty("当前页的数据条数")
    private Integer pageSize;

    @ApiModelProperty("年份")
    @JsonFormat( pattern="yyyy")
    private Date particularYear;

    @ApiModelProperty("季度")
    private Integer quarter;

    @ApiModelProperty("类型（0一般，1量化）")
    private Integer type;

    @ApiModelProperty("部门id")
    private Integer depId;

    @ApiModelProperty("晋升等级id")
    private Integer nowPoliceLevelId;

    @ApiModelProperty("职位id")
    private Integer postId;

    @ApiModelProperty("关键字词")
    private String keyword;

//    @JsonFormat( pattern="yyyy-MM-dd")
    private String beginTime;

//    @JsonFormat( pattern="yyyy-MM-dd")
    private String endTime;

    public Date getParticularYear() {
        return particularYear;
    }

    public void setParticularYear(Date particularYear) {
        this.particularYear = particularYear;
    }
    public void setParticularYear(Date particularYear,Integer quarter) {
        setQuarter(quarter,particularYear);
        this.particularYear = particularYear;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");

        if(quarter==1){
            String beginTime=sdf1.format(getParticularYear())+"-01-01";
            String endTime=sdf1.format(getParticularYear())+"-03-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        }
        if(quarter==2){
            String beginTime=sdf1.format(getParticularYear())+"-04-01";
            String endTime=sdf1.format(getParticularYear())+"-06-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        } if(quarter==3){
            String beginTime=sdf1.format(getParticularYear())+"-07-01";
            String endTime=sdf1.format(getParticularYear())+"-09-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        } if(quarter==4){
            String beginTime=sdf1.format(getParticularYear())+"-10-01";
            String endTime=sdf1.format(getParticularYear())+"-12-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        }
        this.quarter = quarter;
    }

    public void setQuarter(Integer quarter,Date year) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        if(quarter==1){
            String beginTime=sdf1.format(year)+"-01-01";
            String endTime=sdf1.format(year)+"-03-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        }
        if(quarter==2){
            String beginTime=sdf1.format(year)+"-04-01";
            String endTime=sdf1.format(year)+"-06-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        } if(quarter==3){
            String beginTime=sdf1.format(year)+"-07-01";
            String endTime=sdf1.format(year)+"-09-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        } if(quarter==4){
            String beginTime=sdf1.format(year)+"-10-01";
            String endTime=sdf1.format(year)+"-12-31";
            setBeginTime(beginTime);
            setEndTime(endTime);
        }
        this.quarter = quarter;
    }

}

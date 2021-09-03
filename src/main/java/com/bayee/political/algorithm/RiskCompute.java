package com.bayee.political.algorithm;

/**
 * @author xxl
 * @date 2021/8/31 11:09
 */
public class RiskCompute {

    /**
     * 警员风险数据标准化
     * @param minV
     * @param maxV
     * @return
     */
    public static double log10(double minV, double maxV) {
        return Math.log10(10) * minV / Math.log10(10) * maxV;
    }

    /**
     * 获取一组数中的最大值
     * @param v
     * @return
     */
    public static Integer max(Integer...v) {
        return numberCompare(v, CompareType.MAX).intValue();
    }

    /**
     * 获取一组数中的最小值
     * @param v
     * @return
     */
    public static Integer min(Integer...v) {
        return numberCompare(v, CompareType.MIN).intValue();
    }

    /**
     * 获取一组数中的最大值
     * @param v
     * @return
     */
    public static double max(Double...v) {
        return numberCompare(v, CompareType.MAX).doubleValue();
    }

    /**
     * 获取一组数中的最小值
     * @param v
     * @return
     */
    public static double min(Double...v) {
        return numberCompare(v, CompareType.MIN).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(min(112,2,34,412312,5));
    }

    /**
     * compare封装
     * @param v
     * @param compareType
     * @return
     */
    private static Number numberCompare(Number[] v, CompareType compareType) {
        Number value = v[0];
        int len = v.length;
        if (len < 2) {
            throw new RuntimeException("Lack of comparative significance");
        }

        if (CompareType.MAX.equals(compareType)) {
            for (int i=0; i<len; i++) {
                if (v[i].doubleValue() > value.doubleValue()) {
                    value = v[i];
                }
            }
        } else if (CompareType.MIN.equals(compareType)) {
            for (int i=0; i<len; i++) {
                if (v[i].doubleValue() < value.doubleValue()) {
                    value = v[i];
                }
            }
        }

        return value;
    }

    enum CompareType {
        MIN,
        MAX;
    }

}

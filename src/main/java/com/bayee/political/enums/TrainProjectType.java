package com.bayee.political.enums;

/**
 * @author xxl
 * @date 2021/4/17
 */
public enum TrainProjectType {
    /**
     * 体能项目类型
     */
    PRECISION_SHOOTING(1, "精度射击"),
    FAST_FIRE(2, "快速射击"),
    U_TARGET_SHOOTING(3, "U靶形射击"),
    RUN_50(4, "50米跑"),
    ABDOMINAL_CURL(5, "仰卧起坐"),
    PULL_UP(6, "引体向上"),
    PUSH_UP(7, "俯卧撑"),
    GRIP(8, "握力"),
    STANDING_LONG_JUMP(18, "立定跳远"),
    RUN_2000(19, "2000米跑"),
    SITTING_FORWARD(20, "坐立体前屈");

    private Integer id;
    private String desc;

    TrainProjectType(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Integer getId() {
        return this.id;
    }
}

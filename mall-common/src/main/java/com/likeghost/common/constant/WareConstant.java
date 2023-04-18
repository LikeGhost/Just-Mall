package com.likeghost.common.constant;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/16 1:23
 * @description
 */
public class WareConstant {

    public enum PurchaseDetailStatus {
        /**
         * 采购单状态
         */
//        BASE_ATTR(0, "基本属性"), SALE_ATTR(1, "销售属性");

        NEW(0, "新建"),
        ASSIGNED(1, "已分配"),
        PURCHASING(2, "正在采购"),
        COMPLETED(3, "完成"),
        FAILED(4, "采购失败");


        private int code;
        private String message;

        PurchaseDetailStatus(int code, String message) {
            this.code = code;
            this.message = message;
        }


        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static String getValueByCode(int code) {
            for (PurchaseDetailStatus value : PurchaseDetailStatus.values()) {
                if (value.getCode() == code) {
                    return value.getMessage();
                }
            }
            return null;
        }
    }


    public enum PurchaseStatus {
        /**
         * 采购单状态
         */
//        BASE_ATTR(0, "基本属性"), SALE_ATTR(1, "销售属性");

        NEW(0, "新建"),
        ASSIGNED(1, "已分配"),
        RECEIVED(2, "已领取"),
        COMPLETED(3, "已完成"),
        FAILED(4, "有异常");


        private int code;
        private String message;

        PurchaseStatus(int code, String message) {
            this.code = code;
            this.message = message;
        }


        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


        public static String getValueByCode(int code) {
            for (PurchaseStatus value : PurchaseStatus.values()) {
                if (value.getCode() == code) {
                    return value.getMessage();
                }
            }
            return null;
        }
    }
}

package com.likeghost.common.constant;

/**
 * @author LikeGhost
 * @version 1.0
 * @date 2023/4/12 16:55
 * @description
 */
public class ProductConstant {


    public enum AttrType {
        /**
         * 商品属性类型
         */
        BASE_ATTR(0, "基本属性"), SALE_ATTR(1, "销售属性");


        private int code;
        private String type;

        AttrType(int code, String type) {
            this.code = code;
            this.type = type;
        }


        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

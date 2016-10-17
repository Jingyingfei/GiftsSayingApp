package com.example.dllo.giftssayingapp.beanpackage;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 * http://api.liwushuo.com/v2/search/hot_words_v2
 */
public class EditBean {

    /**
     * code : 200
     * data : {"hot_words":[{"style":0,"word":"钢笔"},{"style":1,"word":"情侣"},{"style":0,"word":"手机壳"},{"style":0,"word":"双肩包"},{"style":0,"word":"手表"},{"style":0,"word":"杯子"},{"style":0,"word":"钱包"},{"style":0,"word":"宿舍"},{"style":0,"word":"手链"},{"style":0,"word":"项链"},{"style":0,"word":"耳机"},{"style":0,"word":"水杯"}],"placeholder":"选份走心好礼送给Ta"}
     * message : OK
     */

    private int code;
    /**
     * hot_words : [{"style":0,"word":"钢笔"},{"style":1,"word":"情侣"},{"style":0,"word":"手机壳"},{"style":0,"word":"双肩包"},{"style":0,"word":"手表"},{"style":0,"word":"杯子"},{"style":0,"word":"钱包"},{"style":0,"word":"宿舍"},{"style":0,"word":"手链"},{"style":0,"word":"项链"},{"style":0,"word":"耳机"},{"style":0,"word":"水杯"}]
     * placeholder : 选份走心好礼送给Ta
     */

    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private String placeholder;
        /**
         * style : 0
         * word : 钢笔
         */

        private List<HotWordsBean> hot_words;

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public List<HotWordsBean> getHot_words() {
            return hot_words;
        }

        public void setHot_words(List<HotWordsBean> hot_words) {
            this.hot_words = hot_words;
        }

        public static class HotWordsBean {
            private int style;
            private String word;

            public int getStyle() {
                return style;
            }

            public void setStyle(int style) {
                this.style = style;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }
    }
}

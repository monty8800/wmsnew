package com.rlzz.receivemanagement.entity;

import java.util.List;

/**
 * Created by lml on 2018/3/20.
 */

public class ReceiveBean {

    private List<BodyBean> body;

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * header : 任务1
         * content : [{"position":"1","tips":"25","title":"收货","flag":"ready_enter"},{"position":"1","tips":"25","title":"收货","flag":"ready_enter"}]
         */

        private String header;
        private List<ContentBean> content;

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * position : 1
             * tips : 25
             * title : 收货
             * flag : ready_enter
             */

            //位置
            private String position;
            //气泡数据值
            private String tips;
            //标题
            private String title;
            //启动新的插件的flag
            private String flag;
            //插件ACTIVITY路径
            private String activity;

            public String getActivity() {
                return activity;
            }

            public void setActivity(String activity) {
                this.activity = activity;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }
        }
    }
}

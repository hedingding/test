package com.test.headlistview;


import java.util.List;

public class AddBean {
    private int result;
    private String msg;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String Id;
        private String Name;
        private String Letter;
        private boolean isshow;

        public boolean isshow() {
            return isshow;
        }

        public void setIsshow(boolean isshow) {
            this.isshow = isshow;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getLetter() {
            return Letter;
        }

        public void setLetter(String Letter) {
            this.Letter = Letter;
        }
    }
}

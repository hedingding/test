package com.test.headlistview;


import java.util.List;

public class ShouYeInfoBean {
    private int status;
    private List<AdvBean> adv;
    private List<NewsBean> news;
    private List<TimeGoodsBean> time_goods;
    private List<HotGoodsBean> hot_goods;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AdvBean> getAdv() {
        return adv;
    }

    public void setAdv(List<AdvBean> adv) {
        this.adv = adv;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<TimeGoodsBean> getTime_goods() {
        return time_goods;
    }

    public void setTime_goods(List<TimeGoodsBean> time_goods) {
        this.time_goods = time_goods;
    }

    public List<HotGoodsBean> getHot_goods() {
        return hot_goods;
    }

    public void setHot_goods(List<HotGoodsBean> hot_goods) {
        this.hot_goods = hot_goods;
    }

    public static class AdvBean {

        private String id;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class NewsBean {
        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class TimeGoodsBean {
        private String img;
        private String name;
        private String sell_price;
        private String market_price;
        private String id;
        private String user_nums;

        public String getUser_nums() {
            return user_nums;
        }

        public void setUser_nums(String user_nums) {
            this.user_nums = user_nums;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSell_price() {
            return sell_price;
        }

        public void setSell_price(String sell_price) {
            this.sell_price = sell_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class HotGoodsBean {

        private String img;
        private String name;
        private String sell_price;
        private String market_price;
        private String id;
        private String user_nums;

        public String getUser_nums() {
            return user_nums;
        }

        public void setUser_nums(String user_nums) {
            this.user_nums = user_nums;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSell_price() {
            return sell_price;
        }

        public void setSell_price(String sell_price) {
            this.sell_price = sell_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
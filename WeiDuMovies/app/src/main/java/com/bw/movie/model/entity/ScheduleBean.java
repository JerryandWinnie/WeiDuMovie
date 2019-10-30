package com.bw.movie.model.entity;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/22 10:14
 * 排期bean
 */
public class ScheduleBean {

    /**
     * result : [{"beginTime":"20:00:00","endTime":"21:48:00","fare":0.25,"hallId":116,"id":516,"screeningHall":"杜比厅"},{"beginTime":"19:00:00","endTime":"20:53:00","fare":0.25,"hallId":115,"id":527,"screeningHall":"1号厅"},{"beginTime":"17:00:00","endTime":"23:55:00","fare":0.25,"hallId":116,"id":528,"screeningHall":"杜比厅"},{"beginTime":"22:00:00","endTime":"23:55:00","fare":0.25,"hallId":117,"id":529,"screeningHall":"2号激光厅"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * beginTime : 20:00:00
         * endTime : 21:48:00
         * fare : 0.25
         * hallId : 116
         * id : 516
         * screeningHall : 杜比厅
         */

        private String beginTime;
        private String endTime;
        private double fare;
        private int hallId;
        private int id;
        private String screeningHall;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public double getFare() {
            return fare;
        }

        public void setFare(double fare) {
            this.fare = fare;
        }

        public int getHallId() {
            return hallId;
        }

        public void setHallId(int hallId) {
            this.hallId = hallId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }
    }
}

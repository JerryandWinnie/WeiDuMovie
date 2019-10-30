package com.bw.movie.model.entity;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/17 16:17
 */
public class MovieInfoBean {


    /**
     * result : {"commentNum":31,"duration":"100分钟","imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)1.jpg","movieActor":[{"name":"古天乐","photo":"http://172.17.8.100/images/movie/actor/ftfb3/gutianle.jpg","role":"陆志廉"},{"name":"张智霖","photo":"http://172.17.8.100/images/movie/actor/ftfb3/zhangzhilin.jpg","role":"刘保强"},{"name":"郑嘉颖","photo":"http://172.17.8.100/images/movie/actor/ftfb3/zhengjiaying.jpg","role":"程德明"},{"name":"邓丽欣","photo":"http://172.17.8.100/images/movie/actor/ftfb3/denglixin.jpg","role":"吴颂华"},{"name":"\r\n谢天华","photo":"http://172.17.8.100/images/movie/actor/ftfb3/xietianhua.jpg","role":"狄伟杰"}],"movieDirector":[{"name":"林德禄","photo":"http://172.17.8.100/images/movie/director/ftfb3/1.jpg"}],"movieId":17,"movieType":"动作 / 剧情 / 犯罪","name":"反贪风暴3","placeOrigin":"中国大陆,中国香港","posterList":["http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)1.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)2.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)3.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)4.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)5.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)6.jpg"],"releaseTime":1537545600000,"score":9.1,"shortFilmList":[{"imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)2.jpg","videoUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)3.jpg","videoUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)4.jpg","videoUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)3.mp4"}],"summary":"ICAC (廉政公署) 陆志廉（古天乐 饰），JFIU (联合财富情报组) 刘保强（张智霖 饰）分别侦查贪污及洗黑钱案，但苦无线索，这时廉政公署L组 (内部纪律调查组) 程德明（郑嘉颖 饰）收到举报，指陆志廉收贿1200万，陆无法辩解实时停职。刘发现陆被诬陷，并跟一直调查的洗黑钱案有着千丝万缕关系，同时怀疑银行主任游子新（栢天男 饰）协助罪恶集团洗黑钱；陆冒险搜集罪证却遭禁锢，命悬一线.......","whetherFollow":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * commentNum : 31
         * duration : 100分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)1.jpg
         * movieActor : [{"name":"古天乐","photo":"http://172.17.8.100/images/movie/actor/ftfb3/gutianle.jpg","role":"陆志廉"},{"name":"张智霖","photo":"http://172.17.8.100/images/movie/actor/ftfb3/zhangzhilin.jpg","role":"刘保强"},{"name":"郑嘉颖","photo":"http://172.17.8.100/images/movie/actor/ftfb3/zhengjiaying.jpg","role":"程德明"},{"name":"邓丽欣","photo":"http://172.17.8.100/images/movie/actor/ftfb3/denglixin.jpg","role":"吴颂华"},{"name":"\r\n谢天华","photo":"http://172.17.8.100/images/movie/actor/ftfb3/xietianhua.jpg","role":"狄伟杰"}]
         * movieDirector : [{"name":"林德禄","photo":"http://172.17.8.100/images/movie/director/ftfb3/1.jpg"}]
         * movieId : 17
         * movieType : 动作 / 剧情 / 犯罪
         * name : 反贪风暴3
         * placeOrigin : 中国大陆,中国香港
         * posterList : ["http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)1.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)2.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)3.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)4.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)5.jpg","http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)6.jpg"]
         * releaseTime : 1537545600000
         * score : 9.1
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)2.jpg","videoUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)3.jpg","videoUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)4.jpg","videoUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)3.mp4"}]
         * summary : ICAC (廉政公署) 陆志廉（古天乐 饰），JFIU (联合财富情报组) 刘保强（张智霖 饰）分别侦查贪污及洗黑钱案，但苦无线索，这时廉政公署L组 (内部纪律调查组) 程德明（郑嘉颖 饰）收到举报，指陆志廉收贿1200万，陆无法辩解实时停职。刘发现陆被诬陷，并跟一直调查的洗黑钱案有着千丝万缕关系，同时怀疑银行主任游子新（栢天男 饰）协助罪恶集团洗黑钱；陆冒险搜集罪证却遭禁锢，命悬一线.......
         * whetherFollow : 2
         */

        private int commentNum;
        private String duration;
        private String imageUrl;
        private int movieId;
        private String movieType;
        private String name;
        private String placeOrigin;
        private long releaseTime;
        private double score;
        private String summary;
        private int whetherFollow;
        private List<MovieActorBean> movieActor;
        private List<MovieDirectorBean> movieDirector;
        private List<String> posterList;
        private List<ShortFilmListBean> shortFilmList;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceOrigin() {
            return placeOrigin;
        }

        public void setPlaceOrigin(String placeOrigin) {
            this.placeOrigin = placeOrigin;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public List<MovieActorBean> getMovieActor() {
            return movieActor;
        }

        public void setMovieActor(List<MovieActorBean> movieActor) {
            this.movieActor = movieActor;
        }

        public List<MovieDirectorBean> getMovieDirector() {
            return movieDirector;
        }

        public void setMovieDirector(List<MovieDirectorBean> movieDirector) {
            this.movieDirector = movieDirector;
        }

        public List<String> getPosterList() {
            return posterList;
        }

        public void setPosterList(List<String> posterList) {
            this.posterList = posterList;
        }

        public List<ShortFilmListBean> getShortFilmList() {
            return shortFilmList;
        }

        public void setShortFilmList(List<ShortFilmListBean> shortFilmList) {
            this.shortFilmList = shortFilmList;
        }

        public static class MovieActorBean {
            /**
             * name : 古天乐
             * photo : http://172.17.8.100/images/movie/actor/ftfb3/gutianle.jpg
             * role : 陆志廉
             */

            private String name;
            private String photo;
            private String role;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public static class MovieDirectorBean {
            /**
             * name : 林德禄
             * photo : http://172.17.8.100/images/movie/director/ftfb3/1.jpg
             */

            private String name;
            private String photo;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)2.jpg
             * videoUrl : http://172.17.8.100/video/movie/ftfb3/ftfb(3)1.mp4
             */

            private String imageUrl;
            private String videoUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}

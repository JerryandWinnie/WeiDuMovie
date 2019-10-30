package com.bw.movie.model.entity;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/21 15:23
 * //用来eventbus传值
 */
public class InfoBean {

    private String name;
    private String imageUrl;
    private String videoUrl;
    private int index;
    private int movieId;

    private InfoBean(){

    }

    public static InfoBean getInstance(){
        return new InfoBean();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}

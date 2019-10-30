package com.bw.movie.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/18 10:10
 */
@Entity
public class JsonBean {
    @Id
    private Long id;
    private String bannerJson;
    private String atHotJson;
    private String soonJson;
    private String hotJson;

    @Generated(hash = 1353727218)
    public JsonBean(Long id, String bannerJson, String atHotJson, String soonJson,
            String hotJson) {
        this.id = id;
        this.bannerJson = bannerJson;
        this.atHotJson = atHotJson;
        this.soonJson = soonJson;
        this.hotJson = hotJson;
    }
    @Generated(hash = 1926928967)
    public JsonBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBannerJson() {
        return this.bannerJson;
    }
    public void setBannerJson(String bannerJson) {
        this.bannerJson = bannerJson;
    }
    public String getAtHotJson() {
        return this.atHotJson;
    }
    public void setAtHotJson(String atHotJson) {
        this.atHotJson = atHotJson;
    }
    public String getSoonJson() {
        return this.soonJson;
    }
    public void setSoonJson(String soonJson) {
        this.soonJson = soonJson;
    }
    public String getHotJson() {
        return this.hotJson;
    }
    public void setHotJson(String hotJson) {
        this.hotJson = hotJson;
    }

}

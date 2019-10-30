package com.bw.movie.base;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/27 15:58
 */
public interface IBaseView {
    void onDataSuccess(Object obj);
    void onDataError(String msg);
}

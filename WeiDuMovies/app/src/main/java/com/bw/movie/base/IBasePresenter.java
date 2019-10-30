package com.bw.movie.base;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/27 15:59
 */
public class IBasePresenter<T extends IBaseView> {

    public T iBaseView;

    public IBasePresenter(T iBaseView) {
        this.iBaseView = iBaseView;
    }
}

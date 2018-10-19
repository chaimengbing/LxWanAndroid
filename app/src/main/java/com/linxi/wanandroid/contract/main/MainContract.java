package com.linxi.wanandroid.contract.main;

import com.linxi.wanandroid.base.presenter.AbstractPresenter;
import com.linxi.wanandroid.base.view.AbstractView;

public interface MainContract {

    interface  View extends AbstractView{
        void showSwitchProject();

        void showSwitchNavigation();

        void showAutoLoginView();
    }


    interface  Presenter extends AbstractPresenter<View>{

        void setCurrentPage(int page);


        void setNightModeState(boolean b);
    }
}

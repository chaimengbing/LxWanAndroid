package com.linxi.wanandroid.presenter.main;

import com.linxi.wanandroid.R;
import com.linxi.wanandroid.app.LxWanAndroidApplication;
import com.linxi.wanandroid.base.presenter.BasePresenter;
import com.linxi.wanandroid.component.RxBus;
import com.linxi.wanandroid.contract.main.MainContract;
import com.linxi.wanandroid.core.DataManager;
import com.linxi.wanandroid.core.event.AutoLoginEvent;
import com.linxi.wanandroid.core.event.LoginEvent;
import com.linxi.wanandroid.core.event.NightModeEvent;
import com.linxi.wanandroid.core.event.SwitchNavigationEvent;
import com.linxi.wanandroid.core.event.SwitchProjectEvent;
import com.linxi.wanandroid.utils.RxUtils;
import com.linxi.wanandroid.widget.BaseSubscribe;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
        .compose(RxUtils.rxFlSchedulerHelper())
        .map(NightModeEvent::isNightMode)
        .subscribeWith(new BaseSubscribe<Boolean>(mView,LxWanAndroidApplication.getInstance().getString(R.string.failed_to_cast_mode)){

            @Override
            public void onNext(Boolean aBoolean) {
                mView.useNightMode(aBoolean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                registerEvent();
            }
        }));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(logoutEvent -> mView.showLogoutView()));

        addSubscribe(RxBus.getDefault().toFlowable(AutoLoginEvent.class)
                .subscribe(autoLoginEvent -> mView.showAutoLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchProjectEvent.class)
                .subscribe(switchProjectEvent -> mView.showSwitchProject()));

        addSubscribe(RxBus.getDefault().toFlowable(SwitchNavigationEvent.class)
                .subscribe(switchNavigationEvent -> mView.showSwitchNavigation()));
    }

    @Override
    public void setCurrentPage(int page) {

    }

    @Override
    public void setNightModeState(boolean b) {

    }
}

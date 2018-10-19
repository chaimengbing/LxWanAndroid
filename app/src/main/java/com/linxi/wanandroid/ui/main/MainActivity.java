package com.linxi.wanandroid.ui.main;

import com.linxi.wanandroid.R;
import com.linxi.wanandroid.base.activity.BaseActivity;
import com.linxi.wanandroid.contract.main.MainContract;
import com.linxi.wanandroid.presenter.main.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchNavigation() {

    }

    @Override
    public void showAutoLoginView() {

    }
}

package com.linxi.wanandroid.ui.main;

import android.text.TextUtils;

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
        String res = "";
        String result = "00000000";
        if(result.endsWith("\n")){
            if (TextUtils.isEmpty(res)){
                //往外发result
            }else {
                //往外发res
            }
        }else {
            res +=result;
        }
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

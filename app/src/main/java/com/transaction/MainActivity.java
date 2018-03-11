package com.transaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.transaction.fragment.Fragment1;
import com.transaction.fragment.Fragment2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_add;
    private Button bt_remove;
    private Button bt_show;
    private Button bt_hide;
    private Button bt_replace;
    private FragmentManager fragmentManager;
    private Fragment2 fragment2;
    private Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        fragmentManager = getSupportFragmentManager();
        // 创建两个Fragment
        fragment2 = new Fragment2();
        fragment1 = new Fragment1();
        // 默认添加Fragment1
        addFragment(fragment1, "fragment1");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                addFragment(fragment2, "fragment2");
                break;
            case R.id.bt_remove:
                // 开启事务
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.remove(fragment2);
                beginTransaction.commit();

                break;
            case R.id.bt_replace:
                // 开始
                FragmentTransaction beginTransaction2 = fragmentManager.beginTransaction();
                // 执行替换
                beginTransaction2.replace(R.id.framelayout, fragment2, "fragment2");
                // 提交
                beginTransaction2.commit();
                break;
            case R.id.bt_show:
                FragmentTransaction beginTransaction3 = fragmentManager.beginTransaction();
                beginTransaction3.show(fragment2);
                beginTransaction3.commit();
                break;
            case R.id.bt_hide:
                FragmentTransaction beginTransaction4 = fragmentManager.beginTransaction();
                beginTransaction4.hide(fragment2);
                beginTransaction4.commit();
                break;

            default:
                break;
        }
    }

    private void addFragment(Fragment fragment, String tag) {
        // 往容器中默认的添加Fragment1
        // 开始事物
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        // 要添加到的容器
        beginTransaction.add(R.id.framelayout, fragment, tag);
        // 提交事务
        beginTransaction.commit();
    }

    private void initView() {
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_remove = (Button) findViewById(R.id.bt_remove);
        bt_replace = (Button) findViewById(R.id.bt_replace);
        bt_show = (Button) findViewById(R.id.bt_show);
        bt_hide = (Button) findViewById(R.id.bt_hide);

        // 对按钮设置点击事件
        bt_add.setOnClickListener(this);
        bt_remove.setOnClickListener(this);
        bt_replace.setOnClickListener(this);
        bt_show.setOnClickListener(this);
        bt_hide.setOnClickListener(this);
    }

    // 如果加载数据多,并且要求每次替换Fragment时,都刷新数据 ----replace
    // 如果不需要及时刷新,开发中可以用 show hide来控制展示的Fragment
}

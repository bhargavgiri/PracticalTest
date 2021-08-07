package com.example.androidpracticaltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;

import com.example.androidpracticaltest.D_Fragment.DashboardFragment;

public class MainActivity extends AppCompatActivity {
    DashboardFragment d;
   /* String abc="";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d=new DashboardFragment();
        select(d);
        /*abc=getIntent().getStringExtra("a");
        if(abc.equals(""))
        { select(d);

        }else {
            ExpanseModuleFragment e;
            e=new ExpanseModuleFragment();
            select(e);

        }*/
    }
   public void select(Fragment f)
    {
        FragmentTransaction f1=getSupportFragmentManager().beginTransaction();
        f1.replace(R.id.allfragment,f);
        f1.commit();
    }
}
package com.example.androidpracticaltest.D_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidpracticaltest.R;
import com.example.androidpracticaltest.RegistrationPage;


public class DashboardFragment extends Fragment implements View.OnClickListener {
    androidx.cardview.widget.CardView cvExpense;
    TextView tvLogout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dashboard,container,false);
        // Inflate the layout for this fragment
        cvExpense=view.findViewById(R.id.cvExpense);
        tvLogout=view.findViewById(R.id.tvLogout);

        cvExpense.setOnClickListener(this);
        tvLogout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvExpense:
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                ExpanseModuleFragment e=new ExpanseModuleFragment();
                FragmentTransaction f1=getActivity().getSupportFragmentManager().beginTransaction();
                f1.replace(R.id.allfragment,e);
                f1.commit();
                break;
            case R.id.tvLogout:
                startActivity(new Intent(getContext(), RegistrationPage.class));
                getActivity().finish();
                break;
        }
    }
}
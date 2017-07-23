package com.dwajot.androidtesttask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dwajot.androidtesttask.fragments.InfoFragment;
import com.dwajot.androidtesttask.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title));
        }
    }

    @Override
    public void onItemSelected(int position) {
        InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragment);
        if (infoFragment != null && infoFragment.isAdded()) {
            infoFragment.updateInfo(position);
        } else {
            InfoFragment newInfoFragment = new InfoFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.position), position);
            newInfoFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, newInfoFragment)
                    .addToBackStack(null).commit();
        }
    }
}

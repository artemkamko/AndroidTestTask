package com.dwajot.androidtesttask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dwajot.androidtesttask.fragments.InfoFragment;
import com.dwajot.androidtesttask.fragments.MainFragment;
import com.dwajot.androidtesttask.util.MySharedPreference;

public class MainActivity extends AppCompatActivity implements MainFragment.OnItemSelectedListener {
    private MySharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreference = new MySharedPreference(this);
        InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragment);
        if (infoFragment != null && infoFragment.isAdded()) {
            int position = sharedPreference.getPosition();
            infoFragment.updateInfo(position);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title));
        }
    }

    @Override
    public void onItemSelected(int position) {
        InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragment);
        if (infoFragment != null && infoFragment.isAdded()) {
            infoFragment.updateInfo(position);
        } else {
            Intent intent = new Intent(this, InfoActivity.class);
            intent.putExtra(getString(R.string.position), position);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
        sharedPreference.setPosition(position);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sharedPreference.setPosition(MySharedPreference.DEFAULT_POSITION);
    }
}

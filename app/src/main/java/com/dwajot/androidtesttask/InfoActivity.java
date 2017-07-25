package com.dwajot.androidtesttask;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dwajot.androidtesttask.fragments.InfoFragment;
import com.dwajot.androidtesttask.util.MySharedPreference;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title));
        }
        int position = getIntent().getIntExtra(getString(R.string.position), MySharedPreference.DEFAULT_POSITION);
        InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragment);
        infoFragment.updateInfo(position);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getBoolean(R.bool.isTab)) {
            int orientation = newConfig.orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT ||
                    orientation == Configuration.ORIENTATION_LANDSCAPE) {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        MySharedPreference sharedPreference = new MySharedPreference(getApplicationContext());
        sharedPreference.setPosition(MySharedPreference.DEFAULT_POSITION);
    }
}

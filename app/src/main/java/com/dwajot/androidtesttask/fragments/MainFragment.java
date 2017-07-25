package com.dwajot.androidtesttask.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dwajot.androidtesttask.R;
import com.dwajot.androidtesttask.adapter.MainAdapter;
import com.dwajot.androidtesttask.db.DBHelper;
import com.dwajot.androidtesttask.model.Info;
import com.dwajot.androidtesttask.service.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private MainAdapter adapter;
    private ProgressDialog progressDialog;
    private DBHelper dbHelper;
    private Realm realm;
    private OnItemSelectedListener mListener;

    @BindView(R.id.rvMainFrag)
    RecyclerView recyclerView;
    @BindView(R.id.swipeMainFrag)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tvErrorService)
    TextView tvErrorService;
    @BindView(R.id.btnRepeat)
    Button btnRepeat;


    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setRetainInstance(true);
        realm = Realm.getDefaultInstance();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration
                (recyclerView.getContext(), android.support.v7.widget.DividerItemDecoration.VERTICAL));
        dbHelper = new DBHelper(realm);
        if (!dbHelper.isDBEmpty()) {
            adapter = new MainAdapter(getContext(), dbHelper.getInfoObjects(), mListener);
            recyclerView.setAdapter(adapter);
        } else {
            backgroundService(true);
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                backgroundService(false);
            }
        });
        return view;
    }

    private void backgroundService(boolean showProgress) {
        Intent intent = new Intent(getActivity(), MyService.class);
        getActivity().startService(intent);
        if (showProgress) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDoneDataEvent(List<Info> list) {
        refreshLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        tvErrorService.setVisibility(View.GONE);
        btnRepeat.setVisibility(View.GONE);
        if (adapter == null) {
            adapter = new MainAdapter(getActivity().getBaseContext(), list, mListener);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration
                    (recyclerView.getContext(), android.support.v7.widget.DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateList(list);
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        refreshLayout.setRefreshing(false);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onServiceExceptionEvent(String s) {
        refreshLayout.setRefreshing(false);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (s.equals(getString(R.string.listIsEmpty))) {
            refreshLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            tvErrorService.setText(getString(R.string.emptyList));
            tvErrorService.setVisibility(View.VISIBLE);
        } else {
            refreshLayout.setVisibility(View.GONE);
            tvErrorService.setText(s);
            tvErrorService.setVisibility(View.VISIBLE);
            btnRepeat.setVisibility(View.VISIBLE);
            btnRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backgroundService(true);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(getString(R.string.refreshing), refreshLayout.isRefreshing());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            refreshLayout.setRefreshing(savedInstanceState.getBoolean(getString(R.string.refreshing)));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            mListener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        if (!realm.isClosed()) {
            realm.close();
        }
    }
}

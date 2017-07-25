package com.dwajot.androidtesttask.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dwajot.androidtesttask.R;
import com.dwajot.androidtesttask.db.DBHelper;
import com.dwajot.androidtesttask.model.Info;
import com.dwajot.androidtesttask.util.Converter;
import com.dwajot.androidtesttask.util.MySharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    @BindView(R.id.tvFrRoutId)
    TextView tvFrRoutId;
    @BindView(R.id.tvFrFromCity)
    TextView tvFrFromCity;
    @BindView(R.id.tvFrFromCityId)
    TextView tvFrFromCityId;
    @BindView(R.id.tvFrFromCityInfo)
    TextView tvFrFromCityInfo;
    @BindView(R.id.tvFrToCity)
    TextView tvFrToCity;
    @BindView(R.id.tvFrToCityId)
    TextView tvFrToCityId;
    @BindView(R.id.tvFrToCityInfo)
    TextView tvFrToCityInfo;
    @BindView(R.id.tvFrBusNumber)
    TextView tvFrBusNumber;
    @BindView(R.id.tvFrPrice)
    TextView tvFrPrice;
    @BindView(R.id.tvFrReservation)
    TextView tvFrReservation;
    @BindView(R.id.tvFrStations)
    TextView tvFrStations;
    @BindView(R.id.mainInfoLayout)
    LinearLayout mainInfoLayout;

    private DBHelper dbHelper;
    private Realm realm;

    public InfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        ButterKnife.bind(this, view);
        setRetainInstance(true);
        mainInfoLayout.setVisibility(View.INVISIBLE);
        realm = Realm.getDefaultInstance();
        dbHelper = new DBHelper(realm);
        Bundle bundle = getArguments();
        if (bundle != null) {
            updateInfo(bundle.getInt(getString(R.string.position)));
        }
        return view;
    }

    public void updateInfo(int position) {
        Converter converter = new Converter(getContext());
        Info info = dbHelper.getInfoObject(position);
        if (info != null && position != MySharedPreference.DEFAULT_POSITION) {
            tvFrRoutId.setText(info.getId());
            tvFrFromCity.setText(converter.makeTvFromCity(info.getFromDate(), info.getFromTime(), info.getFromCity().getName()));
            tvFrFromCityId.setText(converter.makeTvCityId(info.getFromCity().getId(), info.getFromCity().getHighLight()));
            tvFrFromCityInfo.setText(converter.makeTvCityInfo(info.getFromInfo()));
            tvFrToCity.setText(converter.makeTvToCity(info.getToDate(), info.getToTime(), info.getToCity().getName()));
            tvFrToCityId.setText(converter.makeTvCityId(info.getToCity().getId(), info.getToCity().getHighLight()));
            tvFrToCityInfo.setText(converter.makeTvCityInfo(info.getToInfo()));
            tvFrBusNumber.setText(info.getBusId());
            tvFrPrice.setText(info.getPrice());
            tvFrReservation.setText(info.getReservation());
            tvFrStations.setText(converter.makeTvStations(info.getInfo()));
            mainInfoLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!realm.isClosed()) {
            realm.close();
        }
    }
}

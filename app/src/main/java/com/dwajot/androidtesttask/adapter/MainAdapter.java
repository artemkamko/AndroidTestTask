package com.dwajot.androidtesttask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dwajot.androidtesttask.R;
import com.dwajot.androidtesttask.fragments.MainFragment;
import com.dwajot.androidtesttask.model.Info;
import com.dwajot.androidtesttask.util.Converter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Artem on 21.07.2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VHolder> {
    private List<Info> infoList;
    private LayoutInflater layoutInflater;
    private MainFragment.OnItemSelectedListener itemSelectedListener;
    private Context context;

    public MainAdapter(Context context, List<Info> infoList, MainFragment.OnItemSelectedListener itemSelectedListener) {
        this.infoList = infoList;
        this.itemSelectedListener = itemSelectedListener;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(List<Info> list) {
        this.infoList = list;
        notifyDataSetChanged();
    }

    @Override
    public VHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.main_fragment_items, parent, false);
        return new VHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final VHolder holder, int position) {
        holder.setIdAndCity(position + 1, infoList.get(position).getFromCity().getName(),
                infoList.get(position).getToCity().getName());
        holder.setFromTime(infoList.get(position).getFromDate(), infoList.get(position).getFromTime());
        holder.setFromCityInfo(infoList.get(position).getFromInfo());
        holder.setToTime(infoList.get(position).getToDate(), infoList.get(position).getToTime());
        holder.setToCityInfo(infoList.get(position).getToInfo());
        holder.mainFragmentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.onItemSelected(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public static class VHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemIdAndCity)
        TextView tvItemIdAndCity;
        @BindView(R.id.tvItemFromTime)
        TextView tvItemFromTime;
        @BindView(R.id.tvItemFromCityInfo)
        TextView tvItemFromCityInfo;
        @BindView(R.id.tvItemToTime)
        TextView tvItemToTime;
        @BindView(R.id.tvItemToCityInfo)
        TextView tvItemToCityInfo;
        @BindView(R.id.mainFragmentItem)
        LinearLayout mainFragmentItem;

        private Converter converter;

        VHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            converter = new Converter(context);
        }

        public void setIdAndCity(int id, String fromCity, String toCity) {
            tvItemIdAndCity.setText(converter.makeTvItemIdAndCity(id, fromCity, toCity));
        }

        public void setFromTime(String fromDate, String fromTime) {
            tvItemFromTime.setText(converter.makeTvTimeDispatch(fromDate, fromTime));
        }

        public void setFromCityInfo(String info) {
            tvItemFromCityInfo.setText(converter.makeTvCityInfo(info));
        }

        public void setToTime(String toDate, String toTime) {
            tvItemToTime.setText(converter.makeTvTimeArrival(toDate, toTime));
        }

        public void setToCityInfo(String info) {
            tvItemToCityInfo.setText(converter.makeTvCityInfo(info));
        }
    }
}

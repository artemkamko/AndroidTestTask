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
        String fromCity = infoList.get(position).getFromCity().getName();
        String toCity = infoList.get(position).getToCity().getName();
        String date = infoList.get(position).getFromDate();
        String time = infoList.get(position).getFromTime();
        String price = infoList.get(position).getPrice();

        holder.setTvFromCity(fromCity);
        holder.setTvToCity(toCity);
        holder.setTvDate(date);
        holder.setTvTime(time);
        holder.setTvPrice(price);
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
        @BindView(R.id.tvItemFromCity)
        TextView tvItemFromCity;
        @BindView(R.id.tvItemToCity)
        TextView tvItemToCity;
        @BindView(R.id.tvItemDate)
        TextView tvItemDate;
        @BindView(R.id.tvItemTime)
        TextView tvItemTime;
        @BindView(R.id.tvItemPrice)
        TextView tvItemPrice;
        @BindView(R.id.mainFragmentItem)
        LinearLayout mainFragmentItem;

        private Converter converter;

        VHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            converter = new Converter(context);
        }

        public void setTvFromCity(String fromCity) {
            tvItemFromCity.setText(fromCity);
        }

        public void setTvToCity(String toCity) {
            tvItemToCity.setText(toCity);
        }

        public void setTvDate(String date) {
            tvItemDate.setText(converter.makeTvDate(date));
        }

        public void setTvTime(String time) {
            tvItemTime.setText(converter.makeTvTime(time));
        }

        public void setTvPrice(String price) {
            tvItemPrice.setText(price);
        }
    }
}

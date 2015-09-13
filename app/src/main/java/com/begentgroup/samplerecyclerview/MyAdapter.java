package com.begentgroup.samplerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-09-12.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public interface OnItemClickListener {
        public void onItemClicked(ViewHolder holder, View view, ItemData data, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        ItemData mData;
        int[] sizeIds = {R.dimen.item_text_size_0, R.dimen.item_text_size_1, R.dimen.item_text_size_2, R.dimen.item_text_size_3, R.dimen.item_text_size_4};


        OnItemClickListener mItemClickListener;

        public void setOnItemClickListener(OnItemClickListener listener) {
            mItemClickListener = listener;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.text_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClicked(ViewHolder.this, v, mData, position);
                        }
                    }
                }
            });
        }

        public void setItemData(ItemData data) {
            mData = data;
            titleView.setText(data.title);
        }

        public void setTextSize(int position) {
            int id = sizeIds[position % sizeIds.length];
            float size = titleView.getContext().getResources().getDimensionPixelSize(id);
            titleView.setTextSize(size);
        }
    }

    List<ItemData> items = new ArrayList<ItemData>();
    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void add(ItemData item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setItemData(items.get(i));
        viewHolder.setTextSize(i);
        viewHolder.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

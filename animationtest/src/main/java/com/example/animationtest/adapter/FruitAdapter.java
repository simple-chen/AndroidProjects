package com.example.animationtest.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animationtest.R;

import java.util.List;

/**
 * @author by chenlp
 * @date 2020/10/22
 * @describe
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<String> mFruitList;
    private int selectedPosition = 0;
    private OnItemClickListener mOnItemClickListener;

    public FruitAdapter() {
    }

    public void addFruitAdapter(List<String> fruitList) {
        mFruitList = fruitList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textView.setText(mFruitList.get(position));
        holder.textView.setTextColor(position == selectedPosition?Color.RED:Color.BLACK);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.itemClick(selectedPosition);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ConstraintLayout mSelected_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            mSelected_item = itemView.findViewById(R.id.selected_item);
        }
    }


    public interface OnItemClickListener{
        void itemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}

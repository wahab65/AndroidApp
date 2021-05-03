package com.example.deelio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deelio.Model.Deal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHolder>{

    List<Deal> deals;
    Context context;
    private FirebaseUser firebaseUser;

    public DealAdapter(List<Deal> deals, FirebaseUser firebaseUser) {
        this.deals = deals;
        this.firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View dealView = inflater.inflate(R.layout.deal_rv_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(dealView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Deal deal = deals.get(i);

        viewHolder.tvTitle.setText(deal.getTitle());
        viewHolder.tvBeforePrice.setText(deal.getBeforePrice());
        viewHolder.tvAfterPrice.setText(deal.getAfterPrice());
        viewHolder.tvStoreName.setText(deal.getStoreName());
        viewHolder.tvLikeCount.setText(deal.getLikeCount());
        viewHolder.tvCommentCount.setText(deal.getCommentCount());
        viewHolder.tvDetails.setText(deal.getDetails());

        Glide.with(context)
                .load(deal.getDealImage())
                .into(viewHolder.ivDealImage);


    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivDealImage;
        public TextView tvTitle;
        public TextView tvBeforePrice;
        public TextView tvAfterPrice;
        public TextView tvStoreName;
        public TextView tvLikeCount;
        public TextView tvCommentCount;
        public TextView tvDetails;

        public ViewHolder(View view) {
            super(view);

            ivDealImage= (ImageView) view.findViewById(R.id.ivDealImage);
            tvTitle= (TextView) view.findViewById(R.id.tvTitle);
            tvBeforePrice= (TextView) view.findViewById(R.id.tv_BeforePrice);
            tvAfterPrice= (TextView) view.findViewById(R.id.tv_AfterPrice);
            tvStoreName= (TextView) view.findViewById(R.id.tv_StoreName);
            tvLikeCount= (TextView) view.findViewById(R.id.tvLikeCount);
            tvCommentCount= (TextView) view.findViewById(R.id.tvCommentCount);
            tvDetails= (TextView) view.findViewById(R.id.tvDetails);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Deal deal = deals.get(position);
            Intent intent = new Intent(context, DealDetailsActivity.class);
            intent.putExtra(Deal.class.getSimpleName(), (Serializable) deal);
            context.startActivity(intent);
        }

    }

    public void reset() {
        deals.clear();
        notifyDataSetChanged();
    }

}

package com.dreams.theroommateagreement;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 3/25/18.
 */

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.AdViewHolder>{

    private Context mContext;
    private AdAdapterListener listener;

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView advertiser;
        TextView personAddress;
        ImageView personPhoto;
        public ImageView iconImp;

        AdViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.roommate_ad_card_view);
            advertiser = (TextView)itemView.findViewById(R.id.person_name);
            personAddress = (TextView)itemView.findViewById(R.id.ad_address);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            iconImp = (ImageView) itemView.findViewById(R.id.icon_star);
        }
    }

    private List<RoommateAdvertisement> ads;

    AdAdapter(List<RoommateAdvertisement> advertisements){
        this.ads = advertisements;
    }

    AdAdapter(Context context,
              List<RoommateAdvertisement> advertisements,
              AdAdapterListener listener){
        this.ads = advertisements;
        this.listener = listener;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.roommate_ad, viewGroup, false);
        AdViewHolder avh = new AdViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(AdViewHolder personViewHolder, int i) {
        personViewHolder.advertiser.setText(ads.get(i).getName());
        personViewHolder.personAddress.setText(ads.get(i).getAddress());

        applyImportant(personViewHolder, ads.get(i));
        applyClickEvents(personViewHolder, i);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface AdAdapterListener {
//        void onIconClicked(int position);

        void onIconImportantClicked(int position);

//        void onMessageRowClicked(int position);

        void onRowLongClicked(int position);
    }


    ////////////// PRIVATE METHODS //////////////////

    private void applyClickEvents(AdViewHolder holder, final int position) {
        holder.iconImp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onIconImportantClicked(position);
            }
        });
    }

    private void applyImportant(AdViewHolder holder, RoommateAdvertisement message) {
        if (message.isImportant()) {
            holder.iconImp.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher_foreground));
            holder.iconImp.setColorFilter(ContextCompat.getColor(mContext, R.color.icon_tint_selected));
        } else {
            holder.iconImp.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher_foreground));
            holder.iconImp.setColorFilter(ContextCompat.getColor(mContext, R.color.icon_tint_normal));
        }
    }

}

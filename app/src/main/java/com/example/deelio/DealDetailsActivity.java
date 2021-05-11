package com.example.deelio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.deelio.Model.Deal;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class DealDetailsActivity extends AppCompatActivity {
    Context ctx;
    private String dealId;
    private Deal deal;
    CarouselView carouselView;
    TextView tvDealTitle;
    TextView tv_BeforePrice2;
    TextView tv_AfterPrice2;
    TextView tvDetails2;
    TextView tv_StoreName2;
    Button UrlButton;

    String[] dealImages = {"https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401758_sd.jpg;maxHeight=640;maxWidth=550",
            "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401758_sd.jpg;maxHeight=640;maxWidth=550",
            "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401758_sd.jpg;maxHeight=640;maxWidth=550"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_details);
        ctx=getApplicationContext();

        carouselView= findViewById(R.id.carouselView);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(3);

        tvDealTitle = findViewById(R.id.tvDealTitle);
        tv_BeforePrice2= findViewById(R.id.tv_BeforePrice2);
        tv_AfterPrice2=  findViewById(R.id.tv_AfterPrice2);
        tvDetails2=  findViewById(R.id.tvDetails2);
        tv_StoreName2=  findViewById(R.id.tv_StoreName2);
        UrlButton = findViewById(R.id.UrlButton);

        deal = getIntent().getParcelableExtra((Deal.class.getSimpleName()));

        tvDealTitle.setText(deal.getTitle());
        tv_BeforePrice2.setText(deal.getBeforePrice());
        tv_AfterPrice2.setText(deal.getAfterPrice());
        tvDetails2.setText(deal.getDetails());
        tv_StoreName2.setText(deal.getStoreName());

        dealImages[0]= deal.getDealImage();
        dealImages[1]= deal.getDealImage();
        dealImages[2]= deal.getDealImage();

        UrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlToVisit= deal.getDealURL();
                openWebURL(urlToVisit);
            }
        });


    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {



//            Glide.with(ctx)
//                    .load(dealImages[position])  // TO-DO:    change this to dealImages array
//                    .override(carouselView.getWidth(), Target.SIZE_ORIGINAL)
//                    .into(imageView);
            
            imageView.setAdjustViewBounds(true);
            Glide.with(ctx)
                    .load(dealImages[position])  // TO-DO:    change this to dealImages array
                    .override(carouselView.getWidth(), Target.SIZE_ORIGINAL)
                    .load(dealImages[position])
                    .apply(RequestOptions.fitCenterTransform())
                    .into(imageView);

        }
    };
    public void openWebURL( String inURL ) {
        Intent i = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );
        startActivity( i );
    }

}
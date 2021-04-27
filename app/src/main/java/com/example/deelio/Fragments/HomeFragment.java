package com.example.deelio.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deelio.LoginActivity;
import com.example.deelio.MainActivity;
import com.example.deelio.Model.Deal;
import com.example.deelio.RecyclerViewAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.example.deelio.R;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Context ctx;
    //private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerViewAdapter rvAdapter;
    ArrayList<Deal> deals = new ArrayList<>();;
    RecyclerView rvDeals;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment








        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //initial test for userAuth by making a toast to get user instance
        // ===== start deleting from here...
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email =null;
                if (user != null)  email = user.getEmail();
                Toast.makeText(getActivity(),"Logged in as: "+email,Toast.LENGTH_SHORT).show();
        // ===== until this line ////

        RecyclerView rvDeals = view.findViewById(R.id.rvTopDeals);

        //construct adapter
        rvAdapter = new RecyclerViewAdapter(deals);

        loadMockDeals();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvDeals.setLayoutManager(linearLayoutManager);
        //implement scroll listener
        rvDeals.addOnScrollListener(new RecyclerView.OnScrollListener() {
        });
        rvDeals.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
        rvDeals.scrollToPosition(0);


    }

    private void loadMockDeals() {

        // Test starts here: to get image access from firebase
        /*
        String[] tempFileLocation = {""};
        StorageReference storageReference;
        storageReference= FirebaseStorage.getInstance().getReference().child("/5947110_sd.jpg"); // the name of the image in the database
        try {
            final File localImageTemp= File.createTempFile("pic1","jpg"); //a temp image placed on local machine named "pic1.jpg"
            storageReference.getFile(localImageTemp).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), " image was retrieved successfully " , Toast.LENGTH_SHORT).show();
                    Bitmap bitmapImage  = BitmapFactory.decodeFile(localImageTemp.getAbsolutePath());
                    tempFileLocation[0] = localImageTemp.getPath();
                    Toast.makeText(getContext(), " localImageTemp.getPath(): "+localImageTemp.getPath() , Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), " localImageTemp.getAbsolutePath(): "+localImageTemp.getAbsolutePath() , Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Retrieval","Image was not retrieved: Exception in loadMockDeals()");
        }

         */

        final Deal firstMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401758_sd.jpg;maxHeight=640;maxWidth=550", "Samsung Tv on Sale", "$600", "$500", "Costco", "12", "2", "Mock Details") ;
        final Deal secondMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401851_sd.jpg;maxHeight=300;maxWidth=450", "LGTV 4k OLED on sale", "$900", "$800", "Bestbuy", "9", "12", "Mock Details") ;
        final Deal thirdMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6084/6084400_sd.jpg;maxHeight=640;maxWidth=550", "Apple - AirPods with Charging Case on sale", "$159.99", "$129.99", "Bestbuy", "90", "54", "The new AirPods combine intelligent design with breakthrough technology and crystal-clear sound. Powered by the new Apple H1 headphone chip, AirPods now feature hands-free access to Siri using just your voice. And up to 3 hours of talk time on a single charge.") ;
        final Deal fourthMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/Gallery/pol-mmt399145-200412_DER-213917.jpg;maxHeight=476;maxWidth=794", "Save $100 on Cam-Kit", "$379.99", "$279.99", "Bestbuy", "76", "14", "Blink - Outdoor 5 Cam Kit– wireless, weather-resistant HD security camera with 2-year battery life and motion detection") ;
        final Deal fifthMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6709/6709101_rd.jpg;maxHeight=640;maxWidth=550", "Cuisinart - 12 PC Knife Set", "$49.99", "$12.99", "Bestbuy", "4", "2", "Add to cart to see the deal! ") ;


        deals.add(firstMockDeal);
        deals.add(secondMockDeal);
        deals.add(thirdMockDeal);
        deals.add(fourthMockDeal);
        deals.add(fifthMockDeal);

        deals.add(firstMockDeal);
        deals.add(secondMockDeal);
        deals.add(thirdMockDeal);
        deals.add(fourthMockDeal);
        deals.add(fifthMockDeal);

        //Toast.makeText(getContext(), deals.size() + " is the deals size " , Toast.LENGTH_SHORT).show();
        rvAdapter.notifyDataSetChanged();
        }

}
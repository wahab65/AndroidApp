package com.example.deelio.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deelio.LoginActivity;
import com.example.deelio.MainActivity;
import com.example.deelio.Model.Deal;
import com.example.deelio.RecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.deelio.R;

import org.w3c.dom.Text;

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

        final Deal firstMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6395/6395125_sd.jpg;maxHeight=300;maxWidth=300", "Samsung Tv on Sale", "$600", "$500", "Costco", "12", "2", "Mock Details") ;
        final Deal secondMockDeal = new Deal("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6401/6401851_sd.jpg;maxHeight=300;maxWidth=450", "LGTV 4k OLED on sale", "$900", "$800", "Bestbuy", "9", "12", "Mock Details") ;
        deals.add(firstMockDeal);
        deals.add(secondMockDeal);
        deals.add(firstMockDeal);
        deals.add(secondMockDeal);
        deals.add(firstMockDeal);
        deals.add(secondMockDeal);
        Toast.makeText(getContext(), deals.size() + " is the deals size " , Toast.LENGTH_SHORT).show();
        rvAdapter.notifyDataSetChanged();
        }

}
package com.example.deelio.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deelio.LoginActivity;
import com.example.deelio.Model.User;
import com.example.deelio.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnLogout;
    private FirebaseUser fUser;
    FirebaseAuth firebaseAuth;

    ImageView ivUserImage;
    TextView tvUserLevel;
    TextView tvPoints;
    TextView tvUserName;
    TextView tvUserName2;
    TextView tvEmail;
    TextView tvEmail2;


    String profileId = "";

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        fUser = FirebaseAuth.getInstance().getCurrentUser();

        String data = getContext().getSharedPreferences("PROFILE", Context.MODE_PRIVATE).getString("profileId", "none");

        if (data.equals("none")) {
            profileId = fUser.getUid();
        } else {
            profileId = data;
            getContext().getSharedPreferences("PROFILE", Context.MODE_PRIVATE).edit().clear().apply();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        ivUserImage = view.findViewById(R.id.ivUserImage2) ;
        tvUserLevel = view.findViewById(R.id.tvUserLevel);
        tvPoints = view.findViewById(R.id.tvPoints);
        tvUserName= view.findViewById(R.id.tvUserName) ;
        tvEmail= view.findViewById(R.id.tvEmail) ;
        tvUserName2= view.findViewById(R.id.tvUserName2) ;
        tvEmail2= view.findViewById(R.id.tvEmail2) ;


        userInfo();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnLogout= view.findViewById(R.id.btnLogout2);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        final Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void userInfo() {

        FirebaseDatabase.getInstance().getReference().child("Users").child(profileId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

//                Toast.makeText(getActivity(), "profileId: "+ profileId, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), "user was :" +user.getUsername(), Toast.LENGTH_SHORT).show();
                if (user != null) {
                    tvUserName.setText(user.getUsername());
                    tvEmail.setText(user.getEmail());
                    tvUserLevel.setText(user.getUserlevel());
                    tvPoints.setText(user.getPoints());
                    tvEmail2.setText(user.getEmail());
                    tvUserName2.setText(user.getUsername());
                } else {
                    Toast.makeText(getActivity(), "user was null!!", Toast.LENGTH_SHORT).show();
                }

               // Glide.with(getView()).load(user.getImageurl()).into(ivUserImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
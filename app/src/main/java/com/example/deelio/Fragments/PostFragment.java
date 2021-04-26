package com.example.deelio.Fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.deelio.Model.Deals;
import com.example.deelio.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;


public class PostFragment extends Fragment {

    public static final String TAG = "Post_Fragments";
    private EditText dealName, dealURL, dealPrice, brandName, dealDescription;
    private ImageView uploadImage;
    private Button uploadButton;
    private ProgressBar progressBar2;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private FirebaseFirestore fstore = FirebaseFirestore.getInstance();
    private Uri imageUri;


    public PostFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dealName = view.findViewById(R.id.dealName);
        dealURL = view.findViewById(R.id.dealURL);
        dealPrice = view.findViewById(R.id.dealPrice);
        brandName = view.findViewById(R.id.brandName);
        dealDescription = view.findViewById(R.id.dealDescription);
        uploadImage = view.findViewById(R.id.uploadImage);
        uploadButton = view.findViewById(R.id.uploadButton);
        progressBar2 = view.findViewById(R.id.progressBar2);

        progressBar2.setVisibility(View.INVISIBLE);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleyIntent = new Intent();
                galleyIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleyIntent.setType("image/*");
                startActivityForResult(galleyIntent, 2);
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri != null){
                    uploadtoFirebase(imageUri);
                }else{
                    Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void uploadtoFirebase(Uri uri) {
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileEXtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                    progressBar2.setVisibility(View.INVISIBLE);
                    uploadImage.setImageResource(R.drawable.image_upload);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar2.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar2.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Upload Failure!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getFileEXtension(Uri uri) {
        ContentResolver cr = getActivity().getApplicationContext().getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data!= null){
            imageUri = data.getData();
            uploadImage.setImageURI(imageUri);


        }


    }



}


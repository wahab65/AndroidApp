package com.example.deelio.Fragments;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.deelio.MainActivity;
import com.example.deelio.Model.Deals;
import com.example.deelio.R;
import com.example.deelio.RegisterActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.collection.*;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class PostFragment extends Fragment {

    public static final String TAG = "Post_Fragments";
    private TextView dealName, dealURL, dealPrice, originalPrice, brandName, dealDescription;

    private Uri imageUri;
    private String imageUrl;

    private ImageView uploadImage;
    private Button uploadButton;
    private ProgressBar progressBar2;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private FirebaseFirestore fstore = FirebaseFirestore.getInstance();



    public PostFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }


    //TODO:  make a field to allow getting original price
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dealName = view.findViewById(R.id.dealName);
        dealURL = view.findViewById(R.id.dealURL);
        dealPrice = view.findViewById(R.id.dealPrice);
        originalPrice = view.findViewById(R.id.dealPrice); //wrong TO-DO: make a field to allow getting original price
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

    /*
    //Previous Upload Code:
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
    */

    //New Upload Code:
    private void uploadtoFirebase(Uri imageUri){


        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Submitting Your Deal Now!");
        pd.show();


        if (imageUri != null){
            final StorageReference filePath = FirebaseStorage.getInstance().getReference("Deals").child(System.currentTimeMillis() + "." + MimeTypeMap.getFileExtensionFromUrl(imageUri.toString()));


            StorageTask uploadtask = filePath.putFile(imageUri);
            uploadtask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }

                    return filePath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri downloadUri = task.getResult();
                    imageUrl = downloadUri.toString();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Deals");
                    String dealId = ref.push().getKey();

                    HashMap<String , Object> map = new HashMap<>();
                    map.put("DealId" , dealId);
                    map.put("Title" , dealName.getText().toString());
                    map.put("DealImage" , imageUrl);
                    map.put("DealURL" , dealURL.getText().toString());
                    map.put("AfterPrice" , dealPrice.getText().toString());
                    map.put("BeforePrice" , originalPrice.getText().toString());
                    map.put("StoreName" , brandName.getText().toString());
                    map.put("Details" , dealDescription.getText().toString());
                    map.put("UserReference" , FirebaseAuth.getInstance().getCurrentUser().getUid());


                    ref.child(dealId).setValue(map);

                    pd.dismiss();


                    final Intent intent = new Intent(getActivity(),MainActivity.class );
                    startActivity(intent);
                    getActivity().finish();
                }}).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this.getContext(), "No image was selected!", Toast.LENGTH_SHORT).show();
        }
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


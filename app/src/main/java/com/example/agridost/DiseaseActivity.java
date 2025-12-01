package com.example.agridost;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.agridost.models.DiseaseResponse;
import com.example.agridost.network.ApiService;
import com.example.agridost.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiseaseActivity extends AppCompatActivity {

    private ImageView ivPreview, btnBack;
    private Button btnCapture, btnUpload;
    private CardView cardResult;
    private TextView tvDiseaseName, tvConfidence, tvTreatment;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int PERMISSION_CAMERA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        ivPreview = findViewById(R.id.ivPreview);
        btnBack = findViewById(R.id.btnBackDisease);
        btnCapture = findViewById(R.id.btnCapture);
        btnUpload = findViewById(R.id.btnUpload);
        cardResult = findViewById(R.id.cardResult);
        tvDiseaseName = findViewById(R.id.tvDiseaseName);
        tvConfidence = findViewById(R.id.tvConfidence);
        tvTreatment = findViewById(R.id.tvTreatment);

        btnBack.setOnClickListener(v -> finish());

        btnCapture.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
            } else {
                dispatchTakePictureIntent();
            }
        });

        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ivPreview.setImageBitmap(imageBitmap);
                analyzeImage();
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                ivPreview.setImageURI(data.getData());
                analyzeImage();
            }
        }
    }

    private void analyzeImage() {
        Toast.makeText(this, "Analyzing Crop...", Toast.LENGTH_SHORT).show();
        
        // Mock API Call - In real app, we would upload the image file
        ApiService apiService = RetrofitClient.getApiService();
        Call<DiseaseResponse> call = apiService.predictDisease();

        call.enqueue(new Callback<DiseaseResponse>() {
            @Override
            public void onResponse(Call<DiseaseResponse> call, Response<DiseaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    showResult(response.body());
                } else {
                    Toast.makeText(DiseaseActivity.this, "Analysis Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DiseaseResponse> call, Throwable t) {
                Toast.makeText(DiseaseActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResult(DiseaseResponse result) {
        cardResult.setVisibility(View.VISIBLE);
        tvDiseaseName.setText("Disease: " + result.getDisease());
        tvConfidence.setText("Confidence: " + result.getConfidence() + "%");
        tvTreatment.setText("Treatment: " + result.getTreatment());
    }
}

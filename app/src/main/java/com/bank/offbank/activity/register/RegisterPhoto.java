package com.bank.offbank.activity.register;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bank.offbank.R;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterPhoto extends AppCompatActivity implements Structure {
    private static final int CAMERA_PERMISSION_CODE = 1001;
    private ClienteModel client;
    private PreviewView previewPhoto;
    private ImageView photoPreview, frame;
    private ExecutorService executorService;
    private Button buttonNext;
    private FaceDetector faceDetector;
    private boolean isPhotoTaken = false;


    private ClienteModel getDateIntent() {
        Intent getDate = getIntent();
        return (ClienteModel) getDate.getSerializableExtra("cliente");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_photo);
        this.client = getDateIntent();
        initializeUI();
        setupListeners();
        checkPermission();
        executorService = Executors.newSingleThreadExecutor();
        FaceDetectorOptions options = new FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
                .build();
        faceDetector = FaceDetection.getClient(options);
    }


    @Override
    public void initializeUI() {
        previewPhoto = findViewById(R.id.previewPhoto);
        photoPreview = findViewById(R.id.photoPreview);
        frame = findViewById(R.id.frameOverlay);
        buttonNext = findViewById(R.id.register_photo_next);

        photoPreview.setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);
    }

    @Override
    public void setupListeners() {
        buttonNext.setOnClickListener(view -> {

        });
    }

    @Override
    public void disableButton() {
    }

    @Override
    public void enableButton() {
    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        } else {
            startCamera();
        }
    }

    @OptIn(markerClass = ExperimentalGetImage.class)
    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                        .build();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewPhoto.getSurfaceProvider());
                ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

                imageAnalysis.setAnalyzer(executorService, image -> {
                    try {
                        InputImage inputImage = InputImage.fromMediaImage(image.getImage(), image.getImageInfo().getRotationDegrees());
                        faceDetector.process(inputImage)
                                .addOnSuccessListener(faces -> {
                                    for (Face face : faces) {
                                        if (isFaceAligned(face)) {
                                            if (!isPhotoTaken) {
                                                isPhotoTaken = true;
                                                // Delay de 1 segundo antes de capturar a foto
                                                new Handler().postDelayed(() -> runOnUiThread(this::takePhoto), 1000);
                                            }
                                        }
                                    }
                                })
                                .addOnFailureListener(e -> Log.e("RegisterPhoto", "Erro ao detectar rosto", e))
                                .addOnCompleteListener(task -> image.close());
                    } catch (Exception e) {
                        image.close();
                        Log.e("RegisterPhoto", "Erro ao analisar imagem", e);
                    }
                });
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
                Log.d("RegisterPhoto", "Câmera inicializada com sucesso");
            } catch (Exception e) {
                Log.e("RegisterPhoto", "Erro ao iniciar a câmera", e);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private boolean isFaceAligned(Face face) {
        float headEulerAngleY = face.getHeadEulerAngleY();
        float headEulerAngleZ = face.getHeadEulerAngleZ();
        return Math.abs(headEulerAngleY) < 10 && Math.abs(headEulerAngleZ) < 10;
    }

    private void takePhoto() {
        File photoFile = new File(getExternalFilesDir(null), "photo.jpg");
        Bitmap bitmap = previewPhoto.getBitmap();
        if (bitmap != null) {
            runOnUiThread(() -> {
                previewPhoto.setVisibility(View.GONE);
                frame.setVisibility(View.GONE);
                photoPreview.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.VISIBLE);
                photoPreview.setImageBitmap(bitmap);
            });
            Log.d("RegisterPhoto", "Foto capturada automaticamente");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}

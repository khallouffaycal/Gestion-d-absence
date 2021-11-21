package com.pfa.cameraupload;

import java.io.File;
import java.io.Serializable;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.midi.MidiSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();
    SharedPreferences sharedpreferences;
    private String filePath = null;
    private String Urifile;
    private TextView txtPercentage;
    private ImageView imgPreview;
    private VideoView vidPreview;
    private Button btnUpload;
    long totalSize = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.reconnaissance_faciale);
        setContentView(R.layout.activity_upload);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        vidPreview = (VideoView) findViewById(R.id.videoPreview);
        sharedpreferences = getSharedPreferences("Keys", Context.MODE_PRIVATE);

        // Changing action bar background color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor(getResources().getString(R.color.action_bar))));

        // Receiving the data from previous activity
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");
        Urifile = i.getStringExtra("Urifile");
        // boolean flag to identify the media type, image or video
        boolean isImage = i.getBooleanExtra("isImage", true);

        if (filePath != null) {
            // Displaying the image or video on the screen
            previewMedia(isImage);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }

        btnUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // uploading the file to server
                new UploadFileToServer().execute(sharedpreferences.getString("APIToken",""));
            }
        });

    }

    /**
     * Displaying captured image/video on the screen
     */
    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imgPreview.setVisibility(View.VISIBLE);
            vidPreview.setVisibility(View.GONE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

            imgPreview.setImageBitmap(bitmap);
        } else {
            imgPreview.setVisibility(View.GONE);
            vidPreview.setVisibility(View.VISIBLE);
            vidPreview.setVideoPath(filePath);
            // start playing
            vidPreview.start();
        }
    }

    /**
     * Uploading the file to server
     */
    private class UploadFileToServer extends AsyncTask<String, Integer, String> {
        boolean success;

        @Override
        protected String doInBackground(String... strings) {
            uploadFile(strings[0]);
            return null;
        }
        private void uploadFile(String auth) {
            // create upload service client
            JsonPlaceHolderApi service =
                    ServiceGenerator.createService(JsonPlaceHolderApi.class);

            File file = new File(filePath);
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            String mimeType = fileNameMap.getContentTypeFor(file.getName());

            Log.d("type", mimeType);
            // create RequestBody instance from file
            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(mimeType),
                            file
                    );
            Intent in = getIntent();
            long idSeance = Long.parseLong(Objects.requireNonNull(in.getStringExtra("idSeance")));
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            RequestBody idsc =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM,String.valueOf(idSeance));
            Call<ArrayList<SeanceEtudsFac>> call = service.upload(auth,body,idsc);
            call.enqueue(new Callback<ArrayList<SeanceEtudsFac>>() {
                @Override
                public void onResponse(Call<ArrayList<SeanceEtudsFac>> call,
                                       Response<ArrayList<SeanceEtudsFac>> response) {
                    Log.v("Upload", "success");
                    Intent i = new Intent(UploadActivity.this, EtudiantAllFacActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST",(Serializable)response.body());
                    i.putExtra("BUNDLE",args);
                    i.putExtra("idSeance",""+idSeance);
                    startActivity(i);
                }
                @Override
                public void onFailure(Call<ArrayList<SeanceEtudsFac>> call, Throwable t) {
                    showAlert("Failed: "+t.getMessage());
                }
            });
        }

        @Override
        protected void onPostExecute(String result) {


            //File file = new File(filePath);
            //file.delete();
            // showing the server response in an alert dialog


            super.onPostExecute(result);
        }
    }
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
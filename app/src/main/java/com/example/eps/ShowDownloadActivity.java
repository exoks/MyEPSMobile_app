package com.example.eps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ShowDownloadActivity extends AppCompatActivity {
    private PDFView view;
    private FloatingActionButton fab;
    private File tempFile;
    //private WebView web_pdf;
    private Uri downloadUrl;
    private String showUrl;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_download);

        fab = findViewById(R.id.pdf_fab);
        path = getIntent().getStringExtra(ListActivity.PATH);

        //Toast.makeText(this, "The path i got : " + path, Toast.LENGTH_SHORT).show();
        view = findViewById(R.id.web_pdf);

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(ShowDownloadActivity.this, permissions, 1);
        /*web_pdf.getSettings().setSupportZoom(true);
        web_pdf.getSettings().setJavaScriptEnabled(true);
        web_pdf.setWebViewClient(new WebViewClient());*/
        // view.fromAsset("المرفقات الادارية/chart.pdf").load();


    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference ref = storage.getReference();
        StorageReference reff = ref.child(path +".pdf");
        Toast.makeText(this, path + ".pdf", Toast.LENGTH_LONG).show();
        reff.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                downloadUrl = uri;
                // IMPORTANT you need to encode the url before give it to loadUrl()

                /**try {
                 showUrl = URLEncoder.encode(downloadUrl,"utf-8");
                 } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
                 }
                 web_pdf.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url="+showUrl);
                 //progressBar.setVisibility(View.GONE);**/
            }
        });


        tempFile = new File(Environment.getExternalStorageDirectory(),"file.pdf");
        reff.getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                view.fromFile(tempFile).load();
                // Requesting permissions
                //Toast.makeText(ShowDownloadActivity.this, path, Toast.LENGTH_SHORT).show();
                //Toast.makeText(ShowDownloadActivity.this, "Loading wih success", Toast.LENGTH_SHORT).show();
                //Intent in = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //in.setType("application/pdf");
                //in.addCategory(Intent.CATEGORY_OPENABLE);
                //in.putExtra(Intent.EXTRA_TITLE,tempFile.getName());
                //in.setData(Uri.fromFile(tempFile));
                //\startActivity(in);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String directory_path = Environment.getExternalStorageDirectory().getPath() + tempFile.getName();

                String targetPdf = directory_path + tempFile.getName();
                File filePath = new File(targetPdf);

                Intent intentShareFile = new Intent(Intent.ACTION_VIEW);
                File fileWithinMyDir = new File(targetPdf);
                Uri bmpUri = FileProvider.getUriForFile(getBaseContext(), "com.packagename.app.fileprovider", filePath);
                if (fileWithinMyDir.exists()) {
                    intentShareFile.setDataAndType(bmpUri,"application/pdf");
                    intentShareFile.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentShareFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Intent.createChooser(intentShareFile, "Open File Using..."));
                }*/

                //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/example.pdf");
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                //startActivity(intent);
                Toast.makeText(ShowDownloadActivity.this, "downloading ...", Toast.LENGTH_SHORT).show();
                DownloadManager.Request mn = new DownloadManager.Request(downloadUrl);
                mn.setTitle(reff.getName());
                mn.setMimeType("application/pdf");
                mn.allowScanningByMediaScanner();
                mn.setAllowedOverMetered(true);
                mn.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, reff.getName());
                mn.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(mn);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ( requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Need permission to accses ", Toast.LENGTH_SHORT).show();
            finish();        }
    }
}
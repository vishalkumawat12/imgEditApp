package com.example.instaediter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatButton button;
    boolean On =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        setSupportActionBar(toolbar);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ImagePicker.Companion.with(MainActivity.this)
                .crop()
//                .compress(1024)
                .start();
    }
});
    }
   private void initialization(){
        toolbar=findViewById(R.id.toolbar);
        button=findViewById(R.id.button);
           }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        
        try {
;
            Uri uri=data.getData();
            if (!uri.toString().equals("")){
                On=true;
                setup(uri.toString());
            }

        }
        catch (Exception e){
            Log.d("onActivityResult: ","Error");
        }
    }
    protected void setup(String  uri1){

        Uri filePath = Uri.parse(uri1);

        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
        dsPhotoEditorIntent.setData(filePath);
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Pico");
        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
        startActivityForResult(dsPhotoEditorIntent, 200);
        }



}
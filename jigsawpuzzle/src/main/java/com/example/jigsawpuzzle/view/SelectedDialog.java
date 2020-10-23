package com.example.jigsawpuzzle.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jigsawpuzzle.MainActivity;
import com.example.jigsawpuzzle.R;

import java.io.File;

/**
 * @author by chenlp
 * @date 2020/9/7
 * @describe
 */
public class SelectedDialog extends Dialog implements View.OnClickListener {
    public static final int RESULT_IMAGE = 100;
    public static final int RESULT_CAMERA = 200;
    public static final String IMAGE_TYPE = "image/*";
    public static final String  TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "/temp.png";


    private Context mContext;

    public SelectedDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public SelectedDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected SelectedDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_dialog);
        initView();


    }

    private void initView() {
        Button btn_selected_from_camera = findViewById(R.id.btn_selected_from_camera);
        Button btn_take_a_picture = findViewById(R.id.btn_take_a_picture);
        btn_selected_from_camera.setOnClickListener(this);
        btn_take_a_picture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_selected_from_camera:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_TYPE);
                ((MainActivity) mContext).startActivityForResult(intent, RESULT_IMAGE);
                break;

            case R.id.btn_take_a_picture:

                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri photoUri = Uri.fromFile(new File(TEMP_IMAGE_PATH));
                intent1.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                ((MainActivity) mContext).startActivityForResult(intent1, RESULT_IMAGE);
                break;
        }
    }
}

package com.example.jigsawpuzzle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.example.jigsawpuzzle.activity.PuzzleMain;
import com.example.jigsawpuzzle.adapter.ImageAdapter;
import com.example.jigsawpuzzle.utils.ScreenUtils;
import com.example.jigsawpuzzle.view.SelectedDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.jigsawpuzzle.view.SelectedDialog.RESULT_CAMERA;
import static com.example.jigsawpuzzle.view.SelectedDialog.RESULT_IMAGE;
import static com.example.jigsawpuzzle.view.SelectedDialog.TEMP_IMAGE_PATH;

public class MainActivity extends AppCompatActivity {


    private List<Bitmap> mJigsawImageList = new ArrayList<>();
    private ImageAdapter mImageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        mImageAdapter.setOnJumpListener(new ImageAdapter.OnJumpListener() {
            @Override
            public void jump(int position) {
                if (position == mJigsawImageList.size() - 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showDialogCustom();
                        }
                    });


                } else {
                    Intent intent = new Intent(MainActivity.this, PuzzleMain.class);
                    intent.putExtra("picSelectedID", mJigsawImageList.get(position));
                    startActivity(intent);
                }
            }
        });
    }

    private void showDialogCustom() {
        SelectedDialog selectedDialog = new SelectedDialog(this);
        selectedDialog.show();
    }

    private void initView() {
        GridView gv_image = findViewById(R.id.gv_image);
        mImageAdapter = new ImageAdapter(this);
        mImageAdapter.addList(mJigsawImageList);
        gv_image.setAdapter(mImageAdapter);
    }

    private void initData() {
        int[] resPicId = new int[7];
        for (int i = 0; i < resPicId.length - 1; i++) {
            resPicId[i] = getResources().getIdentifier("img" + i, "drawable", getPackageName());//加载资源
        }
        resPicId[resPicId.length - 1] = R.drawable.icon_launcher;
        Bitmap[] bitmaps = new Bitmap[resPicId.length];
        for (int i = 0; i < bitmaps.length; i++) {
            mJigsawImageList.add(BitmapFactory.decodeResource(getResources(), resPicId[i]));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_IMAGE && data != null) {
                Cursor cursor = this.getContentResolver().query(Objects.requireNonNull(data.getData()), null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    String imagePath = cursor.getString(cursor.getColumnIndex("_data"));
                    Intent intent = new Intent(MainActivity.this, PuzzleMain.class);
                    intent.putExtra("mPicPath", imagePath);
                    cursor.close();
                    startActivity(intent);
                }
            } else if (requestCode == RESULT_CAMERA) {
                Intent intent = new Intent(MainActivity.this, PuzzleMain.class);
                intent.putExtra("mPicPath", TEMP_IMAGE_PATH);
                startActivity(intent);
            }
        }
    }
}

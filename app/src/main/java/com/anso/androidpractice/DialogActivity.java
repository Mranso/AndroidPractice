package com.anso.androidpractice;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.anso.androidpractice.widget.MyBottomSheetDialog;

public class DialogActivity extends AppCompatActivity {

    private Context context;
    private Button bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        context = this;
        initView();
        initListener();
    }

    private void initView() {
        bottomSheetDialog = (Button) findViewById(R.id.dialog_activity_bottom_sheet_dialog);
    }

    private void initListener() {
        bottomSheetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBottomSheetDialog bottomSheetDialog = new MyBottomSheetDialog(context);
                View bottomSheetDialogView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog_layout, null);
                ImageView imageView = (ImageView) bottomSheetDialogView.findViewById(R.id.bottom_sheet_dialog_button);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "111", Toast.LENGTH_SHORT).show();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }
}

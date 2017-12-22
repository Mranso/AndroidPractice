package com.anso.androidpractice;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.anso.androidpractice.widget.MyBottomSheetDialog;

public class DialogActivity extends AppCompatActivity {

    private Context context;
    private Button bottomSheetDialog, alertDialog;

    private String dialogListItems[] = new String[]{"listItems1", "listItems2", "listItems3",
            "listItems4", "listItems5", "listItems6"};

    private String checkBoxItems[] = new String[]{"checkBoxItems1", "checkBoxItems2",
            "checkBoxItems3", "checkBoxItems4"};
    private boolean isCheck[] = new boolean[]{false, true, true, false};

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
        alertDialog = (Button) findViewById(R.id.dialog_activity_alert_dialog);
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

        alertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//                alertDialogBuilder.setTitle("提示");
//                alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                alertDialogBuilder.setCancelable(true);


                //简单消息弹窗设置
//                alertDialogBuilder.setMessage("这是提示框内容");

                //简单列表弹窗设置
//                alertDialogBuilder.setItems(dialogListItems, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "click" + which, Toast.LENGTH_SHORT).show();
//                    }
//                });

                //简单单选框弹窗设置
//                alertDialogBuilder.setSingleChoiceItems(dialogListItems, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(DialogActivity.this, dialogListItems[which], Toast.LENGTH_SHORT).show();
//                    }
//                });

                //简单多选框弹窗设置
//                alertDialogBuilder.setMultiChoiceItems(checkBoxItems, isCheck, new DialogInterface.OnMultiChoiceClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                                if (isChecked) {
//                                    Toast.makeText(DialogActivity.this,
//                                            dialogListItems[which] + "   选中", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(DialogActivity.this,
//                                            dialogListItems[which] + "   未选中", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

                //自定义布局框弹窗设置
                View dialogView = LayoutInflater.from(context).inflate(R.layout.alert_dialog_custom_layout, null);
                ImageView button = (ImageView) dialogView.findViewById(R.id.alert_dialog_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogActivity.this, "111111", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialogBuilder.setView(dialogView);

                //dialog确认 取消，中立按钮设置
//                alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                alertDialogBuilder.setNeutralButton("中", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
                alertDialogBuilder.show();
            }
        });
    }
}

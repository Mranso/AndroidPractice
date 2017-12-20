package com.anso.androidpractice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Normal Permissions
 * ACCESS_LOCATION_EXTRA_COMMANDS
 * ACCESS_NETWORK_STATE
 * ACCESS_NOTIFICATION_POLICY
 * ACCESS_WIFI_STATE
 * BLUETOOTH
 * BLUETOOTH_ADMIN
 * BROADCAST_STICKY
 * CHANGE_NETWORK_STATE
 * CHANGE_WIFI_MULTICAST_STATE
 * CHANGE_WIFI_STATE
 * DISABLE_KEYGUARD
 * EXPAND_STATUS_BAR
 * GET_PACKAGE_SIZE
 * INSTALL_SHORTCUT
 * INTERNET
 * KILL_BACKGROUND_PROCESSES
 * MODIFY_AUDIO_SETTINGS
 * NFC
 * READ_SYNC_SETTINGS
 * READ_SYNC_STATS
 * RECEIVE_BOOT_COMPLETED
 * REORDER_TASKS
 * REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
 * REQUEST_INSTALL_PACKAGES
 * SET_ALARM
 * SET_TIME_ZONE
 * SET_WALLPAPER
 * SET_WALLPAPER_HINTS
 * TRANSMIT_IR
 * UNINSTALL_SHORTCUT
 * USE_FINGERPRINT
 * VIBRATE
 * WAKE_LOCK
 * WRITE_SYNC_SETTINGS
 * <p>
 * <p>
 * Dangerous Permissions
 * CALENDAR（日历）
 * READ_CALENDAR
 * WRITE_CALENDAR
 * <p>
 * CAMERA（相机）
 * CAMERA
 * <p>
 * CONTACTS（联系人）
 * READ_CONTACTS
 * WRITE_CONTACTS
 * GET_ACCOUNTS
 * <p>
 * LOCATION（位置）
 * ACCESS_FINE_LOCATION
 * ACCESS_COARSE_LOCATION
 * <p>
 * MICROPHONE（麦克风）
 * RECORD_AUDIO
 * <p>
 * PHONE（手机）
 * READ_PHONE_STATE
 * CALL_PHONE
 * READ_CALL_LOG
 * WRITE_CALL_LOG
 * ADD_VOICEMAIL
 * USE_SIP
 * PROCESS_OUTGOING_CALLS
 * <p>
 * SENSORS（传感器）
 * BODY_SENSORS
 * <p>
 * SMS（短信）
 * SEND_SMS
 * RECEIVE_SMS
 * READ_SMS
 * RECEIVE_WAP_PUSH
 * RECEIVE_MMS
 * <p>
 * STORAGE（存储卡）
 * READ_EXTERNAL_STORAGE
 * WRITE_EXTERNAL_STORAGE
 * <p>
 * <p>
 * 在6.0版本以上申请危险权限如果你申请某个危险的权限，假设你的app早已被用户授权了同一组的某个危险权限，那么系统会立即授权，而不需要用户去点击授权
 * 不要对权限组过多的依赖，尽可能对每个危险权限都进行正常流程的申请，因为在后期的版本中这个权限组可能会产生变化
 *
 *
 * 优秀的权限库
 * PermissionsDispatcher 使用注解的方式
 * RxPermissions
 * easypermissions
 * AndPermission
 */


public class PermissionsActivity extends AppCompatActivity {

    private Context context;
    private Button requestPermission;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        context = this;
        initView();
        initListener();
    }

    private void initListener() {
        requestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * PackageManager.PERMISSION_DENIED        未获取权限
                 * PackageManager.PERMISSION_GRANTED       已获取权限
                 * */

                //检查权限
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {

                    //主要用于给用户一个申请权限的解释，该方法只有在用户在上一次已经拒绝过你的这个权限申请。
                    //也就是说，用户已经拒绝一次了，你又弹个授权框，你需要给用户一个解释，为什么要授权，则使用该方法
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionsActivity.this,
                            Manifest.permission.RECORD_AUDIO)) {
                        Toast.makeText(context, "需要权限去录音", Toast.LENGTH_SHORT).show();
                    } else {
                        //异步申请权限
                        ActivityCompat.requestPermissions(PermissionsActivity.this,
                                new String[]{Manifest.permission.RECORD_AUDIO},
                                PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                } else {
                    //已获取相应权限，可处理业务逻辑
                    readContacts();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //处理获取权限之后的业务逻辑
                    readContacts();
                } else {
                    //用户拒绝权限请求，弹窗或Toast提示
                    Toast.makeText(context, "用户拒绝了录音权限", Toast.LENGTH_SHORT).show();
                }
                break;

            }
            default:
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void readContacts() {
        Toast.makeText(context, "录音成功", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        requestPermission = (Button) findViewById(R.id.request_permission);
    }
}

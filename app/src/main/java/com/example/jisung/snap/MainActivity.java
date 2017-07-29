package com.example.jisung.snap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        final AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        View view = getLayoutInflater().inflate(R.layout.no_smoking_dialog, null);
        final Button cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        final Button confirmBtn = (Button) view.findViewById(R.id.confirmBtn);
        alertDialog.setView(view);
        alertDialog.show();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "취소되었습니다!", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "금연펀드에 가입되었습니다!", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
    }

    void setActionBar() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.

        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainCol)));

        View view = getLayoutInflater().inflate(R.layout.action_bar, null);
        actionBar.setCustomView(view);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.comBtn) {
            NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(MainActivity.this.getApplicationContext(), MainActivity.class); //인텐트 생성.
            Notification.Builder builder = new Notification.Builder(getApplicationContext());
            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);//현재 액티비티를 최상으로 올리고, 최상의 액티비티를 제외한 모든 액티비티를 없앤다.
            PendingIntent pendingNotificationIntent = PendingIntent.getActivity(MainActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setSmallIcon(R.drawable.logo).setTicker("SNAP").setWhen(System.currentTimeMillis())
                    .setNumber(1).setContentTitle("금연에 실패하셨습니다!!!!").setContentText("금연에 실패하셨습니다!!!!")
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingNotificationIntent).setAutoCancel(true).setOngoing(true);
            //해당 부분은 API 4.1버전부터 작동합니다.

            notificationManager.notify(1, builder.build());
            Intent intent = new Intent(this, CommunityActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.chatBtn) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.monBtn) {
            Intent intent = new Intent(this, MoneyActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.setBtn) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
    }
}

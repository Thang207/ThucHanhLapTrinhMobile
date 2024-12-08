package com.example.bth5_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcastReceiver();
    }

    private void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_LONG).show();
        TextView tvContent = findViewById(R.id.tv_content);

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);

        StringBuilder sms = new StringBuilder();
        SmsMessage smsMsg;

        for (int i = 0; i < Objects.requireNonNull(messages).length; i++) {
            String format = bundle.getString("format");
            smsMsg = SmsMessage.createFromPdu((byte[]) messages[i], format);

            String smsBody = smsMsg.getMessageBody();
            String address = smsMsg.getDisplayOriginatingAddress();

            sms.append(address).append(":\n").append(smsBody).append("\n");
        }
        tvContent.setText(sms.toString());
    }

    private void initBroadcastReceiver() {
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) initBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
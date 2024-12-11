package com.example.bth5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButtons;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver broadcastReceiver;
    public static boolean isRunning;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private final String AUTO_RESPONSE = "auto_response";

    private void findViewsByIds() {
        swAutoResponse = findViewById(R.id.sw_auto_response);
        llButtons = findViewById(R.id.ll_buttons);
        lvMessages = findViewById(R.id.lv_messages);
        btnSafe = findViewById(R.id.btn_safe);
        btnMayday = findViewById(R.id.btn_mayday);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();
        handleOnClickListener();

        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY) != null) {
            processReceiveAddresses(Objects.requireNonNull(intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY)));
        }
    }

    private void respond(String to, String response) {
        reentrantLock.lock();
        requesters.remove(to);
        adapter.notifyDataSetChanged();
        reentrantLock.unlock();


        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(to, null, response, null, null);

    }

    private void respond(boolean ok) {
        String okString = getString(R.string.i_am_safe_and_well_worry_not);
        String notOkString = getString(R.string.tell_my_mother_i_love_her);

        String outputString = ok ? okString : notOkString;

        ArrayList<String> requestersCopy = (ArrayList<String>) requesters.clone();

        for (String to : requestersCopy) {
            respond(to, outputString);
        }
    }

    public void processReceiveAddresses(ArrayList<String> addresses) {
        for (int i = 0; i < addresses.size(); i++) {
            if (!requesters.contains(addresses.get(i))) {
                reentrantLock.lock();
                requesters.add(addresses.get(i));
                adapter.notifyDataSetChanged();
                reentrantLock.unlock();
            }

            if (swAutoResponse.isChecked()) respond(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
        if (broadcastReceiver == null) initBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
        unregisterReceiver(broadcastReceiver);
    }
    private void handleOnClickListener() {

        btnSafe.setOnClickListener(view -> respond(true));
        btnMayday.setOnClickListener(view -> respond(false));
        swAutoResponse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) llButtons.setVisibility(View.GONE);
            else llButtons.setVisibility(View.VISIBLE);
            // Save auto response setting
            editor.putBoolean(AUTO_RESPONSE, isChecked);
            editor.commit();
        });
    }
    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<String> addresses = intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);
                assert addresses != null;
                processReceiveAddresses(addresses);
            }
        };
    }
    private void initVariables() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

        reentrantLock = new ReentrantLock();
        requesters = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, requesters);

        lvMessages.setAdapter(adapter);

        boolean autoResponse = sharedPreferences.getBoolean(AUTO_RESPONSE, false);

        swAutoResponse.setChecked(autoResponse);
        if (autoResponse) llButtons.setVisibility(View.GONE);

        initBroadcastReceiver();
    }
}
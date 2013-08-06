
package com.example.bservice;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.bservice.debug.BLog;
import com.example.bservice.event.BEvent;

/**
 * BServiceとのコネクションを基底Classに実装したケース
 */
public class SecondActivity extends BBaseActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BLog.d(TAG, "onCreate");
        setContentView(R.layout.activity_second);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        BLog.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        BLog.d(TAG, "onStop");
    }

    @Override
    protected void onServiceEvent(BEvent event) {
        // XXX: ログ出力だけ仮実装
        if (event != null) {
            BLog.d(TAG, "onEvent(kind=" + event.getKind() + " code=" + event.getCode() + ")");
        } else {
            BLog.d(TAG, "onEvent(event=null)");
            return;
        }
    }

    private void init() {
        findViewById(R.id.senddummyevent_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String DUMMY_CODE = "DUMMY_CODE";
                byte[] data = DUMMY_CODE.getBytes();
                sendEvent(new BEvent(TAG, DUMMY_CODE, data.length, data));
            }
        });
    }
}


package com.example.bservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.bservice.BServiceIF;
import com.example.bservice.BServiceListener;
import com.example.bservice.R;
import com.example.bservice.debug.BLog;
import com.example.bservice.event.BEvent;

/**
 * BServiceとのコネクションをActivityに直接実装したケース
 */
public class LauncherActivity extends Activity {
    private static final String TAG = LauncherActivity.class.getSimpleName();

    /** BService IF instance */
    private BServiceIF mBServiceIF;

    /** Service Conn (for Background Service) */
    private final ServiceConnection mBServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBServiceIF = BServiceIF.Stub.asInterface(service);
            registerBServiceListener();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    /** Background Service Event Listener */
    private final BServiceListener mBServiceListener = new BServiceListener.Stub() {
        @Override
        public void onEvent(BEvent event) throws RemoteException {
            if (event != null) {
                BLog.d(TAG, "onEvent(kind=" + event.getKind() + " code=" + event.getCode() + ")");
            } else {
                BLog.d(TAG, "onEvent(event=null)");
                return;
            }
        }
    };

    /** bind and register to BService */
    private void registerBServiceListener() {
        if (mBServiceIF == null) {
            bindService(new Intent(BServiceIF.class.getName()), mBServiceConnection,
                    Context.BIND_AUTO_CREATE);
        } else {
            try {
                mBServiceIF.registerListener(mBServiceListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /** unregister and unbind to BService */
    private void unregisterBServiceListener() {
        if (mBServiceIF != null) {
            try {
                mBServiceIF.unregisterListener(mBServiceListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            try {
                unbindService(mBServiceConnection);
                mBServiceIF = null;
            } catch (IllegalArgumentException e) {
                // ignore
            }
        }
    }

    /** send Event if IF is available */
    private void sendEvent(BEvent event) {
        if (mBServiceIF == null) {
            BLog.d(TAG, "sendEvent(mBServiceIF=null)");
            return;
        }
        try {
            mBServiceIF.sendCommand(event);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BLog.d(TAG, "onCreate");
        setContentView(R.layout.activity_launcher);

        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        BLog.d(TAG, "onStart");
        registerBServiceListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        BLog.d(TAG, "onStop");
        unregisterBServiceListener();
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

        findViewById(R.id.start_musicactivity_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SecondActivity.class);
                startActivity(i);
            }
        });
    }
}

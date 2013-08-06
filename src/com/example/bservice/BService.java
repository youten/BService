
package com.example.bservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.bservice.debug.BLog;
import com.example.bservice.event.BEvent;

/**
 * よくあるBackground Service
 */
public class BService extends Service {
    private static String TAG = BService.class.getSimpleName();

    private static final String PREFIX_ACTION = BService.class.getPackage().getName() + ".action.";
    private static final String ACTION_START = PREFIX_ACTION + "START";
    private static final String ACTION_STOP = PREFIX_ACTION + "STOP";

    /** callback list */
    private RemoteCallbackList<BServiceListener> callbackList = new RemoteCallbackList<BServiceListener>();

    /** BServiceIFのStub */
    private final BServiceIF.Stub stub = new BServiceIF.Stub() {
        @Override
        public void registerListener(BServiceListener listener) throws RemoteException {
            synchronized (callbackList) {
                BLog.d(TAG, "registerListener");
                callbackList.register(listener);
            }
        }

        @Override
        public void unregisterListener(BServiceListener listener) throws RemoteException {
            synchronized (callbackList) {
                BLog.d(TAG, "unregisterListener");
                callbackList.unregister(listener);
            }
        }

        @Override
        public void sendCommand(BEvent command) throws RemoteException {
            if (command != null) {
                BLog.d(TAG, "sendCommand(kind=" + command.getKind() + " code=" + command.getCode()
                        + ")");
            } else {
                BLog.d(TAG, "sendCommand(command=null)");
                return;
            }

            // XXX: 差し当たりオブジェクトをコピーしてオウム返し
            int len = command.getDataLength();
            byte[] data = new byte[len];
            System.arraycopy(command.getData(), 0, data, 0, len);
            new CallbackThread(new BEvent(command.getKind(), command.getCode(), len, data)).start();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        BLog.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BLog.d(TAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleCommand(intent);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            if (BServiceIF.class.getName().equals(intent.getAction())) {
                return stub;
            }
        }

        return null;
    }

    private void handleCommand(Intent intent) {
        if (intent == null) {
            BLog.d(TAG, "intent=null");
            return;
        }
        String action = intent.getAction();
        if (ACTION_START.equals(action)) {
            BLog.d(TAG, "ACTION_START");

        } else if (ACTION_STOP.equals(action)) {
            BLog.d(TAG, "ACTION_STOP");

        } else {
            BLog.d(TAG, "Unknown Action");
        }
    }

    /**
     * BServiceEventをListenerへcallbackするThread<br>
     * new CallbackThread.(new BEvent(kind, code, data)).start(); の様にして使う。
     */
    public class CallbackThread extends Thread {
        private BEvent mEvent;

        public CallbackThread(BEvent event) {
            mEvent = event;
        }

        @Override
        public void run() {
            synchronized (callbackList) {
                int listeners = callbackList.beginBroadcast();
                if (listeners > 0) {
                    for (int i = 0; i < listeners; i++) {
                        try {
                            callbackList.getBroadcastItem(i).onEvent(mEvent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                callbackList.finishBroadcast();
            }
        }
    }
}

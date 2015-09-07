
package youten.redo.bservice;

import youten.redo.bservice.debug.BLog;
import youten.redo.bservice.event.BEvent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Background Serviceとの接続をよしなにやってくれる規定クラス
 */
public abstract class BBaseActivity extends Activity {
    private static final String TAG = BBaseActivity.class.getSimpleName();
    private static final ComponentName BSERVICE_COMPONENT_NAME = new ComponentName(BService.class
            .getPackage().getName(), BService.class.getName());

    /** BService IF instance */
    private BServiceIF mBServiceIF;

    /** Service Conn (for Background Service) */
    private final ServiceConnection mBServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BLog.d(TAG, "onServiceConnected(name=" + name);

            if (BSERVICE_COMPONENT_NAME.equals(name)) {
                mBServiceIF = BServiceIF.Stub.asInterface(service);
                registerBServiceListener();
                onBServiceConnected();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    /** Background Service Event Listener */
    private final BServiceListener mBServiceListener = new BServiceListener.Stub() {
        @Override
        public boolean onEvent(BEvent event) {
            return onBServiceEvent(event);
        }
    };

    /** bind and register to BService */
    private void registerBServiceListener() {
        if (mBServiceIF == null) {
            Intent intent = new Intent(BServiceIF.class.getName());
            intent.setPackage(BServiceIF.class.getPackage().getName());
            bindService(intent, mBServiceConnection,
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

    /**
     * BService初回接続時
     *
     * @param event
     */
    abstract protected void onBServiceConnected();

    /**
     * BServiceからのイベントコールバック
     *
     * @param event
     */
    protected boolean onBServiceEvent(BEvent event) {
        return false;
    }

    /** send Event if IF is available */
    protected void sendEvent(BEvent event) {
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBServiceListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterBServiceListener();
    }
}

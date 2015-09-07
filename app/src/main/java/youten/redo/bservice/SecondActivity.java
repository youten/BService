
package youten.redo.bservice;

import youten.redo.bservice.debug.BLog;
import youten.redo.bservice.event.BEvent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


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
    protected void onBServiceConnected() {
        BLog.d(TAG, "onBServiceConnected");
        // TODO: ここにBServiceと初回接続時の処理を実装する。
    }

    @Override
    protected boolean onBServiceEvent(BEvent event) {
        if (event != null) {
            BLog.d(TAG, "onEvent(kind=" + event.getKind() + " code=" + event.getCode() + ")");
        } else {
            BLog.d(TAG, "onEvent(event=null)");
            return false;
        }
        // TODO: ここにイベントcallback時の処理を実装する。
        return true;
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


package youten.redo.bservice.debug;

import android.util.Log;

public class BLog {
    public static final boolean IS_LOG = true;
    public static final boolean IS_DEBUG = true;

    public static final void d(String tag, String msg) {
        if (IS_LOG && IS_DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static final void d(String tag, String msg, Throwable tr) {
        if (IS_LOG && IS_DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    public static final void e(String tag, String msg) {
        if (IS_LOG) {
            Log.e(tag, msg);
        }
    }

    public static final void e(String tag, String msg, Throwable tr) {
        if (IS_LOG) {
            Log.e(tag, msg, tr);
        }
    }

}

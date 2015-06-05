package com.zodiake.app;

import android.app.Activity;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.PowerManager;
import org.json.JSONObject;
/**
 * Created by yagamai on 15-6-4.
 */
public class LocalNotificationPlugin extends CordovaPlugin {
    public static final String NOTIFY= "notify";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
    throws JSONException {
        if (NOTIFY.equals(action)) {
            Activity context = cordova.getActivity();
            JSONObject arg_object = args.getJSONObject(0);
            String title = arg_object.getString("title");
            String content = arg_object.getString("content");

            Notification.Builder notification = new Notification.Builder(context)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(getIconResId());

            NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PowerManager pm =
                    (PowerManager)context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl =
                    pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "");
            wl.acquire();
            manager.notify(1,notification.build());
            wl.release();

            return true;
        }
        return false;
    }

    private int getIconResId() {
        Context context = cordova.getActivity().getApplicationContext();
        Resources res   = context.getResources();
        String pkgName  = context.getPackageName();

        int resId;
        resId = res.getIdentifier("icon", "drawable", pkgName);
        return resId;
    }
}

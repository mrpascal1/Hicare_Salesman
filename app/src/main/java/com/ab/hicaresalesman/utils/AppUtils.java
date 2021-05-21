package com.ab.hicaresalesman.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.area.AddAreaRequest;
import com.ab.hicaresalesman.network.models.area.AreaData;
import com.ab.hicaresalesman.network.models.area.AreaType;
import com.ab.hicaresalesman.network.models.area.ServiceActivity;
import com.ab.hicaresalesman.network.models.area.ServiceActivityRequest;
import com.ab.hicaresalesman.network.models.area.ServiceActivityType;
import com.ab.hicaresalesman.network.models.area.TowerData;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;


public class AppUtils {

    public static List<AreaType> mCommonList = null;
    public static List<AreaType> mRegularList = null;
    public static List<TowerData> mCommonTowerList = null;
    public static List<TowerData> mRegularTowerList = null;
    public static List<ServiceActivityType> mServiceList = new ArrayList<>();
    public static List<AddAreaRequest> mAreaData = new ArrayList<>();
    public static HashMap<Integer,AddAreaRequest> mHashArea = new HashMap<>();
    public  static boolean isCommonAllowMultiple = false;
    public  static boolean isRegularAllowMultiple = false;
    private static final int REQ_AREA = 1000;
    public static String CAMERA_ORIENTATION = "CAMERA_ORIENTATION";
    public static String CAMERA_SCREEN = "QUESTIONS";


    public static void getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        String formattedDate = dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: " + formattedDate);
    }


    public static String compareDates(String d1, String d2) {
        String date_result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date2);
//            cal.add(Calendar.MINUTE, 15);
//            Date dateAfter = cal.getTime();
            if (date1.after(date2)) {
                date_result = "afterdate";

            }
            if (date1.before(date2)) {
                date_result = "beforedate";
            }

            if (date1.equals(date2)) {
                date_result = "equalsdate";
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date_result;
    }

    public static String compareLoginDates(String d1, String d2) {
        String date_result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
            if (date1.after(date2)) {
                date_result = "after";
            }
            if (date1.before(date2)) {
                date_result = "before";
            }
            if (date1.equals(date2)) {
                date_result = "equal";
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date_result;
    }

    public static String covertTimeToText(String dataDate) {

        String convertTime = null;
        String suffix = "ago";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
            Date pasTime = dateFormat.parse(dataDate);

            Date nowTime = new Date();

            long dateDiff = nowTime.getTime() - pasTime.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                if (second == 1) {
                    convertTime = second + " second " + suffix;
                } else {
                    convertTime = second + " seconds " + suffix;
                }
            } else if (minute < 60) {
                if (minute == 1) {
                    convertTime = minute + " minute " + suffix;
                } else {
                    convertTime = minute + " minutes " + suffix;
                }
            } else if (hour < 24) {
                if (hour == 1) {
                    convertTime = hour + " hour " + suffix;
                } else {
                    convertTime = hour + " hours " + suffix;
                }
            } else if (day >= 7) {
                if (day >= 365) {
                    long tempYear = day / 365;
                    if (tempYear == 1) {
                        convertTime = tempYear + " year " + suffix;
                    } else {
                        convertTime = tempYear + " years " + suffix;
                    }
                } else if (day >= 30) {
                    long tempMonth = day / 30;
                    if (tempMonth == 1) {
                        convertTime = (day / 30) + " month " + suffix;
                    } else {
                        convertTime = (day / 30) + " months " + suffix;
                    }
                } else {
                    long tempWeek = day / 7;
                    if (tempWeek == 1) {
                        convertTime = (day / 7) + " week " + suffix;
                    } else {
                        convertTime = (day / 7) + " weeks " + suffix;
                    }
                }
            } else {
                if (day == 1) {
                    convertTime = day + " day " + suffix;
                } else {
                    convertTime = day + " days " + suffix;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("TimeAgo", e.getMessage() + "");
        }
        return convertTime;
    }

    public static String formatTime(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm aa", Locale.ENGLISH);
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String formatDate(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm aa", Locale.ENGLISH);
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String reFormatRedeemedDate(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String reFormatDateAndTime(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm aa", Locale.ENGLISH);
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String getFormatted(String dateIn, String format, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String currentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
        Date date1 = new Date();
        return dateFormat.format(date1);
    }

    public static String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        return dateFormat.format(date1);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTomorrowDate() {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    public static String formattedCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date1 = new Date();
        return dateFormat.format(date1);
    }


    public static String reFormatDateTime(String dateIn, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }


    public static String reFormatTime(String time, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        Date date = simpleDateFormat.parse(time);
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }


    private static void buildAlertMessageNoGps(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    try {
                        Intent mIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(mIntent);
                        dialog.dismiss();
                        Activity activity = (Activity) context;
                        activity.finishAffinity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                })
                .setNegativeButton("No, thanks", (dialog, id) -> {
                    dialog.cancel();
                    Activity activity = (Activity) context;
                    activity.finishAffinity();
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    public static String getCurrentTimeStamp() {
        String s = "";
        try {
            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            dateFormatter.setLenient(false);
            Date today = new Date();
            s = dateFormatter.format(today);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void getAreaByActivity(int activityId) {
        try {
            NetworkCallController controller = new NetworkCallController();
            controller.setListner(new NetworkResponseListner<List<AreaType>>() {
                @Override
                public void onResponse(List<AreaType> items) {
                    mCommonList = new ArrayList<>();
                    mRegularList = new ArrayList<>();
                    mCommonTowerList = new ArrayList<>();
                    mRegularTowerList = new ArrayList<>();
                    if (items != null && items.size() > 0) {
                        for (AreaType data : items) {
                            if (data.getTemplateType().equals("Common Area")) {
                                mCommonList.add(data);
                                mCommonTowerList.addAll(data.getData());
                                isCommonAllowMultiple = data.getAllowMultiple();
                            } else {
                                mRegularList.add(data);
                                mRegularTowerList.addAll(data.getData());
                                isRegularAllowMultiple = data.getAllowMultiple();
                            }
                        }
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getActivityBySubArea(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

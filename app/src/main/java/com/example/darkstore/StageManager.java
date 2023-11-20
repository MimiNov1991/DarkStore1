package com.example.darkstore;


import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class StageManager {


        private final static String TAG_SYSTEM_UTIL = "SystemUtil";

        public static void clearMemory(Context context) {
            long beforemem = getAvailableMemory(context);

            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos;
            List<ActivityManager.RunningServiceInfo> runningServiceInfos;
            if (am != null) {
                runningAppProcessInfos = am.getRunningAppProcesses();
                runningServiceInfos = am.getRunningServices(100);

                if (runningAppProcessInfos != null) {
                    for (ActivityManager.RunningAppProcessInfo r : runningAppProcessInfos) {
                        if (r.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                            String[] pkgList = r.pkgList;
                            for (String p : pkgList) {
                                am.killBackgroundProcesses(p);
                               //am.killBackgroundProcesses(p);
                            }//from   w w w.jav a 2s .co m
                        }
                    }
                }

                if (runningServiceInfos != null) {
                    for (ActivityManager.RunningServiceInfo r : runningServiceInfos) {

                    }
                }
            }

            long aftermem = getAvailableMemory(context);

        }

        public static long getAvailableMemory(Context context) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            am.getMemoryInfo(memoryInfo);

            Log.d(TAG_SYSTEM_UTIL, "before clear the memory is " + memoryInfo.availMem / (1024 * 1024));

            return memoryInfo.availMem / (1024 * 1024);
        }

    }



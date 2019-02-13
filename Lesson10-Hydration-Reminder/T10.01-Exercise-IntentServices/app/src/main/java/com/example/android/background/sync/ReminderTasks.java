package com.example.android.background.sync;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android.background.utilities.PreferenceUtilities;

// TODO (1) Create a class called ReminderTasks
public class ReminderTasks
{
    // TODO (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT
    public static final String ACTION_INCREMENT_WATER_COUNT = "increment water count";
    
    // TODO (6) Create a public static void method called executeTask
    // TODO (7) Add a Context called context and String parameter called action to the parameter list
    static void executeTask(Context context, @NonNull String action)
    {
        // TODO (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount
        if (action.equals(ACTION_INCREMENT_WATER_COUNT))
        {
            incrementWaterCount(context);
        }
        
    }
    
    // TODO (3) Create a private static void method called incrementWaterCount
    // TODO (4) Add a Context called context to the argument list
    private static void incrementWaterCount(Context context)
    {
        // TODO (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count
        PreferenceUtilities.incrementWaterCount(context);
    }
}

package com.example.android.todolist;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class TaskContentObserver extends ContentObserver
{
    private static final String TAG = TaskContentObserver.class.getSimpleName();
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Singleton
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static TaskContentObserver taskContentObserver;
    public static TaskContentObserver getInstance()
    {
        if (taskContentObserver == null)
        {
            HandlerThread thread = new HandlerThread(TaskContentObserver.class.getSimpleName() + " thread");
            thread.start();
            taskContentObserver = new TaskContentObserver(thread);
        }
        return taskContentObserver;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean mDataChanged = false;
    private final HandlerThread mHandlerThread;
    
    private TaskContentObserver(HandlerThread thread)
    {
        super(new Handler(thread.getLooper()));
        mHandlerThread = thread;
    }
    
    @Override
    public void onChange(boolean selfChange, Uri uri)
    {
        Log.d(TAG, "onChange: ");
        mDataChanged = true;
    }
    
    @Override
    public void onChange(boolean selfChange)
    {
        onChange(selfChange, null);
    }
    
    @Override
    public boolean deliverSelfNotifications()
    {
        return true;
    }
    
    public boolean isDataChanged()
    {
        return mDataChanged;
    }
    
    public void resetNotification()
    {
        Log.d(TAG, "resetNotification: ");
        mDataChanged = false;
    }
    
    public boolean checkAndReset()
    {
        boolean res = mDataChanged;
        mDataChanged = false;
        return res;
    }
}

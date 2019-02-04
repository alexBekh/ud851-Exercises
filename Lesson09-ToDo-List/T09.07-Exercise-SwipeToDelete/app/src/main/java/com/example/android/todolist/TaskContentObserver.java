package com.example.android.todolist;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

public class TaskContentObserver extends ContentObserver
{
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
    //  Listener
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public interface OnChangeListener
    {
        void onChange();
    }
    private OnChangeListener mChangeListener;
    
    public void registerListener(OnChangeListener listener)
    {
        mChangeListener = listener;
    }
    
    public void unregisterListener(OnChangeListener listener)
    {
        mChangeListener = null;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private final HandlerThread mHandlerThread;
    
    private TaskContentObserver(HandlerThread thread)
    {
        super(new Handler(thread.getLooper()));
        mHandlerThread = thread;
    }
    
    @Override
    public void onChange(boolean selfChange, Uri uri)
    {
        if(mChangeListener != null)
            mChangeListener.onChange();
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
}

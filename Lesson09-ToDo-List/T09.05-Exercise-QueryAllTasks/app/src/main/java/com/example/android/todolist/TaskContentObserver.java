package com.example.android.todolist;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

class TaskContentObserver extends ContentObserver
{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Singleton
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static TaskContentObserver sInstance;
    
    static TaskContentObserver getInstance()
    {
        if (sInstance == null)
        {
            HandlerThread handlerThread = new HandlerThread("ContentObserver thread");
            handlerThread.start();
            sInstance = new TaskContentObserver(handlerThread);
        }
        return sInstance;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Listener
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public interface onChangeListener
    {
        void onChange();
    }
    private onChangeListener mListener;
    
    public void registerOnChangeListener(onChangeListener listener)
    {
        mListener = listener;
    }
    
    public void unregisterOnChangeListener(onChangeListener listener)
    {
        mListener = null;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  fields & methods
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private HandlerThread mHandlerThread;
    
    private TaskContentObserver(HandlerThread handlerThread)
    {
        super(new Handler(handlerThread.getLooper()));
        mHandlerThread = handlerThread;
    }
    
    @Override
    public void onChange(boolean selfChange)
    {
        onChange(selfChange, null);
    }
    
    @Override
    public void onChange(boolean selfChange, Uri uri)
    {
        mListener.onChange();
    }
    
    @Override
    public boolean deliverSelfNotifications()
    {
        return true;
    }
}

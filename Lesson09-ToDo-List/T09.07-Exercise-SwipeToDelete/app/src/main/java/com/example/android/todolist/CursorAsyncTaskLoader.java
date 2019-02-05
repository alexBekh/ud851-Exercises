package com.example.android.todolist;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.todolist.data.TaskContract;

class CursorAsyncTaskLoader extends AsyncTaskLoader<Cursor>
{
    
    private static final String TAG = CursorAsyncTaskLoader.class.getSimpleName();
    // Initialize a Cursor, this will hold all the task data
    private Cursor mTaskData = null;
    private boolean mDataReloaded = false;
    
    CursorAsyncTaskLoader(Context context)
    {
        super(context);
    }
    
    // onStartLoading() is called when a loader first starts loading data
    @Override
    protected void onStartLoading()
    {
        mDataReloaded = false;
        
        if (mTaskData != null)
        {
            Log.d(TAG, "onStartLoading: using cached data");
            // Delivers any previously loaded data immediately
            deliverResult(mTaskData);
            
        }
        else
        {
            Log.d(TAG, "onStartLoading: reloading data");
            // Force a new load
            forceLoad();
            mDataReloaded = true;
        }
    }
    
    // loadInBackground() performs asynchronous loading of data
    @Override
    public Cursor loadInBackground()
    {
        // Will implement to load data
        
        // Query and load all task data in the background; sort by priority
        // [Hint] use a try/catch block to catch any errors in loading data
        try
        {
            return getContext().getContentResolver().query(TaskContract.TaskEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        TaskContract.TaskEntry.COLUMN_PRIORITY);
            
        }
        catch (Exception e)
        {
            Log.e(TAG, "Failed to asynchronously load data.");
            e.printStackTrace();
            return null;
        }
    }
    
    // deliverResult sends the result of the load, a Cursor, to the registered listener
    public void deliverResult(Cursor data)
    {
        if(mTaskData != null && mTaskData != data)
            mTaskData.close();
        
        mTaskData = data;
        super.deliverResult(data);
    }
    
    boolean isDataReloaded()
    {
        return mDataReloaded;
    }
}

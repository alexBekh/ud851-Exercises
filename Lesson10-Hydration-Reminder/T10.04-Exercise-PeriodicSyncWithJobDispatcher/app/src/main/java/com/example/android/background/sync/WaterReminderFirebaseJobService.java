/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.sync;

import android.os.Handler;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

// TODO (3) WaterReminderFirebaseJobService should extend from JobService
public class WaterReminderFirebaseJobService extends JobService
{
    private Thread mBackgroundTask;
    private Handler handler = new Handler();
    
    // TODO (4) Override onStartJob
    @Override
    public boolean onStartJob(final JobParameters job)
    {
        mBackgroundTask = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                ReminderTasks.executeTask(WaterReminderFirebaseJobService.this,
                        ReminderTasks.ACTION_CHARGING_REMINDER);
                
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        jobFinished(job, false);
                    }
                });
            }
        });
        mBackgroundTask.run();
        
        return true;
    }
    
    // TODO (11) Override onStopJob
    @Override
    public boolean onStopJob(JobParameters job)
    {
        // TODO (12) If mBackgroundTask is valid, cancel it
        if (mBackgroundTask != null)
            mBackgroundTask.interrupt();
        
        // TODO (13) Return true to signify the job should be retried
        return true;
    }
}

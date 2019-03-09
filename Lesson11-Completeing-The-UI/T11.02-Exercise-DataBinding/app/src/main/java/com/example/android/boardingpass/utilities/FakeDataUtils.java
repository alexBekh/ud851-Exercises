package com.example.android.boardingpass.utilities;

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

import android.content.Context;

import com.example.android.boardingpass.BoardingPassInfo;
import com.example.android.boardingpass.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to generate fake data that will be displayed in the boarding pass layout
 */
public class FakeDataUtils
{
    
    /**
     * Generates fake boarding pass data to be displayed.
     *
     * @return fake boarding pass data
     */
    public static BoardingPassInfo generateFakeBoardingPassInfo(Context context)
    {
        
        BoardingPassInfo bpi = new BoardingPassInfo();
        
        bpi.passengerName = "MR. RANDOM PERSON";
        bpi.flightCode = "UD 777";
        bpi.originCode = "JFK";
        bpi.destCode = "DCA";
        
        long now = System.currentTimeMillis();
        
        // Anything from 0 minutes up to (but not including) 30 minutes
        long randomMinutesUntilBoarding = (long) (Math.random() * 90);
        // Standard 40 minute boarding time
        long totalBoardingMinutes = 40;
        // Anything from 1 hours up to (but not including) 6 hours
        long randomFlightLengthHours = (long) (Math.random() * 5 + 1);
        
        long boardingMillis = now + minutesToMillis(randomMinutesUntilBoarding);
        long departure = boardingMillis + minutesToMillis(totalBoardingMinutes);
        long arrival = departure + hoursToMillis(randomFlightLengthHours);
        
        SimpleDateFormat formatter = new SimpleDateFormat(context.getString(R.string.timeFormat), Locale.getDefault());
        bpi.boardingTime = formatter.format(new Timestamp(boardingMillis));
        bpi.departureTime = formatter.format(new Timestamp(departure));
        bpi.arrivalTime = formatter.format(new Timestamp(arrival));
    
        long minutesUntilBoarding = TimeUnit.MILLISECONDS.toMinutes(boardingMillis - System.currentTimeMillis());
        long hours = minutesUntilBoarding / 60;
        long minutes = minutesUntilBoarding % 60;
        bpi.boardingInTime = String.format(context.getString(R.string.countDownFormat), hours, minutes);
        
        bpi.departureTerminal = "3A";
        bpi.departureGate = "33C";
        bpi.seatNumber = "1A";
        bpi.barCodeImageResource = R.drawable.art_plane;
        
        return bpi;
    }
    
    private static long minutesToMillis(long minutes)
    {
        return TimeUnit.MINUTES.toMillis(minutes);
    }
    
    private static long hoursToMillis(long hours)
    {
        return TimeUnit.HOURS.toMillis(hours);
    }
}
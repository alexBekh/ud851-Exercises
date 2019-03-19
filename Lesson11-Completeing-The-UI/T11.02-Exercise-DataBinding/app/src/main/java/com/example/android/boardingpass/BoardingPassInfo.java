package com.example.android.boardingpass;

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

/**
 * BoardingPassInfo is a simple POJO that contains information about, you guessed it, a boarding
 * pass! Normally, it is best practice in Java to declare member variables as private and provide
 * getters, but we are leaving these fields public for ease of use.
 */
public class BoardingPassInfo {

    public String passengerName;
    public String flightCode;
    public String originCode;
    public String destCode;

    public String boardingTime;
    public String departureTime;
    public String arrivalTime;
    public String boardingInTime;
    
    public String departureTerminal;
    public String departureGate;
    public String seatNumber;
    
    public int barCodeImageResource;
}
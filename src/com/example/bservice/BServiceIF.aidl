package com.example.bservice;

import com.example.bservice.BServiceListener;
import com.example.bservice.event.BEvent;

oneway interface BServiceIF {
    void registerListener(in BServiceListener listener);
    void unregisterListener(in BServiceListener listener);
    void sendCommand(in BEvent command);
}
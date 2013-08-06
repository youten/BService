package com.example.btsppservice;

import com.example.btsppservice.BTSppServiceListener;
import com.example.btsppservice.event.BEvent;

oneway interface BTSppServiceIF {
    void registerListener(in BTSppServiceListener listener);
    void unregisterListener(in BTSppServiceListener listener);
    void sendCommand(in BEvent command);
}
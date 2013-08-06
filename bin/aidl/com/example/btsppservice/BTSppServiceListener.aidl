package com.example.btsppservice;

import com.example.btsppservice.event.BEvent;

oneway interface BTSppServiceListener {
    void onEvent(in BEvent event);
}
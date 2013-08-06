package com.example.bservice;

import com.example.bservice.event.BEvent;

oneway interface BServiceListener {
    void onEvent(in BEvent event);
}
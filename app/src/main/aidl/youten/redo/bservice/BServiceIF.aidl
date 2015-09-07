package youten.redo.bservice;

import youten.redo.bservice.BServiceListener;
import youten.redo.bservice.event.BEvent;

oneway interface BServiceIF {
    void registerListener(in BServiceListener listener);
    void unregisterListener(in BServiceListener listener);
    void sendCommand(in BEvent command);
}
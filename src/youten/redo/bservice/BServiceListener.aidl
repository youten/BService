package youten.redo.bservice;

import youten.redo.bservice.event.BEvent;

interface BServiceListener {
    boolean onEvent(in BEvent event);
}
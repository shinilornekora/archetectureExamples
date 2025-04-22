package app.shiniasse.common.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private static final EventBus INST = new EventBus();
    public static EventBus getInstance() { return INST; }

    private final List<EventHandler> handlers = new CopyOnWriteArrayList<>();
    public interface EventHandler { void handle(Event e); }

    public void register(EventHandler h) { handlers.add(h); }
    public void unregister(EventHandler h) { handlers.remove(h); }

    public void publish(Event e) {
        for (EventHandler h : handlers) {
            h.handle(e);
        }
    }
}

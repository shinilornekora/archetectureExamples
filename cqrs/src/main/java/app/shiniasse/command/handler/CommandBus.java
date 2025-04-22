package app.shiniasse.command.handler;

import java.util.HashMap;
import java.util.Map;

import app.shiniasse.command.command.Command;

public class CommandBus {
    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> handlers = new HashMap<>();

    public <T extends Command> void register(Class<T> cls, CommandHandler<T> h) {
        handlers.put(cls, h);
    }

    @SuppressWarnings("unchecked")
    public <T extends Command> void dispatch(T cmd) {
        CommandHandler<T> h = (CommandHandler<T>) handlers.get(cmd.getClass());
        if (h == null) {
            throw new IllegalStateException("No handler for " + cmd.getClass());
        }
        h.handle(cmd);
    }
}

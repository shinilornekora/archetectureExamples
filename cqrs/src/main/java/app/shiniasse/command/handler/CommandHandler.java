package app.shiniasse.command.handler;

import app.shiniasse.command.command.Command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}

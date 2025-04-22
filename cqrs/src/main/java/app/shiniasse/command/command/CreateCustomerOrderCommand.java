package app.shiniasse.command.command;

import java.util.UUID;

public class CreateCustomerOrderCommand implements Command {
    private final String commandId;

    public CreateCustomerOrderCommand() {
        this.commandId = UUID.randomUUID().toString();
    }

    @Override
    public String getCommandId() {
        return commandId;
    }
}

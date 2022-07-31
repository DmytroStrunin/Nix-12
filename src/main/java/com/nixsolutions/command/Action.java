package com.nixsolutions.command;

import lombok.Getter;

@Getter
public enum Action {
    CREATE("Create vehicle", new Create()),
    UPDATE("Update vehicles", new Update()),
    PRINT("Print vehicles", new Print()),
    DELETE("Delete vehicle", new Delete()),
    EXIT("Exit", null);

    private final String name;
    private final Command command;

    Action(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public Command execute() {
        if (command != null) {
            command.execute();
        }
        return command;
    }
}

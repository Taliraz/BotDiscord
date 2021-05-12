package fr.taliraz.botdiscord.command;

import java.lang.reflect.Method;

import fr.taliraz.botdiscord.command.Command.ExecutorType;

public final class SimpleCommand {

    private final String name;
    private final String description;
    private final ExecutorType executorType;
    private final String locale;

    private final Object object;
    private final Method method;

    public SimpleCommand(String name, String description, ExecutorType executorType, String locale, Object object, Method method) {
        this.name = name;
        this.description = description;
        this.executorType = executorType;
        this.locale = locale;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public ExecutorType getExecutorType() {
        return executorType;
    }

    public String getLocale() { return locale; }
}

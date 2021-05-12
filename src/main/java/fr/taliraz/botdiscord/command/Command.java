package fr.taliraz.botdiscord.command;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    public String name();
    public String description() default "Sans description";
    public ExecutorType type() default ExecutorType.ALL;
    public String locale() default "fr";

    public enum ExecutorType{
        ALL, USER, CONSOLE
    }
}

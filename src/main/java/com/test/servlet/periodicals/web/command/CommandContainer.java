package com.test.servlet.periodicals.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Misha Malysh
 */
public class CommandContainer {

    private static final Logger log = LogManager.getLogger();

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put("email", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("errorCommand", new ErrorCommand());
        commands.put("viewSettings", new ViewSettingCommand());
        commands.put("updateSettings", new UpdateSettingCommand());

        // client commands
        commands.put("listPublications", new ListPublicationCommand());

        // admin commands
        commands.put("listOrders", new ListOrderCommand());
        commands.put("makeOrder", new MakeOrderCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());

    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("errorCommand");
        }

        return commands.get(commandName);
    }
}

package com.epam.chernev.servlet.cart.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private final Map<String, CartCommand> commands = new TreeMap<>();

    public CommandContainer() {

        commands.put("addToCart", new AddToCartCommand());
        commands.put("incCount", new IncrementCountCommand());
        commands.put("decCount", new DecrementCountCommand());
        commands.put("setCount", new SetCountCommand());
        commands.put("deleteProduct", new DeleteProductCommand());
        commands.put("clearCart", new ClearCartCommand());

    }


    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public CartCommand get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }


}

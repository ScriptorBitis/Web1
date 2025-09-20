package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = ServerLogger.getInstance();
        try {

            Server server = new Server();
            server.run();
        } catch (Exception e) {

            logger.severe(e.getMessage()
                    + "\n" + e.getStackTrace() +
                    "\n" + e.getCause().getMessage());
            System.exit(0);
        }
    }

}

package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import java.io.IOException;

public class TerminalUtiles {
    public static void executeTerminalCommand(String... commandParts) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commandParts);

            processBuilder.inheritIO();

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                LogsManager.error("Command failed with exitCode " + exitCode);
            } else {
                LogsManager.info("Command executed successfully!");
            }
        } catch (IOException | InterruptedException e) {
            LogsManager.error("Exception during command execution: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

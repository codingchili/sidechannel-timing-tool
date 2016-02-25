package com.rduda.View;

import com.rduda.Controller.Controller;
import com.rduda.Model.ModuleResult;
import com.rduda.Model.ModuleState;
import com.rduda.Model.TestModule;

import java.io.IOException;

/**
 * @author Robin Duda
 *         <p>
 *         Console-line interface.
 */
public class Console implements View {
    private LineHandler lines = new TextHandler();
    private Controller controller;
    private String target;
    private String password;
    private int iterations;
    private String[] suite;

    public Console(Controller controller, String[] args) {
        this.controller = controller;
        this.banner();


        if (args.length > 3) {
            try {
                this.setTarget(args[0])
                        .setPassword(args[1])
                        .setIterations(Integer.parseInt(args[2]))
                        .setSuite(getSuite(args))
                        .scan();
            } catch (NumberFormatException e) {
                writeHelp();
            }
        } else
            writeHelp();
    }

    private static void writeHelp() {
        System.out.println("SideTimer - Usage \r\n" +
                "\t <Target> <Password> <Iterations> <Module...>\r\n" +
                "\r\n" +
                "Available Modules\r\n" +
                "\t - LocalSafe\r\n" +
                "\t - LocalVulnerable\r\n" +
                "\t - Telnet\r\n\r\n" +
                "Example: java -jar file.jar 127.0.0.1 the_password Telnet LocalSafe\r\n\r\n");
    }

    private static String[] getSuite(String[] args) {
        String[] suite = new String[args.length - 3];
        System.arraycopy(args, 3, suite, 0, args.length - 3);
        return suite;
    }

    private void banner() {
        lines.write(
                "-----------------------------------------\r\n" +
                        " Side-channel (Timing) detection tool.\r\n" +
                        " Author: ~RD.\r\n" +
                        "\tVersion 1.0, released 25/02-16\r\n" +
                        "-----------------------------------------\r\n");
    }


    private Console setTarget(String target) {
        this.target = target;
        lines.writeln("Target = " + target);
        return this;
    }

    private Console setPassword(String password) {
        this.password = password;
        lines.writeln("Password = " + password + "\r\n");
        return this;
    }


    private Console setIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    private Console setSuite(String[] suite) {
        this.suite = suite;
        lines.writeln("Test suite loaded, ");

        for (String module : suite)
            lines.writeln("\t - " + module);

        lines.writeln("");

        return this;
    }

    private void scan() {
        controller.execute(target, password, iterations,

                (TestModule module, ModuleState state) -> {

                    switch (state) {
                        case CONNECTING:
                            lines.writeln(module.getName() + " connecting to host..");
                            break;
                        case CONNECTED:
                            lines.writeln(module.getName() + " running tests..");
                            break;
                        case DISCONNECT:
                            lines.writeln(module.getName() + " failed to connect..");
                            break;
                        case PROGRESS:
                            lines.write(".");
                            break;
                    }
                }, (ModuleResult result) -> {
                    try {
                        result.writeToFileCSV();
                        lines.writeln("\r\nresults saved to " + result.getName() + ".csv\r\n");
                    } catch (IOException e) {
                        lines.writeln("\r\nfailed to save results, no write access to " + result.getName() + ".csv");
                    }
                }, suite);
    }
}

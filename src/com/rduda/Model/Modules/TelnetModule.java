package com.rduda.Model.Modules;

import com.rduda.Model.TestModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Robin Duda
 *         <p>
 *         Implementation for telnet login protocol.
 */
public class TelnetModule implements TestModule {
    private final static String NAME = "Telnet";
    private BufferedReader reader;
    private Socket socket;
    private String target;

    public TelnetModule(String target, String password) {
        this.target = target;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean connect() {
        try {
            socket = new Socket(target, 23);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void disconnect() {
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public void send(String match) {
        try {
            for (int i = 0; i < 4; i++)
                reader.readLine();

            socket.getOutputStream().write((match + "\r\n").getBytes());
            reader.readLine();
        } catch (IOException ignored) {
        }
    }
}

package com.rduda.Model.Modules;

import com.rduda.Model.TestModule;

/**
 * Created by Robin on 2016-02-25.
 *
 * Local test module.
 */
public class LocalVulnerableModule implements TestModule {
    private String NAME = "Local-V";
    private String password;

    public LocalVulnerableModule(String target, String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void send(String match) {
        boolean equals = password.equals(match);

        if (equals)
            System.out.print("");
    }
}

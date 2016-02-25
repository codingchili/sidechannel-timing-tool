package com.rduda.Model.Modules;

import com.rduda.Model.TestModule;

/**
 * Created by Robin on 2016-02-25.
 *
 * Local test module.
 */
public class LocalSafeModule implements TestModule {
    private String NAME = "Local-S";
    private String password;

    public LocalSafeModule(String target, String password) {
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
       int equals = 0;

        for (int i = 0; i < match.length(); i++) {
            equals |= match.charAt(i) ^ password.charAt(i);
        }

        if (equals == 0)
            System.out.print("");
    }
}

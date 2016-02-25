package com.rduda.Model;

import com.rduda.Controller.ProgressHandler;
import com.rduda.Controller.ResultHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author Robin Duda
 *         <p>
 *         Executes the tests in a test suite.
 */
public class TestLoader {
    private ArrayList<TestModule> modules = new ArrayList<>();
    private ProgressHandler progress;
    private ResultHandler result;
    private String password;
    private int iterations;

    public TestLoader(String target, String password, int iterations, ProgressHandler progress, ResultHandler result, String... suite) {
        this.password = password;
        this.iterations = iterations;
        this.result = result;
        this.progress = progress;

        // Dynamic loading of modules.
        for (String name : suite) {
            try {
                Class<?> object = Class.forName("com.rduda.Model.Modules." + name + "Module");
                Constructor<?> constructor = object.getConstructor(String.class, String.class);
                TestModule instance = (TestModule) constructor.newInstance(target, password);
                modules.add(instance);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            }
        }
    }

    /**
     * Start all the tests in new threads.
     */
    public void start() {
        modules.forEach(this::fork);
    }

    private void fork(TestModule module) {
        ((Runnable) () -> {
            progress.update(module, ModuleState.CONNECTING);

            if (module.connect()) {
                progress.update(module, ModuleState.CONNECTED);

                result.set(measure(module, password));
            } else
                progress.update(module, ModuleState.DISCONNECT);

        }).run();
    }

    private ModuleResult measure(TestModule module, String password) {
        ModuleResult result = new ModuleResult(module, iterations);
        int bytes = 0;

        while (bytes <= password.length()) {
            String match = ByteMatcher.match(password, bytes);

            for (int i = 0; i < iterations; i++) {
                module.connect();
                long time = System.nanoTime();
                module.send(match);
                result.add(System.nanoTime() - time);
                module.disconnect();
            }

            progress.update(module, ModuleState.PROGRESS);
            bytes++;
        }
        return result;
    }
}

package com.rduda.Controller;

import com.rduda.Model.TestLoader;

/**
 * @author Robin Duda
 *         <p>
 *         Controller class.
 */
public class Handler implements Controller {

    @Override
    public void execute(String target, String password, int iterations, ProgressHandler progress, ResultHandler result, String... suite) {
        new TestLoader(target, password, iterations, progress, result, suite).start();
    }

}

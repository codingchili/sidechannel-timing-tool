package com.rduda.Controller;

/**
 * @author Robin Duda.
 *         <p>
 *         Controller interface.
 */
public interface Controller {

    /**
     * Runs the test against the given target.
     *
     * @param target     given target as IP address.
     * @param password   the correct password.
     * @param iterations number of times to run the simulation.
     * @param progress   handler for updating a modules test progress.
     * @param result     handler for the completion of a test module.
     * @param suite      contains list of modules to be used.
     */
    void execute(String target, String password, int iterations, ProgressHandler progress, ResultHandler result, String... suite);
}

package com.rduda.Model;

/**
 * @author Robin Duda
 *
 * Interface for the testing modules implementing underlying protocols.
 */
public interface TestModule {
    /**
     * Get the name of the test module.
     * @return the name.
     */
    String getName();

    /**
     * Determine if the module has connectivity.
     * @return boolean indicating connectivity.
     */
    boolean connect();

    void disconnect();

    /**
     * Sends a login query with defined password.
     * @param match string to match.
     */
    void send(String match);
}

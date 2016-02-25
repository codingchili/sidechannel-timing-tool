package com.rduda.Controller;

import com.rduda.Model.ModuleState;
import com.rduda.Model.TestModule;

/**
 * @author Robin Duda
 */
public interface ProgressHandler {
    /**
     * Updates the progress of a test module.
     * @param module to update the status for.
     * @param state the current state of the test.
     */
    void update(TestModule module, ModuleState state);
}

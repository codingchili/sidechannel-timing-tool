package com.rduda.Controller;

import com.rduda.Model.ModuleResult;

/**
 * @author Robin Duda
 */
public interface ResultHandler {
    /**
     * Sets the result of an operation that has completed.
     * @param result contains the result data.
     */
    void set(ModuleResult result);
}

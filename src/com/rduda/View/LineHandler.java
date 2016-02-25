package com.rduda.View;

/**
 * @author Robin Duda
 *
 * Handles lines of text.
 */
interface LineHandler {
    /**
     * Writes text to the interface.
     * @param data text to be written.
     * @return self as fluent.
     */
    LineHandler write(String data);

    /**
     * Writes a newline to the interface.
     * @return self as fluent.
     */
    LineHandler writeln(String data);

}

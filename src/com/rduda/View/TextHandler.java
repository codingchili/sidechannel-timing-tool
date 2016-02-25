package com.rduda.View;

/**
 * @author Robin Duda
 *
 * Handles text io.
 */
class TextHandler implements LineHandler {

    @Override
    public LineHandler write(String data) {
        System.out.print(data);
        return this;
    }

    @Override
    public LineHandler writeln(String data) {
        System.out.println(data);
        return this;
    }

}

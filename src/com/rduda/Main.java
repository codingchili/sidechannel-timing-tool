package com.rduda;

import com.rduda.Controller.Handler;
import com.rduda.View.Console;

/**
 * @author Robin Duda
 */

public class Main {

    public static void main(String[] args) {
        new Console(new Handler(), args);
    }
}

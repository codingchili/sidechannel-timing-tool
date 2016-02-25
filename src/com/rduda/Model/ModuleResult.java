package com.rduda.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

/**
 * @author Robin Duda
 *         <p>
 *         Contains the result of a module measurement.
 */
public class ModuleResult {
    private ArrayList<Long> measurements = new ArrayList<>();
    private int attempts;
    private String name;

    public ModuleResult(TestModule module, int attempts) {
        this.name = module.getName();
        this.attempts = attempts;
    }

    public void add(Long time) {
        measurements.add(time);
    }

    public void writeToFileCSV() throws IOException {
        File file = new File(name + ".csv");
        int items = 1;
        BufferedWriter output = new BufferedWriter(new FileWriter(file));

        for (Long data : measurements) {
            output.write(data.toString());

            if (items % attempts == 0)
                output.write("\r\n");
            else
                output.write(",");

            items++;
        }
        output.close();
    }

    public String getName() {
        return name;
    }
}

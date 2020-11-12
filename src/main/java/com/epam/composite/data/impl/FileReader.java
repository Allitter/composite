package com.epam.composite.data.impl;


import com.epam.composite.data.Reader;
import com.epam.composite.data.exception.DataException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader implements Reader {
    @Override
    public String read(String path) throws DataException {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            return String.join("\n", strings);
        } catch (IOException e) {
            throw new DataException(e);
        }
    }
}

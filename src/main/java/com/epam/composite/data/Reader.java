package com.epam.composite.data;

import com.epam.composite.data.exception.DataException;

public interface Reader {

     String read(String path) throws DataException;

}

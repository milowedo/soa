package com.agh.soa.daoInterfaces;

import com.agh.soa.entity.Reader;
import java.io.Serializable;
import java.util.List;

public interface IReaderDAO extends Serializable {
    void addReader(Reader reader);
    Reader getReaderByID(int id);
    List getAllReader();
}

package com.agh.soa.daoInterfaces;

import com.agh.soa.entity.Reader;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IReaderDAO extends Serializable {
    void addReader(Reader reader);
    Reader getReaderByID(long id);
    List getAllReader();

    List getByAuthorAndTimeStamp(String surname, Date left, Date right);

    List getByBookName(String name);
}

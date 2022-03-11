package com.oleh.database.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import com.oleh.database.model.Deal;

@RegisterBeanMapper(Deal.class)
public interface DealDao {

    @SqlQuery("Select * from deeals where id = ?")
    Deal findById(int id);
}

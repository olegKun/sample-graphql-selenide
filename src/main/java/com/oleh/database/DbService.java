package com.oleh.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.postgresql.ds.PGSimpleDataSource;
import com.oleh.database.dao.DealDao;
import com.oleh.database.model.Deal;

public class DbService {
    private final Jdbi jdbi;

    public DbService() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5433);
        dataSource.setDatabaseName("deals");
        dataSource.setUser("admin");
        dataSource.setPassword("admin");

        this.jdbi = Jdbi.create(dataSource);
        this.jdbi.installPlugin(new SqlObjectPlugin());
    }

    public Deal getDealById(int id){
        return jdbi.onDemand(DealDao.class).findById(id);
    }
}

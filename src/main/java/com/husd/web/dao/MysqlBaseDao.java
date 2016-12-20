package com.husd.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

@SuppressWarnings("deprecation")
public class MysqlBaseDao {

    @Autowired(required = false)
    protected SqlMapClientTemplate write = null;

    public void setWrite(SqlMapClientTemplate write) {
        this.write = write;
    }

}

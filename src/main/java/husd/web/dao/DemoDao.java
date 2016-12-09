package husd.web.dao;

import org.springframework.stereotype.Repository;

@SuppressWarnings({"deprecation", "unused"})
@Repository
public class DemoDao extends MysqlBaseDao {

    public int queryCount() {
        Integer count = (Integer) this.write.queryForObject("demoSqlMap.queryCount");
        if (count == null) {
            count = 0;
        }
        return count;
    }
}

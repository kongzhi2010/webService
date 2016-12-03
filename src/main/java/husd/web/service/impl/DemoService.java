package husd.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import husd.web.dao.DemoDao;
import husd.web.model.DemoCondition;
import husd.web.model.DemoResult;
import husd.web.model.Pager;
import husd.web.service.IDemoService;

/**
 * Created by hushengdong on 16/6/3.
 */
@Service
public class DemoService implements IDemoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private DemoDao demoDao;

    @Override
    public Pager<DemoResult> queryDemoResult(DemoCondition demoCondition) {
        // 1 先把总页数查询出来,然后做分页查询,注意total为0的情况.
//        int count = demoDao.queryCount();
//        LOGGER.info("test query count is :{}", count);
        int total = queryTotalCount(demoCondition);
        if (total == 0) {
            return new Pager<DemoResult>();
        }
        int start = demoCondition.getStart();
        int pageSize = demoCondition.getPageSize();
        Pager<DemoResult> pager = new Pager<DemoResult>(total, start, pageSize);
        // 2 获取到SQL中的两个参数start end ,实际生效:limit start,end
        demoCondition.setStart((pager.getPageNumber() - 1) * pager.getLimit());
        // 3 根据修改后的条件,查询分页数据
        List<DemoResult> data = queryTotalData(demoCondition);
        pager.setDataList(data);
        return pager;
    }

    private List<DemoResult> queryTotalData(DemoCondition demoCondition) {
        List<DemoResult> data = new ArrayList<DemoResult>();
        Random random = new Random(1);
        // 模拟数据
        for (int i = 0; i < demoCondition.getPageSize(); i++) {
            DemoResult d = new DemoResult();
            d.setBatchCode("2123332223");
            d.setProductId(i);
            d.setState(random.nextInt(2));
            d.setPerson("hushengdong");
            d.setTime("2016-12-10 12:00:00");
            data.add(d);
        }
        return data;
    }

    private int queryTotalCount(DemoCondition demoCondition) {
        return 100;
    }
}

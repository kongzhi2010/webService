package husd.web.service;

import husd.web.model.DemoCondition;
import husd.web.model.DemoResult;
import husd.web.model.Pager;

/**
 * Created by hushengdong on 16/6/3.
 */
public interface IDemoService {

    public Pager<DemoResult> queryDemoResult(DemoCondition demoCondition);

}

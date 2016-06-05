package husd.web.demo.service;

import husd.web.demo.model.DemoCondition;
import husd.web.demo.model.DemoResult;
import husd.web.demo.model.Pager;

/**
 * Created by hushengdong on 16/6/3.
 */
public interface IDemoService {

    public Pager<DemoResult> queryDemoResult(DemoCondition demoCondition);

}

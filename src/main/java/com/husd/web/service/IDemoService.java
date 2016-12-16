package com.husd.web.service;

import com.husd.web.model.DemoCondition;
import com.husd.web.model.DemoResult;
import com.husd.web.model.Pager;

/**
 * Created by hushengdong on 16/6/3.
 */
public interface IDemoService {

    public Pager<DemoResult> queryDemoResult(DemoCondition demoCondition);

}

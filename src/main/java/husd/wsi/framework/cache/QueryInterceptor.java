package husd.wsi.framework.cache;

import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

@Intercepts({@org.apache.ibatis.plugin.Signature(type=Executor.class, method="query", args={org.apache.ibatis.mapping.MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
public class QueryInterceptor
  implements Interceptor
{
  public Object intercept(Invocation invocation)
    throws Throwable
  {
    Executor target = (Executor)invocation.getTarget();
    target.clearLocalCache();
    return invocation.proceed();
  }
  
  public Object plugin(Object target)
  {
    return Plugin.wrap(target, this);
  }
  
  public void setProperties(Properties properties) {}
}
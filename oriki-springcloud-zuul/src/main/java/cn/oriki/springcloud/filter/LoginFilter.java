package cn.oriki.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    // 设置过滤器类型为：pre
    @Override
    public String filterType() {
        return "pre";
    }

    // 设置优先级为最高
    @Override
    public int filterOrder() {
        return 0;
    }

    // 设置需要被过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if ("1".equals(token)) {
            return null;
        }
        // 其余都不放行
        requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
        requestContext.setResponseStatusCode(401); // 设置响应状态码
        return null;
    }

}

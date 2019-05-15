package org.byron4j.servicezuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义Zuul过滤器
 */
@Slf4j
@Component
public class MyZuulFilter extends ZuulFilter {

    /**
     * 按类型对过滤器进行分类。
     * Zuul中的标准类型包括用于路由前过滤的“pre”、
     * 用于路由到源的“route”、用于路由后过滤器的“post”和用于错误处理的“error”
     * @return
     */
    @Override
    public String filterType() {
        // 我们使用前置过滤器，在请求前进行过滤
        return "pre";
    }

    /**
     * 还必须为过滤器定义filterOrder()。
     * 如果优先级对筛选器不重要，则筛选器可能具有相同的筛选顺序。过滤器的顺序不需要是顺序性的。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否调用run方法，返回true则会调用run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * shouldFilter() 方法返回true则必定调用该方法；
     * run方法是ZuulFilter的核心
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 这里对请求做一个token的简单的模拟，并返回响应码
        // RequestContext 对象持有请求、响应、状态信息和数据，以便zuulfilter访问和共享
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info(String.format("%s>>>%s", request.getMethod(), request.getRequestURL().toString()));

        // 模拟客户端传进来一个token
        String token = request.getParameter("token");
        if( null == token ){
            log.warn("token is empty!");
            // 不再次发送zuul响应
            currentContext.setSendZuulResponse(false);
            // 设置状态码
            currentContext.setResponseStatusCode(401);

            try {
                currentContext.getResponse().getWriter().write("your token is empty!");
            } catch (IOException e) {
                log.error("访问出错：", e);
            }
        }
        log.info("ok");

        return null;
    }
}

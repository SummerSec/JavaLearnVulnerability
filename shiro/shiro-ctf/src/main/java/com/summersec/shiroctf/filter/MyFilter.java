package com.summersec.shiroctf.filter;

/**
 * @ClassName: MyFilter
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 14:30
 * @Version: v1.0.0
 * @Description:
 **/

import java.io.IOException;
import java.lang.reflect.Proxy;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.summersec.shiroctf.Tools.AllFilter;
import com.summersec.shiroctf.Tools.IAllFilter;
import com.summersec.shiroctf.Tools.LogHandler;
import org.springframework.stereotype.Component;


@Component
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = new MyFilter.MyRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(request, servletResponse);
    }


    static class MyRequestWrapper extends HttpServletRequestWrapper {
        private static AllFilter allFilter = new AllFilter();
        private static LogHandler logHandler;
        private static IAllFilter iAllFilter;

        public MyRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getRequestURI() {
            return iAllFilter.filter(super.getRequestURI());
        }

        @Override
        public String getParameter(String name) {
            return iAllFilter.filter(super.getParameter(name));
        }

        static {
            logHandler = new LogHandler(allFilter);
            iAllFilter = (IAllFilter) Proxy.newProxyInstance(IAllFilter.class.getClassLoader(), new Class[]{IAllFilter.class}, logHandler);
        }
    }

}
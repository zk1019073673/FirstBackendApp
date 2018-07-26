package com.advantech.homework.filter;

import com.advantech.homework.authority.Audience;
import com.advantech.homework.authority.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    /**
     *  Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
         iss(Issuser)：代表这个JWT的签发主体；
         sub(Subject)：代表这个JWT的主体，即它的所有人；
         aud(Audience)：代表这个JWT的接收对象；
         exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
         nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
         iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
         jti(JWT ID)：是JWT的唯一标识。
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {
            if (authHeader == null || !authHeader.startsWith("bearer;")) {
                logger.error("===========================没有操作权限！=========================");
                return;
            }
            final String token = authHeader.substring(7);
            try {
                if(audience == null){
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                    audience = (Audience) factory.getBean("audience");
                }
                final Claims claims = JwtHelper.parseJWT(token,audience.getBase64Secret());
                if(claims == null){
                    logger.info("==================================用户信息为空！=================================");
                }
                request.setAttribute("HI", claims);
            } catch (final Exception e) {
                logger.error("====================================登陆异常！===============================");
            }
            chain.doFilter(req, res);
        }
    }
}

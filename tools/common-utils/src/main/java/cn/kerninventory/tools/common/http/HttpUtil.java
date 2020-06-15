package cn.kerninventory.tools.common.http;


import cn.kerninventory.tools.common.BeanUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>中文注释</h1>
 * <p>
 *     HTTP 工具类，待完善
 * </p>
 * @author yangaj
 * @version 1.0
 **/
public class HttpUtil {
    /**
     * <p>
     *     获取发送请求的客户端真实IP
     * </p>
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if (BeanUtil.nonEmpty(xFor) && !"unKnown".equals(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        if (BeanUtil.nonEmpty(xIp) && !"unKnown".equalsIgnoreCase(xIp)) {
            return xIp;
        }
        if (BeanUtil.isBlank(xIp) || "unknown".equalsIgnoreCase(xIp)) {
            xIp = request.getHeader("Proxy-Client-IP");
        }
        if (BeanUtil.isBlank(xIp) || "unknown".equalsIgnoreCase(xIp)) {
            xIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (BeanUtil.isBlank(xIp) || "unknown".equalsIgnoreCase(xIp)) {
            xIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (BeanUtil.isBlank(xIp) || "unknown".equalsIgnoreCase(xIp)) {
            xIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (BeanUtil.isBlank(xIp) || "unknown".equalsIgnoreCase(xIp)) {
            xIp = request.getRemoteAddr();
        }
        return xIp;
    }

}

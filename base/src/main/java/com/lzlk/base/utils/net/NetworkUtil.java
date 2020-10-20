package com.lzlk.base.utils.net;


import org.apache.commons.lang3.StringUtils;
import sun.net.util.IPAddressUtil;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 网格相关处理工具类
 * 
 * @Description
 * @author 邻座旅客
 * @date 2017年7月14日 下午3:06:21
 */
@SuppressWarnings("restriction")
public class NetworkUtil {

    public static String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，中第一个非unknown的有效IP字符串
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                String[] ipArray = ipAddress.split(",");
                for (String ip : ipArray) {
                    if (!"unknown".equalsIgnoreCase(ipAddress)) {
                        ipAddress = ip;
                        break;
                    }
                }
            }
        }
        return ipAddress;
    }

    /**
     * 获取来源域名
     * 
     * @param request
     * @return
     */
    public static String getRequestLyYm(HttpServletRequest request) {
        String lyYm = request.getHeader("Referer");
        if (StringUtils.isNotBlank(lyYm) && lyYm.indexOf("?") > 0) {
            return lyYm.substring(0, lyYm.indexOf("?"));
        }
        return lyYm;
    }

    /**
     * 获取用户浏览器类型
     * 
     * @param request
     * @return
     */
    public static String getRequestBrowserInfo(HttpServletRequest request) {
        String browserVersion = null;
        String header = request.getHeader("room-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        if (header.indexOf("MSIE") > 0) {
            browserVersion = "IE";
        } else if (header.indexOf("Firefox") > 0) {
            browserVersion = "Firefox";
        } else if (header.indexOf("Chrome") > 0) {
            browserVersion = "Chrome";
        } else if (header.indexOf("Safari") > 0) {
            browserVersion = "Safari";
        } else if (header.indexOf("Camino") > 0) {
            browserVersion = "Camino";
        } else if (header.indexOf("Konqueror") > 0) {
            browserVersion = "Konqueror";
        }
        return browserVersion;
    }

    /**
     * 获取请求操作系统
     * 
     * @param request
     * @return
     */
    public static String getRequestSystemInfo(HttpServletRequest request) {
        String systenInfo = null;
        String header = request.getHeader("room-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        header = header.toLowerCase();
        if (header.indexOf("win") > 0) {
            systenInfo = "Windows";
        } else if (header.indexOf("mac") > 0) {
            systenInfo = "Mac";
        } else if (header.indexOf("unix") > 0) {
            systenInfo = "UNIX";
        } else if (header.indexOf("linux") > 0) {
            systenInfo = "Linux";
        } else if (header.indexOf("sunos") > 0) {
            systenInfo = "SunOS";
        }
        return systenInfo;
    }

    /**
     * 判断该IP是否是内网IP
     * 
     * @param ip
     * @return
     */
    public static boolean internalIp(String ip) {
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
        case SECTION_1:
            return true;
        case SECTION_2:
            if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                return true;
            }
        case SECTION_5:
            switch (b1) {
            case SECTION_6:
                return true;
            }
        default:
            return false;

        }
    }

    /**
     * 获取本地MAC地址
     * 
     * @return
     * @throws SocketException
     */
    public static String getLocalMac() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                // 字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            return sb.toString().toUpperCase();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前tomcat配置的端口<br>
     * 1：包含http跟https端口
     * 
     * @return
     */
    public static Set<String> getTomcatPort() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            Set<String> setPort = new HashSet<String>();
            for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
                ObjectName obj = i.next();
                String port = obj.getKeyProperty("port");
                if (StringUtils.isNotBlank(port)) {
                    setPort.add(port);
                }
            }
            return setPort;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前tomcat特征<br>
     * 1:特征组成规则：MAC:Port
     * 
     * @return
     */
    public static Set<String> getTomcatIdentification() {
        Set<String> setIdentification = new HashSet<String>();
        String mac = getLocalMac();
        if (StringUtils.isBlank(mac)) {
            return null;
        }
        Set<String> setPort = getTomcatPort();
        if (setPort == null || setPort.size() == 0) {
            return null;
        }

        for (String port : setPort) {
            setIdentification.add(mac + ":" + port);
        }
        return setIdentification;
    }

}

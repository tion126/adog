package vip.adog.common;


import vip.adog.utils.token.TokenManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

public abstract class BaseController {
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String urlPara;
    private String[] urlParaArray;

    private static final String[] NULL_URL_PARA_ARRAY = new String[0];

    public void init(HttpServletRequest request, HttpServletResponse response, String urlPara) {
        this.request = request;
        this.response = response;
        this.urlPara = urlPara;
    }
    public void init(HttpServletRequest request) {
        this.request = request;
    }

    public void setUrlPara(String urlPara) {
        this.urlPara = urlPara;
        this.urlParaArray = null;
    }

    /**
     * Stores an attribute in this request
     * @param name a String specifying the name of the attribute
     * @param value the Object to be stored
     */
    public BaseController setAttr(String name, Object value) {
        request.setAttribute(name, value);
        return this;
    }

    /**
     * Removes an attribute from this request
     * @param name a String specifying the name of the attribute to remove
     */
    public BaseController removeAttr(String name) {
        request.removeAttribute(name);
        return this;
    }

    /**
     * Stores attributes in this request, key of the map as attribute name and value of the map as attribute value
     * @param attrMap key and value as attribute of the map to be stored
     */
    public BaseController setAttrs(Map<String, Object> attrMap) {
        for (Map.Entry<String, Object> entry : attrMap.entrySet())
            request.setAttribute(entry.getKey(), entry.getValue());
        return this;
    }

    /**
     * Returns the value of a request parameter as a String, or null if the parameter does not exist.
     * <p>
     * You should only use this method when you are sure the parameter has only one value. If the
     * parameter might have more than one value, use getParaValues(java.lang.String).
     * <p>
     * If you use this method with a multivalued parameter, the value returned is equal to the first
     * value in the array returned by getParameterValues.
     * @param name a String specifying the name of the parameter
     * @return a String representing the single value of the parameter
     */
    public String getPara(String name) {
        return request.getParameter(name);
    }

    /**
     * Returns the value of a request parameter as a String, or default value if the parameter does not exist.
     * @param name a String specifying the name of the parameter
     * @param defaultValue a String value be returned when the value of parameter is null
     * @return a String representing the single value of the parameter
     */
    public String getPara(String name, String defaultValue) {
        String result = request.getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    /**
     * Returns the values of the request parameters as a Map.
     * @return a Map contains all the parameters name and value
     */
    public Map<String, String[]> getParaMap() {
        return request.getParameterMap();
    }

    /**
     * Returns an Enumeration of String objects containing the names of the parameters
     * contained in this request. If the request has no parameters, the method returns
     * an empty Enumeration.
     * @return an Enumeration of String objects, each String containing the name of
     * 			a request parameter; or an empty Enumeration if the request has no parameters
     */
    public Enumeration<String> getParaNames() {
        return request.getParameterNames();
    }

    /**
     * Returns an array of String objects containing all of the values the given request
     * parameter has, or null if the parameter does not exist. If the parameter has a
     * single value, the array has a length of 1.
     * @param name a String containing the name of the parameter whose value is requested
     * @return an array of String objects containing the parameter's values
     */
    public String[] getParaValues(String name) {
        return request.getParameterValues(name);
    }

    /**
     * Returns an array of Integer objects containing all of the values the given request
     * parameter has, or null if the parameter does not exist. If the parameter has a
     * single value, the array has a length of 1.
     * @param name a String containing the name of the parameter whose value is requested
     * @return an array of Integer objects containing the parameter's values
     */
    public Integer[] getParaValuesToInt(String name) {
        String[] values = request.getParameterValues(name);
        if (values == null)
            return null;
        Integer[] result = new Integer[values.length];
        for (int i = 0; i < result.length; i++)
            result[i] = Integer.parseInt(values[i]);
        return result;
    }

    public Long[] getParaValuesToLong(String name) {
        String[] values = request.getParameterValues(name);
        if (values == null)
            return null;
        Long[] result = new Long[values.length];
        for (int i = 0; i < result.length; i++)
            result[i] = Long.parseLong(values[i]);
        return result;
    }

    /**
     * Returns an Enumeration containing the names of the attributes available to this request.
     * This method returns an empty Enumeration if the request has no attributes available to it.
     * @return an Enumeration of strings containing the names of the request's attributes
     */
    public Enumeration<String> getAttrNames() {
        return request.getAttributeNames();
    }

    /**
     * Returns the value of the named attribute as an Object, or null if no attribute of the given name exists.
     * @param name a String specifying the name of the attribute
     * @return an Object containing the value of the attribute, or null if the attribute does not exist
     */
    public <T> T getAttr(String name) {
        return (T) request.getAttribute(name);
    }

    /**
     * Returns the value of the named attribute as an Object, or null if no attribute of the given name exists.
     * @param name a String specifying the name of the attribute
     * @return an String Object containing the value of the attribute, or null if the attribute does not exist
     */
    public String getAttrForStr(String name) {
        return (String) request.getAttribute(name);
    }

    /**
     * Returns the value of the named attribute as an Object, or null if no attribute of the given name exists.
     * @param name a String specifying the name of the attribute
     * @return an Integer Object containing the value of the attribute, or null if the attribute does not exist
     */
    public Integer getAttrForInt(String name) {
        return (Integer) request.getAttribute(name);
    }

    private Integer toInt(String value, Integer defaultValue) {
        try {
            if (value == null || "".equals(value.trim()))
                return defaultValue;
            value = value.trim();
            if (value.startsWith("N") || value.startsWith("n"))
                return -Integer.parseInt(value.substring(1));
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println("Can not parse the parameter \"" + value + "\" to Integer value.");
        }
        return defaultValue;
    }

    /**
     * Returns the value of a request parameter and convert to Integer.
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Integer getParaToInt(String name) {
        return toInt(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Integer with a default value if it is null.
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Integer getParaToInt(String name, Integer defaultValue) {
        return toInt(request.getParameter(name), defaultValue);
    }

    private Long toLong(String value, Long defaultValue) {
        try {
            if (value == null || "".equals(value.trim()))
                return defaultValue;
            value = value.trim();
            if (value.startsWith("N") || value.startsWith("n"))
                return -Long.parseLong(value.substring(1));
            return Long.parseLong(value);
        } catch (Exception e) {
            System.out.println("Can not parse the parameter \"" + value + "\" to Long value.");
        }
        return defaultValue;
    }

    /**
     * Returns the value of a request parameter and convert to Long.
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Long getParaToLong(String name) {
        return toLong(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Long with a default value if it is null.
     * @param name a String specifying the name of the parameter
     * @return a Integer representing the single value of the parameter
     */
    public Long getParaToLong(String name, Long defaultValue) {
        return toLong(request.getParameter(name), defaultValue);
    }

    private Boolean toBoolean(String value, Boolean defaultValue) {
        if (value == null || "".equals(value.trim()))
            return defaultValue;
        value = value.trim().toLowerCase();
        if ("1".equals(value) || "true".equals(value))
            return Boolean.TRUE;
        else if ("0".equals(value) || "false".equals(value))
            return Boolean.FALSE;
        System.out.println("Can not parse the parameter \"" + value + "\" to Boolean value.");
        return defaultValue;
    }

    /**
     * Returns the value of a request parameter and convert to Boolean.
     * @param name a String specifying the name of the parameter
     * @return true if the value of the parameter is "true" or "1", false if it is "false" or "0", null if parameter is not exists
     */
    public Boolean getParaToBoolean(String name) {
        return toBoolean(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Boolean with a default value if it is null.
     * @param name a String specifying the name of the parameter
     * @return true if the value of the parameter is "true" or "1", false if it is "false" or "0", default value if it is null
     */
    public Boolean getParaToBoolean(String name, Boolean defaultValue) {
        return toBoolean(request.getParameter(name), defaultValue);
    }

    /**
     * Get all para from url and convert to Boolean
     */
    public Boolean getParaToBoolean() {
        return toBoolean(getPara(), null);
    }


    private Date toDate(String value, Date defaultValue) {
        try {
            if (value == null || "".equals(value.trim()))
                return defaultValue;
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value.trim());
        } catch (Exception e) {
            System.out.println("Can not parse the parameter \"" + value + "\" to Date value.");
        }
        return defaultValue;
    }

    /**
     * Returns the value of a request parameter and convert to Date.
     * @param name a String specifying the name of the parameter
     * @return a Date representing the single value of the parameter
     */
    public Date getParaToDate(String name) {
        return toDate(request.getParameter(name), null);
    }

    /**
     * Returns the value of a request parameter and convert to Date with a default value if it is null.
     * @param name a String specifying the name of the parameter
     * @return a Date representing the single value of the parameter
     */
    public Date getParaToDate(String name, Date defaultValue) {
        return toDate(request.getParameter(name), defaultValue);
    }

    /**
     * Get all para from url and convert to Date
     */
    public Date getParaToDate() {
        return toDate(getPara(), null);
    }

    /**
     * Return HttpServletRequest. Do not use HttpServletRequest Object in constructor of BaseController
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Return HttpServletResponse. Do not use HttpServletResponse Object in constructor of BaseController
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Return HttpSession.
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * Return HttpSession.
     * @param create a boolean specifying create HttpSession if it not exists
     */
    public HttpSession getSession(boolean create) {
        return request.getSession(create);
    }

    /**
     * Return a Object from session.
     * @param key a String specifying the key of the Object stored in session
     */
    public <T> T getSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * Store Object to session.
     * @param key a String specifying the key of the Object stored in session
     * @param value a Object specifying the value stored in session
     */
    public BaseController setSessionAttr(String key, Object value) {
        request.getSession().setAttribute(key, value);
        return this;
    }

    /**
     * Remove Object in session.
     * @param key a String specifying the key of the Object stored in session
     */
    public BaseController removeSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.removeAttribute(key);
        return this;
    }

    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name) {
        return getCookie(name, null);
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     */
    public Cookie getCookieObject(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * Get all cookie objects.
     */
    public Cookie[] getCookieObjects() {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    /**
     * Set Cookie to response.
     */
    public BaseController setCookie(Cookie cookie) {
        response.addCookie(cookie);
        return this;
    }

    /**
     * Set Cookie to response.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path see Cookie.setPath(String)
     */
    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path) {
        setCookie(name, value, maxAgeInSeconds, path, null);
        return this;
    }

    /**
     * Set Cookie to response.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path see Cookie.setPath(String)
     * @param domain the domain name within which this cookie is visible; form is according to RFC 2109
     */
    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (domain != null)
            cookie.setDomain(domain);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath(path);
        response.addCookie(cookie);
        return this;
    }

    /**
     * Set Cookie with path = "/".
     */
    public BaseController setCookie(String name, String value, int maxAgeInSeconds) {
        setCookie(name, value, maxAgeInSeconds, "/", null);
        return this;
    }

    /**
     * Remove Cookie with path = "/".
     */
    public BaseController removeCookie(String name) {
        setCookie(name, null, 0, "/", null);
        return this;
    }

    /**
     * Remove Cookie.
     */
    public BaseController removeCookie(String name, String path) {
        setCookie(name, null, 0, path, null);
        return this;
    }

    /**
     * Remove Cookie.
     */
    public BaseController removeCookie(String name, String path, String domain) {
        setCookie(name, null, 0, path, domain);
        return this;
    }

    // --------

    /**
     * Get all para with separator char from url
     */
    public String getPara() {
        if ("".equals(urlPara))    // urlPara maybe is "" see ActionMapping.getAction(String)
            urlPara = null;
        return urlPara;
    }


    /**
     * Get all para from url and convert to Integer
     */
    public Integer getParaToInt() {
        return toInt(getPara(), null);
    }

    /**
     * Get all para from url and convert to Long
     */
    public Long getParaToLong() {
        return toLong(getPara(), null);
    }


    // TODO public <T> List<T> getModels(Class<T> modelClass, String modelName) {}

    // --------

    /**
     * Keep all parameter's value except model value
     */
    public BaseController keepPara() {
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            String[] values = e.getValue();
            if (values.length == 1)
                request.setAttribute(e.getKey(), values[0]);
            else
                request.setAttribute(e.getKey(), values);
        }
        return this;
    }

    /**
     * Keep parameter's value names pointed, model value can not be kept
     */
    public BaseController keepPara(String... names) {
        for (String name : names) {
            String[] values = request.getParameterValues(name);
            if (values != null) {
                if (values.length == 1)
                    request.setAttribute(name, values[0]);
                else
                    request.setAttribute(name, values);
            }
        }
        return this;
    }


    public BaseController keepPara(Class type, String... names) {
        if (type == String.class)
            return keepPara(names);

        if (names != null)
            for (String name : names)
                keepPara(type, name);
        return this;
    }

    /**
     * Create a token.
     * @param tokenName the token name used in view
     * @param secondsOfTimeOut the seconds of time out, secondsOfTimeOut >= Const.MIN_SECONDS_OF_TOKEN_TIME_OUT
     */
    public void createToken(String tokenName, int secondsOfTimeOut) {
        TokenManager.createToken(this, tokenName, secondsOfTimeOut);
    }

    /**
     * Create a token with default token name and with default seconds of time out.
     */
    public void createToken() {
        createToken(Constant.DEFAULT_TOKEN_NAME, Constant.DEFAULT_SECONDS_OF_TOKEN_TIME_OUT);
    }

    /**
     * Create a token with default seconds of time out.
     * @param tokenName the token name used in view
     */
    public void createToken(String tokenName) {
        createToken(tokenName, Constant.DEFAULT_SECONDS_OF_TOKEN_TIME_OUT);
    }

    /**
     * Check token to prevent resubmit.
     * @param tokenName the token name used in view's form
     * @return true if token is correct
     */
    public boolean validateToken(String tokenName) {
        return TokenManager.validateToken(this, tokenName);
    }

    /**
     * Check token to prevent resubmit  with default token key ---> "JFINAL_TOKEN_KEY"
     * @return true if token is correct
     */
    public boolean validateToken() {
        return validateToken(Constant.DEFAULT_TOKEN_NAME);
    }

    /**
     * Return true if the para value is blank otherwise return false
     */
    public boolean isParaBlank(String paraName) {
        String value = request.getParameter(paraName);
        return value == null || value.trim().length() == 0;
    }


}

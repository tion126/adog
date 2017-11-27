package vip.adog.common;

/**
 * Created by jekay on 17-11-26.
 */
public class JsonMsg {
    private Object data; //返回数据
    private int status = 0;  //返回状态码
    private String msg = "操作成功";  //返回提示信息

    /**
    * @Description: 设置状态码、信息
    * @author: Jekay
    * @date: 2017-11-27 10:13
    **/
    public JsonMsg setMsgStatus(String msg,int status){
        this.msg = msg;
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.ss.model;

import org.apache.ibatis.annotations.ResultMap;

import java.util.HashMap;

public class ResultModel extends HashMap<String,Object> {

    /////////////////////// 默认的键 ///////////////////////
    public static final String KEY_OPER = "oper";
    public static final String KEY_SUCC = "succ";
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA = "data";
    /////////////////////// 默认的值 ///////////////////////
    public static final String DEFAULT_OPER_VAL = "default";
    public static final int DEFAULT_SUCC_CODE = 1;
    public static final int DEFAULT_FAIL_CODE = -1;
    public static final String DEFAULT_SUCC_MSG = "ok";
    public static final String DEFAULT_FAIL_MSG = "fail";

    /////////////////////// 最常用的两个构造函数 ///////////////////////

    /**
     * 操作成功的返回信息
     */
    public ResultModel(){
        this.put(KEY_OPER,DEFAULT_OPER_VAL);
        this.put(KEY_SUCC,true);
        this.put(KEY_CODE,DEFAULT_SUCC_CODE);
        this.put(KEY_MSG,DEFAULT_SUCC_MSG);
    }

    /**
     * 返回操作类型对应的信息
     * @param oper
     */
    public ResultModel(String oper){
        this.put(KEY_OPER,oper);
        this.put(KEY_SUCC,true);
        this.put(KEY_CODE,DEFAULT_SUCC_MSG);
        this.put(KEY_MSG,DEFAULT_SUCC_MSG);
    }
    /////////////////////// 全餐构造函数 ///////////////////////
    public ResultModel(String oper,Boolean success,Integer code,String msg,Object data){
        this.put(KEY_OPER,oper);
        this.put(KEY_SUCC,success);
        this.put(KEY_CODE,code);
        this.put(KEY_MSG,msg);
        if(data != null) this.put(KEY_DATA,data);
    }
    /////////////////// 返回操作成功的方法 ///////////////////
    public ResultModel succ(){
        return new ResultModel();
    }
    public ResultModel succ(String oper){
        return new ResultModel(oper,true,DEFAULT_SUCC_CODE,DEFAULT_SUCC_MSG,null);
    }
    public ResultModel succ(String oper , String msg){
        return new ResultModel(oper,true,DEFAULT_SUCC_CODE,msg,null);
    }
    public ResultModel succ(String oper,Object data){
        return new ResultModel(oper,true,DEFAULT_SUCC_CODE,DEFAULT_SUCC_MSG,data);
    }
    public ResultModel succ(String oper,String dataKey,Object dataVal){
        return new ResultModel(oper,true,DEFAULT_SUCC_CODE,DEFAULT_SUCC_MSG,null).data(dataKey,dataVal);
    }
    /////////////////// 返回操作失败的方法 ///////////////////
    public ResultModel fail(){
        return new ResultModel(DEFAULT_OPER_VAL,false,DEFAULT_FAIL_CODE,DEFAULT_FAIL_MSG,null);
    }
    public ResultModel fail(String oper){
        return new ResultModel(oper,false,DEFAULT_FAIL_CODE,DEFAULT_FAIL_MSG,null);
    }
    public ResultModel fail(String oper,String msg){
        return new ResultModel(oper,false,DEFAULT_FAIL_CODE,msg,null);
    }
    public ResultModel faile(String oper,Object data){
        return new ResultModel(oper,false,DEFAULT_FAIL_CODE,DEFAULT_FAIL_MSG,data);
    }
    public ResultModel failt(String oper,String dataKey,Object dataValue){
        return new ResultModel(oper,false,DEFAULT_FAIL_CODE,DEFAULT_FAIL_MSG,null).data(dataKey,dataValue);
    }
    /**
     * 设置返回的状态码
     */
    public ResultModel code(String code){
        this.put(KEY_CODE,code);
        return this;
    }
    /**
     * 设置返回的消息
     */
    public ResultModel msg(String msg){
        this.put(KEY_MSG,msg);
        return this;
    }

    /**
     * 设置返回的数据
     */
    public ResultModel data(String dataVal){
        this.put(KEY_DATA,dataVal);
        return this;
    }
    /**
     * 自定义返回的数据的key的方式设置返回的数据
     */
    public ResultModel data(String dataKey,Object dataVal){
        this.put(dataKey,dataVal);
        return this;
    }
}

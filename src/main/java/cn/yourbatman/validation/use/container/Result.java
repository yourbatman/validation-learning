package cn.yourbatman.validation.use.container;

import lombok.Data;

import java.io.Serializable;

@Data
public final class Result<T> implements Serializable {

    private boolean success = true;
    private T data = null;

    private String errCode;
    private String errMsg;

}
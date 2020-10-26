package cn.yourbatman.validation.use.container;

import lombok.Data;

import javax.validation.Valid;

@Data
public class ResultDemo {

    @Valid
    public Result<@Valid Room> roomResult;
}

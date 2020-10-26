package cn.yourbatman.validation.use.property;

import cn.yourbatman.validation.ValidatorUtil;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * 在此处添加备注信息
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/10/23 12:16
 */
public class Room {

    public String name;
    public boolean finished;

    public static void main(String[] args) {
        Room bean = new Room();
        bean.finished = false;
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(bean));
    }

    @NotNull
    public String getName() {
        return name;
    }

    @AssertTrue
    public boolean isFinished() {
        return finished;
    }
}

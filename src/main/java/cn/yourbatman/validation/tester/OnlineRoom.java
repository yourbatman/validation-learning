package cn.yourbatman.validation.tester;

import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 在此处添加备注信息
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/9/7 19:54
 */
@Setter
public class OnlineRoom implements Online {

    @Override
    public @NotNull @Min(1) Integer getOnlineNum() {
        return 0;
    }

    @Override
    public @Positive CharSequence takeClass(Number classNum) {
        return "hello world";
    }
}

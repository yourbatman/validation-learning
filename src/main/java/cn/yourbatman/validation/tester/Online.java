package cn.yourbatman.validation.tester;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 在此处添加备注信息
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/9/7 19:55
 */
public interface Online {

    @NotNull
    Integer getOnlineNum();

    @NotNull CharSequence takeClass(@NotNull @Min(10) Number classNum);

}

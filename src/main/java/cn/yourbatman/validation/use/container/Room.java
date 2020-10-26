package cn.yourbatman.validation.use.container;

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

    @NotNull
    public String name;
    @AssertTrue
    public boolean finished;

    // public static void main(String[] args) {
    //     @Valid List<@Valid @NotNull Room> beans = new ArrayList<>();
    //     beans.add(null);
    //     beans.add(new Room());
    //
    //     Room room = new Room();
    //     room.name = "YourBatman";
    //     beans.add(room);
    //
    //     ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(beans));
    // }

}

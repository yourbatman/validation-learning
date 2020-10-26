package cn.yourbatman.validation.use.container;

import cn.yourbatman.validation.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 在此处添加备注信息
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/10/23 14:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {

    // @Valid
    // private List<@NotNull Room> rooms;
    private List<@Valid @NotNull Room> rooms;

    public static void main(String[] args) {
        List<Room> beans = new ArrayList<>();
        beans.add(null);
        beans.add(new Room());
        beans.add(new Room());

        // Room room = new Room();
        // room.name = "YourBatman";
        // beans.add(room);

        // 必须基于Java Bean，验证才会生效
        Rooms rooms = new Rooms(beans);
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(rooms));
    }

}

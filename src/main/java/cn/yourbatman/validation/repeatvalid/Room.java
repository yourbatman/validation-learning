package cn.yourbatman.validation.repeatvalid;

import cn.yourbatman.validation.ValidatorUtil;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 * 在此处添加备注信息
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/10/25 22:22
 */
@Data
public class Room {

    @NotNull
    private String name;
    // @Valid
    // @NotNull
    // private Room parentRoom;
    // @Valid
    // private List<@Valid Room> rooms;

    @Valid
    private Result<@Valid @NotNull Room> roomResult;

    public static void main(String[] args) {
        Room room = new Room();
        // room.setParentRoom(new Room());
        // room.setRooms(Collections.singletonList(new Room()));
        Result<Room> roomResult = new Result<>();
        roomResult.setData(new Room());
        room.setRoomResult(roomResult);
        room.setName("YourBatman");

        // 注册自定义的值提取器
        Validator validator = ValidatorUtil.obtainValidatorFactory()
                .usingContext()
                .addValueExtractor(new ResultValueExtractor())
                .getValidator();
        ValidatorUtil.printViolations(validator.validate(room));
    }

}

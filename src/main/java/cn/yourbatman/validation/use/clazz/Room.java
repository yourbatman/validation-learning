package cn.yourbatman.validation.use.clazz;

import cn.yourbatman.validation.ValidatorUtil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.List;

/**
 * 在此处添加备注信息
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/10/23 12:16
 */
@ValidStudentCount
// @ScriptAssert(lang = "javascript", alias = "_", script = "_.maxStuNum >= _.studentNames.length")
@Data
public class Room {
    @Positive
    private int maxStuNum;
    @NotNull
    private List<String> studentNames;

    public static void main(String[] args) {
        Room room = new Room();
        room.setStudentNames(Collections.singletonList("YourBatman"));

        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(room));
    }

}

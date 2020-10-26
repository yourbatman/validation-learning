package cn.yourbatman.validation.tester;

import lombok.Data;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@ScriptAssert(lang = "javascript", script = "_.maxStuNum >= _.students.length", alias = "_")
@Data
public class Room {

    // 学生
    public Integer maxStuNum;
    private String name;
    @NotNull
    @Min(1)
    private Integer level;
    @NotNull
    private Map<@NotNull String, @Min(1) Integer> courseMap;
    private List<Student> students;

    @NotNull
    public String getName() {
        return name;
    }

    @ParameterScriptAssert(lang = "javascript", script = "arg0 >= arg1.size()")
    public void load(Integer maxStuNum, List<Student> students) {

    }

}


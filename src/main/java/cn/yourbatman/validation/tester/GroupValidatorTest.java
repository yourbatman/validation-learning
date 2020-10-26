package cn.yourbatman.validation.tester;

import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.GroupSequence;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Set;


/**
 * 分组约束：
 * 1、如果不指定，都属于Default默认组，校验的也只是默认组
 * 2、组是可以继承的。比如Create就继承了Update组，也就属于这个主啦
 * 注意：若你们都不继承Default组的话，消息后果哦，所以适时的继承Default组是个不错的做法
 * 3、默认情况下，多个组之间的校验是无需的。若你需要顺序，可以使用@GroupSequence来排序（请避免循环依赖），排序好后有快速失败的效果，以一个组表示一个单位
 * 4、@GroupSequenceProvider 可认为是Hibernate提供的加强版：当顺序需要逻辑控制时，使用它更为动态化（@GroupSequence属于静态的嘛）用于根据对象状态动态重新定义默认组序列。
 */

interface Create extends Update {
}

interface Update {
}

@GroupSequence({Create.class, Default.class, Update.class})
interface OrderedChecks {
}


/**
 * 在此处添加备注信息
 *
 * @author yourbatman
 * @site https://www.yourbatman.cn
 * @date 2020/9/8 9:25
 */
public class GroupValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void test1() {
        Person rootBean = new Person();
        rootBean.setId(1L);

        Set<ConstraintViolation<Person>> violations = validator.validate(rootBean);
        printViolations(violations);
    }

    @Test
    public void test2() {
        Person rootBean = new Person();

        Set<ConstraintViolation<Person>> violations = validator.validate(rootBean);
        printViolations(violations);
    }


    private <T> void printViolations(Set<ConstraintViolation<T>> violations) {
        if (violations.isEmpty()) {
            System.out.println("无校验失败项");
        }
        violations.stream().map(v -> v.getPropertyPath() + v.getMessage() + ",非法值为" + v.getInvalidValue()).forEach(System.out::println);
    }
}

@GroupSequence({Update.class, Person.class})
@Data
class Person {

    @NotNull(groups = Update.class)
    @Min(value = 0, groups = Update.class)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer age;
}

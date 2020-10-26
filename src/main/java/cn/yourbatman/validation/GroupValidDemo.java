package cn.yourbatman.validation;

import lombok.Data;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.util.Set;


public class GroupValidDemo {

    /**
     * 分组校验
     */
    @Test
    public void test1() {
        Validator validator = obtainValidator();

        User user = new User();
        Set<ConstraintViolation<User>> result = validator.validate(user);

        // 输出校验结果
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }

    private Validator obtainValidator() {
        // 1、使用【默认配置】得到一个校验工厂  这个配置可以来自于provider、SPI提供
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        // 2、得到一个校验器
        return validatorFactory.getValidator();
    }

    private ExecutableValidator obtainExecutableValidator() {
        return obtainValidator().forExecutables();
    }

}

@Data
class User {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer age;
}

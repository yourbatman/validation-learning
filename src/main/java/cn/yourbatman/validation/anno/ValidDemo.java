package cn.yourbatman.validation.anno;

import cn.yourbatman.validation.ValidatorUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidDemo {

    @Test
    public void test1() {
        Validator validator = ValidatorUtil.obtainValidator();

        User user = new User();
        user.setAge(2);

        Set<ConstraintViolation<User>> validate = validator.validate(user);
        ValidatorUtil.printViolations(validate);
    }
}


@Data
class User {

    @MyMultiple(base = 3)
    private Integer age;

}
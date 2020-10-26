package cn.yourbatman.validation;

import cn.yourbatman.validation.bean.User;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ConstraintViolationDemo {

    @Test
    public void test1() {
        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validateValue(User.class, "fullName", "A哥");

        for (ConstraintViolation<User> violation : result) {
            User rootBean = violation.getRootBean();
            System.out.println(violation.getPropertyPath());
            System.out.println(violation.getConstraintDescriptor());
            System.out.println(rootBean);
        }

    }
}

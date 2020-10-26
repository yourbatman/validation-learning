package cn.yourbatman.validation.constraintvalidatorcontext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Map;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class MyParameterAnnoConstraintValidator implements ConstraintValidator<MyParameterAnno, Object[]> {

    /**
     * 因为是多个参数，所以这里使用Object[]接收
     */
    @Override
    public boolean isValid(Object[] parameters, ConstraintValidatorContext context) {
        System.out.println(parameters);

        context.disableDefaultConstraintViolation();


        // 方法上的跨参数约束，如createUser(String password, String passwordRepeat)方法
        // 要求两个参数的值一模一样的约束，则路径为default path + "passwordRepeat"
        // context.buildConstraintViolationWithTemplate("Passwords do not match")
        //         .addParameterNode(1)
        //         .addConstraintViolation();
        //
        // 方法上的跨参数约束，如mergeAddresses(Map<String,Address> addresses,Map<String,Address> otherAddresses)
        // default path + "otherAddresses["home"]
        // 两个Map里都有key为home的键值对，但值不相同
        context.buildConstraintViolationWithTemplate(
                "Map entry home present in both but does not match")
                .addParameterNode(1)
                .addBeanNode()
                .inContainer(Map.class, 1)
                .inIterable().atKey("home")
                .addConstraintViolation();
        //
        // // mergeAddresses(Map<String,Address> addresses,Map<String,Address> otherAddresses)
        // //path + "otherAddresses["home"].city
        // // 两个Map里都有key为home的键值对，但Map里的city不匹配
        // context.buildConstraintViolationWithTemplate(
        //         "Map entry home present in both but city does not match")
        //         .addParameterNode(1)
        //         .addPropertyNode("city")
        //         .inContainer(Map.class, 1)
        //         .inIterable().atKey("home")
        //         .addConstraintViolation();
        return false;
    }
}

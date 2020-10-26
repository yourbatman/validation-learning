package cn.yourbatman.validation.anno;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyMultipleConstraintValidator implements ConstraintValidator<MyMultiple, Integer> {

    private int base;

    @Override
    public void initialize(MyMultiple anno) {
        // 获得一些注解里的参数
        base = anno.base();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean b = value % base == 0;
        if (!b) {
            // context.disableDefaultConstraintViolation();


            // ===========校验User============

            // 从属性User.addresses出发，addresses属性约束消息。path为默认
            context.buildConstraintViolationWithTemplate("User.addresses not valid")
                    .addConstraintViolation();
            //
            // // 从类Address出发，Address的street属性约束消息。path为默认 + "street"
            // context.buildConstraintViolationWithTemplate("Address street not valid")
            //         .addPropertyNode("street")
            //         .addConstraintViolation();
            //
            // // 从属性User.addresses出发，key为'home'的约束消息。path为默认 + the bean stored
            // context.buildConstraintViolationWithTemplate("Incorrect home address")
            //         .addBeanNode()
            //         .inContainer(Map.class, 1)
            //         .inIterable().atKey("home")
            //         .addConstraintViolation();
            //
            // // 从类User出发，key为'home'的Address里面的country.name属性的约束消息。path为默认 + addresses["home"].country.name
            // context.buildConstraintViolationWithTemplate("this detail is wrong")
            //         .addPropertyNode("addresses")
            //         .addPropertyNode("country")
            //         .inContainer(Map.class, 1)
            //         .inIterable().atKey("home")
            //         .addPropertyNode("name")
            //         .addConstraintViolation();
            //
            // // 从类User出发，addresses里面的非法key
            // context.buildConstraintViolationWithTemplate("the map key is invalid")
            //         .addPropertyNode("addresses")
            //         .addContainerElementNode("<map key>", Map.class, 0)
            //         .inIterable().atKey("invalid")
            //         .addConstraintViolation();

            // 如果需要，方法上的交叉参数约束可以创建特定于特定参数的节点

            // // 方法上的跨参数约束，如createUser(String password, String passwordRepeat)方法
            // // 要求两个参数的值一模一样的约束，则路径为default path + "passwordRepeat"
            // context.buildConstraintViolationWithTemplate("Passwords do not match")
            //         .addParameterNode(1)
            //         .addConstraintViolation();
            //
            // // 方法上的跨参数约束，如mergeAddresses(Map<String,Address> addresses,Map<String,Address> otherAddresses)
            // // default path + "otherAddresses["home"]
            // // 两个Map里都有key为home的键值对，但值不相同
            // context.buildConstraintViolationWithTemplate(
            //         "Map entry home present in both and does not match")
            //         .addParameterNode(1)
            //         .addBeanNode()
            //         .inContainer(Map.class, 1)
            //         .inIterable().atKey("home")
            //         .addConstraintViolation();
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

        }

        return b;
    }
}

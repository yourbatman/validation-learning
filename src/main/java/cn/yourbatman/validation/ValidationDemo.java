package cn.yourbatman.validation;

import cn.yourbatman.validation.bean.Person;
import cn.yourbatman.validation.bean.User;
import cn.yourbatman.validation.service.PersonService;
import org.hibernate.validator.internal.engine.DefaultParameterNameProvider;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ParameterNameProvider;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstructorDescriptor;
import javax.validation.metadata.MethodDescriptor;
import javax.validation.metadata.MethodType;
import javax.validation.metadata.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;

import static cn.yourbatman.validation.ValidatorUtil.obtainValidator;

public class ValidationDemo {

    /**
     * 校验Java Bean
     */
    @Test
    public void test1() {
        Validator validator = obtainValidator();

        Person person = new Person();
        person.setAge(-1);
        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // 输出校验结果
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }

    /**
     * 方法参数 / 返回值校验
     */
    @Test
    public void test2() throws NoSuchMethodException {
        // 看到没 IDEA自动帮你前面加了个notNull
        @NotNull Person result = new PersonService().getOne(1, "A哥");
    }

    /**
     * Java Bean作为方法参数的校验
     */
    @Test
    public void test3() throws NoSuchMethodException {
        // save.arg0 不能为null: null
        // new PersonService().save(null);
        new PersonService().save(new Person());
    }


    /**
     * 一个字段上标注多个注解
     */
    @Test
    public void test4() {
        User user = new User();
        user.setName("A哥");

        Set<ConstraintViolation<User>> result = obtainValidator().validate(user);
        result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }


    /**
     * 校验Java Bean
     */
    @Test
    public void test5() {
        User user = new User();
        user.setName("YourBatman");

        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validate(user);
        ValidatorUtil.printViolations(result);
    }

    /**
     * 校验Java Bean的某个属性
     */
    @Test
    public void test6() {
        User user = new User();
        user.setFullName("YourBatman");

        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validateProperty(user, "fullName");
        ValidatorUtil.printViolations(result);
    }

    /**
     * 校验某个值是否符合某个属性
     */
    @Test
    public void test7() {
        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validateValue(User.class, "fullName", "A哥");
        ValidatorUtil.printViolations(result);
    }

    @Test
    public void test8() {
        BeanDescriptor beanDescriptor = obtainValidator().getConstraintsForClass(User.class);
        System.out.println("此类是否需要校验：" + beanDescriptor.isBeanConstrained());

        // 获取属性、方法、构造器的约束
        Set<PropertyDescriptor> constrainedProperties = beanDescriptor.getConstrainedProperties();
        Set<MethodDescriptor> constrainedMethods = beanDescriptor.getConstrainedMethods(MethodType.GETTER);
        Set<ConstructorDescriptor> constrainedConstructors = beanDescriptor.getConstrainedConstructors();
        System.out.println("需要校验的属性：" + constrainedProperties);
        System.out.println("需要校验的方法：" + constrainedMethods);
        System.out.println("需要校验的构造器：" + constrainedConstructors);

        PropertyDescriptor fullNameDesc = beanDescriptor.getConstraintsForProperty("fullName");
        System.out.println(fullNameDesc);
        System.out.println(fullNameDesc.getConstraintDescriptors().size());
    }


    @Test
    public void test9() {
        ParameterNameProvider parameterNameProvider = new DefaultParameterNameProvider();

        // 拿到Person的无参构造和有参构造（@NoArgsConstructor和@AllArgsConstructor）
        Arrays.stream(Person.class.getConstructors()).forEach(c -> System.out.println(parameterNameProvider.getParameterNames(c)));
    }


    @Test
    public void test10() {
    }


}


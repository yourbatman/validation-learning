package cn.yourbatman.validation.service;

import cn.yourbatman.validation.bean.Person;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Set;

import static cn.yourbatman.validation.ValidatorUtil.obtainExecutableValidator;

public class PersonService implements PersonInterface {


    @Override
    public Person getOne(Integer id, String name) throws NoSuchMethodException {
        // 校验逻辑
        Method currMethod = this.getClass().getMethod("getOne", Integer.class, String.class);
        Set<ConstraintViolation<PersonService>> validResult = obtainExecutableValidator().validateParameters(this, currMethod, new Object[]{id, name});
        if (!validResult.isEmpty()) {
            // ... 输出错误详情validResult
            validResult.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
            throw new IllegalArgumentException("参数错误");
        }

        // ... 模拟逻辑执行，得到一个result
        Person result = null;
        //
        // // 在结果返回之前校验
        // Method currMethod = this.getClass().getMethod("getOne", Integer.class, String.class);
        // Set<ConstraintViolation<PersonService>> validResult = obtainExecutableValidator().validateReturnValue(this, currMethod, result);
        // if (!validResult.isEmpty()) {
        //     // ... 输出错误详情validResult
        //     validResult.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
        //     throw new IllegalArgumentException("参数错误");
        // }
        return result;
    }

    @Override
    public void save(Person person) throws NoSuchMethodException {
        Method currMethod = this.getClass().getMethod("save", Person.class);
        Set<ConstraintViolation<PersonService>> validResult = obtainExecutableValidator().validateParameters(this, currMethod, new Object[]{person});
        if (!validResult.isEmpty()) {
            // ... 输出错误详情validResult
            validResult.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
            throw new IllegalArgumentException("参数错误");
        }
    }


}
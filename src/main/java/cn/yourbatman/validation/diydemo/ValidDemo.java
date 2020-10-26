package cn.yourbatman.validation.diydemo;

import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * List级联校验
 *
 * @author yourbatman
 * @date 2020/9/2 15:41
 */
public class ValidDemo {

    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Person person = new Person();
        person.setName("YourBatman");

        Dog dog = new Dog();
        dog.setAge(-1);
        person.setDogs(Collections.singletonList(dog));

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        violations.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
    }


}

@Data
class Person {

    @NotEmpty
    private String name;
    private List<@Valid Dog> dogs;

}

@Data
class Dog {
    @NotBlank
    private String name;
    @Positive
    private Integer age;
}
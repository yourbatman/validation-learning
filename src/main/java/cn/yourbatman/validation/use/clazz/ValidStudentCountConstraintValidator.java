package cn.yourbatman.validation.use.clazz;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStudentCountConstraintValidator implements ConstraintValidator<ValidStudentCount, Room> {

    @Override
    public void initialize(ValidStudentCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Room room, ConstraintValidatorContext context) {
        if (room == null) {
            return true;
        }
        boolean isValid = false;
        if (room.getStudentNames().size() <= room.getMaxStuNum()) {
            isValid = true;
        }

        // 自定义提示语
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("校验失败xxx")
                    .addPropertyNode("studentNames")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
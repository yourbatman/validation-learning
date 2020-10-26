package cn.yourbatman.validation.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

import javax.validation.constraints.NotNull;
import java.time.Duration;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NotNull
    @Length(min = 1, max = 10)
    public String name;
    @NotNull
    @Range(min = 1, max = 18)
    public Integer age;

    @DurationMin(minutes = 40)
    @DurationMax(minutes = 80)
    public Duration duration;

    @DurationMin(minutes = 40)
    @DurationMax(minutes = 80)
    public Duration classDuration;

}

package cn.yourbatman.validation.service;

import cn.yourbatman.validation.bean.Person;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface PersonInterface {
    void save(@NotNull @Valid Person person) throws NoSuchMethodException;

    @NotNull Person getOne(@NotNull @Min(1) Integer id, String name) throws NoSuchMethodException;


}

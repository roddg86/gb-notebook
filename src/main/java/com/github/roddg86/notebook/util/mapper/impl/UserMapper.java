package com.github.roddg86.notebook.util.mapper.impl;

import com.github.roddg86.notebook.util.mapper.Mapper;
import com.github.roddg86.notebook.model.User;

public class UserMapper implements Mapper<User, String> {
    @Override
    public String toInput(User user) {
        return String.format("%s;%s;%s;%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public User toOutput(String s) {
        String[] lines = s.split(";");
        long id;
        if (isDigit(lines[0])) {
            id = Long.parseLong(lines[0]);
            return User.builder().id(id).firstName(lines[1]).lastName(lines[2]).phone(lines[3]).build();
        }
        throw new NumberFormatException("Id must be a large number");
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

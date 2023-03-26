package com.github.roddg86.notebook.view;

import com.github.roddg86.notebook.controller.UserControllerDecorator;
import com.github.roddg86.notebook.model.User;
import com.github.roddg86.notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserViewWithLog {
    private final UserControllerDecorator userControllerDecorator;

    public UserViewWithLog(UserControllerDecorator userControllerDecorator) {
        this.userControllerDecorator = userControllerDecorator;
    }

    public void run(){
        Commands com;

        while (true) {
            System.out.println();
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    userControllerDecorator.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userControllerDecorator.findUserById(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    List<User> users = userControllerDecorator.getAllUsers();
                    for(User user: users) {
                        System.out.println(user);
                        System.out.println();
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Введите user id: ");
                    userControllerDecorator.updateUser(userId, createUser());
                    break;
                case DELETE:
                    String deleteUserId = prompt("Введите user id: ");
                    userControllerDecorator.deleteUser(deleteUserId);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }
}

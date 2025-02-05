package com.github.roddg86.notebook.view;

import com.github.roddg86.notebook.controller.UserController;
import com.github.roddg86.notebook.model.User;
import com.github.roddg86.notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            System.out.println();
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE -> {
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    userController.saveUser(User.builder().firstName(firstName).lastName(lastName).phone(phone).build());
                }
                case READ -> {
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.findUserById(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case LIST -> {
                    List<User> users = userController.getAllUsers();
                    for (User user : users) {
                        System.out.println(user);
                        System.out.println();
                    }
                }
                case UPDATE -> {
                    String userId = prompt("Введите user id: ");
                    userController.updateUser(userId, createUser());
                }
                case DELETE -> {
                    String deleteUserId = prompt("Введите user id: ");
                    userController.deleteUser(deleteUserId);
                }
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
        return User.builder().firstName(firstName).lastName(lastName).phone(phone).build();
    }
}

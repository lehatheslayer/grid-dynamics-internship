package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int roleId;

    public User(String name, String password, String email, int roleId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }
}

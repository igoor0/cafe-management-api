package pl.obrona.managementapi.user;

import lombok.Getter;

@Getter
public class CreateUserCommand {
    private String username;
    private String password;
    private Role role;
}

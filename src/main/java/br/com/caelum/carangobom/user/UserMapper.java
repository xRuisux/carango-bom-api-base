package br.com.caelum.carangobom.user;


public class UserMapper {
    private Long id;
    private String name;
    private String email;

    public UserMapper(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

}

package ru.job4j.generics.store;

public class Role extends Base {

    private final String roleName;

    public Role(String id, String name) {
        super(id);
        this.roleName = name;
    }

    public String getRoleName() {
        return roleName;
    }
}
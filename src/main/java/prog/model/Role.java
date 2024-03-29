package prog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "role", unique = true)
    private String role;

//    @ManyToMany(mappedBy = "roles")
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id"
//    )
//    private Set<User> users = new HashSet<>();


    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.role = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return getRole();
    }

}

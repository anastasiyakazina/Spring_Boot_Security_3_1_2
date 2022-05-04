package web.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role(String role) {
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }


}
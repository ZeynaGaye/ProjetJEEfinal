package ucad.sn.master2.model;

import jakarta.persistence.*;
import ucad.sn.master2.util.RoleType;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleType role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Role() {
    }

    public Role(Long id, RoleType role, Users user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

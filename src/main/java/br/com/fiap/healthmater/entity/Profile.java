package br.com.fiap.healthmater.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Map the 'profile' table in the database.
 *
 * @author Gabriel Oliveira
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"description"})})
public class Profile {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String description;

    @ManyToMany(mappedBy = "profiles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_permission", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    @PreRemove
    private void removeProfiles() {
        for (User user : users) {
            user.getProfiles().remove(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

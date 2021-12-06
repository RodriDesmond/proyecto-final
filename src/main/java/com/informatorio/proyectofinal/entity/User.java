package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.StringUtils.capitalize;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users_table")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(unique = true, length = 45)
    private String username;
    @NonNull
    @Getter(onMethod = @__( @JsonIgnore ))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role;
    private boolean active = true;
    @NotBlank
    private String firstname;
    private String lastname;
    private String city;
    private String province;
    private String country;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "creator", cascade=CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Emprendimiento> emprendimientos = new ArrayList<>();
    private LocalDateTime lastUpdated;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = capitalize(city.toLowerCase());
    }
    public void setProvince(String province) {
        this.province = capitalize(province.toLowerCase());
    }
    public void setCountry(String country) {this.country = capitalize(country.toLowerCase());}

    public void addEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.add(emprendimiento);
        emprendimiento.setCreator(this);
    }
    public void removerEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.remove(emprendimiento);
        emprendimiento.setCreator(null);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

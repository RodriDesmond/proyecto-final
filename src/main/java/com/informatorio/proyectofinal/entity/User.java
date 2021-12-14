package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.StringUtils.capitalize;

@Entity
@Getter
@Setter
@ToString
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE users_table SET active=false WHERE id = ?")
@Table(name = "users_table")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(unique = true, length = 45)
    @NotEmpty(message = "You must provide a valid email ")
    private String username;

    @NotEmpty(message = "You must provide a valid password ")
    @Getter(onMethod = @__( @JsonIgnore ))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.USUARIO;
    private boolean active = true;

    @NotEmpty(message = "This should not be empty.")
    private String firstname;
    private String lastname;
    private String city;
    private String province;
    private String country;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "creator", cascade=CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<Emprendimiento> emprendimientos = new ArrayList<>();

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime lastUpdated;

    public User() {
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

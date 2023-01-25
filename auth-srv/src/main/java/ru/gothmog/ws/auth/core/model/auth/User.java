package ru.gothmog.ws.auth.core.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import ru.gothmog.ws.auth.core.model.AbstractEntity;

import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "auth", indexes = {
        @Index(name = "idx_user_uuid_unq", columnList = "uuid", unique = true),
        @Index(name = "idx_user_username_unq", columnList = "username", unique = true),
        @Index(name = "idx_user_email_unq", columnList = "email", unique = true),
        @Index(name = "idx_user_phone_unq", columnList = "phone", unique = true)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractEntity {
    @NotNull
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Size(max = 200)
    @NotNull
    @Column(name = "username", nullable = false, unique = true, length = 200)
    private String username;

    @Size(max = 200)
    @NotNull
    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Size(max = 512)
    @NotNull
    @Column(name = "passwd", nullable = false, length = 512)
    private String passwd;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserBill> userBills = new ArrayList<>();
}

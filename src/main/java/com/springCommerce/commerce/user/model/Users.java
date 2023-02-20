package com.springCommerce.commerce.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "users")
public class Users implements UserDetails {

  @Enumerated(EnumType.STRING)
  Role role = Role.USER;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NonNull private String firstName;
  @NonNull private String middleName;
  @NonNull private String lastName;
  @NonNull
  @Column(unique = true)
  private String mail;
  @NonNull private Boolean isActive;
  @NonNull private String password;
  @NonNull
  @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<UserExtraDetails> userExtraDetailsSet = new HashSet<>();

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return mail;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  //  public Users(Long id, @NonNull String firstName, @NonNull String middleName, @NonNull String
  // lastName, @NonNull String mail, @NonNull Boolean isActive) {
  //    this.id = id;
  //    this.firstName = firstName;
  //    this.middleName = middleName;
  //    this.lastName = lastName;
  //    this.mail = mail;
  //    this.isActive = isActive;
  //  }
  //
  //  public Users(@NonNull String firstName, @NonNull String middleName, @NonNull String lastName,
  // @NonNull String mail, @NonNull Boolean isActive) {
  //    this.firstName = firstName;
  //    this.middleName = middleName;
  //    this.lastName = lastName;
  //    this.mail = mail;
  //    this.isActive = isActive;
  //  }

}

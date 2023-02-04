package com.springCommerce.commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class Users {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Users user = (Users) o;
    return Objects.equals(id, user.id)
        && firstName.equals(user.firstName)
        && middleName.equals(user.middleName)
        && lastName.equals(user.lastName)
        && mail.equals(user.mail)
        && isActive.equals(user.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleName, lastName, mail, isActive);
  }
}

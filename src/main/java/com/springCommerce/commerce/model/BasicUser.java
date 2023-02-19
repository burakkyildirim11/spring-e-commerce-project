package com.springCommerce.commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "basicUser")
public class BasicUser {

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


  @OneToMany(mappedBy = "basicUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<UserDetails> userDetailsSet = new HashSet<>();

  //  public Users(Long id, @NonNull String firstName, @NonNull String middleName, @NonNull String lastName, @NonNull String mail, @NonNull Boolean isActive) {
  //    this.id = id;
  //    this.firstName = firstName;
  //    this.middleName = middleName;
  //    this.lastName = lastName;
  //    this.mail = mail;
  //    this.isActive = isActive;
  //  }
  //
  //  public Users(@NonNull String firstName, @NonNull String middleName, @NonNull String lastName, @NonNull String mail, @NonNull Boolean isActive) {
  //    this.firstName = firstName;
  //    this.middleName = middleName;
  //    this.lastName = lastName;
  //    this.mail = mail;
  //    this.isActive = isActive;
  //  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BasicUser basicUser = (BasicUser) o;
    return Objects.equals(id, basicUser.id)
            && firstName.equals(basicUser.firstName)
            && middleName.equals(basicUser.middleName)
            && lastName.equals(basicUser.lastName)
            && mail.equals(basicUser.mail)
            && isActive.equals(basicUser.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleName, lastName, mail, isActive);
  }
}
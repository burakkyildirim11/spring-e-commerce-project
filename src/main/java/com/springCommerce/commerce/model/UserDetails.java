package com.springCommerce.commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull private String phoneNumber;
  @NonNull private String address;
  @NonNull private String city;
  @NonNull private String country;
  @NonNull private String postCode;


  @NonNull
  @JoinColumn(name = "users_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  Users users;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetails that = (UserDetails) o;
    return Objects.equals(id, that.id)
        && phoneNumber.equals(that.phoneNumber)
        && address.equals(that.address)
        && city.equals(that.city)
        && country.equals(that.country)
        && postCode.equals(that.postCode)
        && users.equals(that.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, phoneNumber, address, city, country, postCode, users);
  }
}

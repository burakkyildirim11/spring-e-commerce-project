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
@Table(name = "user_details")
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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "basicUser_id", nullable = false)
  BasicUser basicUser;


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
            && basicUser.equals(that.basicUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, phoneNumber, address, city, country, postCode, basicUser);
  }
}
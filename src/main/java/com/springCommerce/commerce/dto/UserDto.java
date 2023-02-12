package com.springCommerce.commerce.dto;

<<<<<<< HEAD
import lombok.*;

=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> e0ccc1d1a10e3cc3ce19b2021b7aaa80e32cefc2
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

  @NonNull
  private String firstName;
  @NonNull
  private String middleName;
  @NonNull
  private String lastName;
  @NonNull
  private String mail;
<<<<<<< HEAD

  private List<UserDetailsDto> userDetailsDto;
}
=======
  private List<UserDetailsDto> userDetailsDto;
}
>>>>>>> e0ccc1d1a10e3cc3ce19b2021b7aaa80e32cefc2

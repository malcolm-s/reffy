package com.mstone.reffy.register.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.mstone.reffy.register.RegisterForm;
import com.mstone.reffy.user.User;
import com.mstone.reffy.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UniqueUserValidatorTest {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UniqueUserValidator validator;

  @Test
  public void isValidReturnsTrueWhenNoMatchingUserFound() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    var form = new RegisterForm();
    form.setEmail("asd@asd.asd");

    assertTrue(validator.isValid(form, null));
  }

  @Test
  public void isValidReturnsFalseWhenMatchingUserFound() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
    var form = new RegisterForm();
    form.setEmail("asd@asd.asd");

    assertFalse(validator.isValid(form, null));
  }
}

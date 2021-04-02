package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModelBase;
import modelimpls.LoginModelInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginVMTest
{
  private LoginVM loginVM;
  private StringProperty username = new SimpleStringProperty();
  private StringProperty password = new SimpleStringProperty();
  private StringProperty result = new SimpleStringProperty();

  @BeforeEach public void setup()
  {
    LoginModelBase model = new LoginModelInMemory();
    model.populateModelWithDummyUsers();
    loginVM = new LoginVM(model);

    username.bindBidirectional(loginVM.userNameProperty());
    password.bindBidirectional(loginVM.passwordProperty());
    result.bind(loginVM.loginResultProperty());
  }

  // Tests for login in
  @Test void ValidLogin()
  {
    // act
    username.set("Jakob");
    password.set("Jakob1");
    loginVM.validateLogin();

    // assert
    assertEquals("", loginVM.userNameProperty().get());
    assertEquals("", loginVM.passwordProperty().get());
    assertEquals("OK", loginVM.loginResultProperty().get());
  }

  @Test void loginWithNewUser()
  {
    // act
    username.set("Luis");
    password.set("123456Az");
    loginVM.validateLogin();

    // assert
    assertEquals("Luis", loginVM.userNameProperty().get());
    assertEquals("123456Az", loginVM.passwordProperty().get());
    assertEquals("User not found", loginVM.loginResultProperty().get());
  }

  @Test void loginWithIncorrectPass()
  {
    // act
    username.set("Jakob");
    password.set("123456Az");
    loginVM.validateLogin();

    // assert
    assertEquals("Jakob", loginVM.userNameProperty().get());
    assertEquals("123456Az", loginVM.passwordProperty().get());
    assertEquals("Incorrect password", loginVM.loginResultProperty().get());
  }
}
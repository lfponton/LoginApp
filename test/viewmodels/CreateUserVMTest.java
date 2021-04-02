package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModelBase;
import modelimpls.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserVMTest
{

  private CreateUserVM createUserVM;
  private StringProperty username = new SimpleStringProperty();
  private StringProperty password = new SimpleStringProperty();
  private StringProperty passwordAgain = new SimpleStringProperty();
  private StringProperty result = new SimpleStringProperty();

  @BeforeEach public void setup()
  {
    // arrange
    LoginModelBase model = new LoginModelInMemory();
    model.populateModelWithDummyUsers();
    createUserVM = new CreateUserVM(model);

    createUserVM.usernameProperty().bindBidirectional(username);
    createUserVM.passwordProperty().bindBidirectional(password);
    createUserVM.passwordAgainProperty().bindBidirectional(passwordAgain);
    createUserVM.createUserResultProperty().bindBidirectional(result);

  }

  // Tests for creating a user
  @Test public void createValidUser()
  {
    // act
    username.setValue("Luis");
    password.setValue("123456Az");
    passwordAgain.setValue("123456Az");
    createUserVM.attemptCreateUser();

    // assert
    assertEquals("OK", result.getValue());
  }

  @Test public void createUserEmptyName()
  {
    // act
    username.setValue(null);
    password.setValue("123456Az");
    passwordAgain.setValue("123456Az");
    createUserVM.attemptCreateUser();

    // assert
    assertEquals("Username cannot be empty", result.getValue());
  }


}
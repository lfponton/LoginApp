package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModelBase;
import modelimpls.LoginModelInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserVMTest
{
  private CreateUserVM vm;
  @BeforeEach
  public void setup() {
    LoginModelBase model = new LoginModelInMemory();
    model.populateModelWithDummyUsers();
    vm = new CreateUserVM(model);
  }

  @Test
  public void createValidUser() {
    //arrange
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty passwordAgain = new SimpleStringProperty();
    StringProperty result = new SimpleStringProperty();
    vm.usernameProperty().bindBidirectional(username);
    vm.passwordProperty().bindBidirectional(password);
    vm.passwordAgainProperty().bindBidirectional(passwordAgain);
    vm.createUserResultProperty().bindBidirectional(result);

    // act
    username.setValue("Luis");
    password.setValue("123456Az");
    passwordAgain.setValue("123456Az");
    vm.attemptCreateUser();

    // assert
    assertEquals("OK", result.getValue());
  }
}
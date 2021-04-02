package viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModelBase;
import modelimpls.LoginModelInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordVMTest
{
  private ChangePasswordVM changePasswordVM;
  private StringProperty username = new SimpleStringProperty();
  private StringProperty password = new SimpleStringProperty();
  private StringProperty newPassword = new SimpleStringProperty();
  private StringProperty newPasswordAgain = new SimpleStringProperty();
  private StringProperty result = new SimpleStringProperty();

  @BeforeEach public void setup()
  {
    // arrange
    LoginModelBase model = new LoginModelInMemory();
    model.populateModelWithDummyUsers();
    changePasswordVM = new ChangePasswordVM(model);

    username.bindBidirectional(changePasswordVM.usernameProperty());
    password.bindBidirectional(changePasswordVM.passwordProperty());
    newPassword.bindBidirectional(changePasswordVM.newPasswordProperty());
    newPasswordAgain.bindBidirectional(changePasswordVM.newPasswordAgainProperty());
    result.bind(changePasswordVM.changePasswordResultProperty());
  }

    // Tests for changing password

    @Test void changingPassword() {

    // act
    username.set("Jakob");
    password.set("Jakob1");
    newPassword.set("zA123456");
    newPasswordAgain.set("zA123456");
    changePasswordVM.updatePassword();

    // assert
    assertEquals("Jakob", changePasswordVM.usernameProperty().get());
    assertEquals("Jakob1", changePasswordVM.passwordProperty().get());
    assertEquals("zA123456", changePasswordVM.newPasswordProperty().get());
    assertEquals("zA123456", changePasswordVM.newPasswordAgainProperty().get());
    assertEquals("OK", changePasswordVM.changePasswordResultProperty().get());
  }
}
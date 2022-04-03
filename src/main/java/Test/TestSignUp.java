package Test;

import com.example.surinklietuva.BigDataManager;
import com.example.surinklietuva.Controllers.SignUpView;
import com.example.surinklietuva.DataStructures.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class TestSignUp {
    @Test
    public void TestUserPassword() throws FileNotFoundException {
        SignUpView signUpView = new SignUpView();
        Assert.assertEquals(signUpView.checkPasswordLength("kk"),false);
        Assert.assertEquals(signUpView.checkPasswordLength("0123456789012345"),false);
        Assert.assertEquals(signUpView.checkPasswordLength("kkkk"),true);
        Assert.assertEquals(signUpView.checkPasswordLength("012345678901234"),true);
    }
    @Test
    public void TestUserEmail(){
        SignUpView signUpView = new SignUpView();
        //Assert.assertEquals(signUpView.checkEmailValidation("diana.gmail.com"),true);                 //1.1 - wrong test
        //Assert.assertEquals(signUpView.checkEmailValidation("diana"),true);                           //1.1 - wrong test
        Assert.assertFalse(signUpView.checkEmailValidation("diana.gmail.com"));                //1.2 - correct
        Assert.assertTrue(signUpView.checkEmailValidation("diana@gmail.com"));                 //1.2 - correct
    }
}

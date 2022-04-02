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
    public void TestUserInput() throws FileNotFoundException {
        SignUpView signUpView = new SignUpView();
        Assert.assertEquals(signUpView.checkPasswordLength("kkk"),false);
        Assert.assertEquals(signUpView.checkPasswordLength("0123456789012345"),false);
        Assert.assertEquals(signUpView.checkPasswordLength("kkkk"),true);
        Assert.assertEquals(signUpView.checkPasswordLength("012345678901234"),true);
    }
}

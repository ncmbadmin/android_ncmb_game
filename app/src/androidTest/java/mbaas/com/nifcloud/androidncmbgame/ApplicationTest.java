package mbaas.com.nifcloud.androidncmbgame;
import com.google.common.truth.Truth.assertThat;
import org.junit.Test;

public class ApplicationTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(EmailValidator.isValidEmail("name@email.com")).isTrue();
    }
}

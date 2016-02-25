import com.rduda.Model.ByteMatcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Robin on 2016-02-25.
 *
 * Tests the BitMatcher.
 */

public class ByteMatcherTest {
    private String PASSWORD = "the_password";

    @Test
    public void shouldMatchWholeString() {
        String result = ByteMatcher.match(PASSWORD, PASSWORD.length());
        Assert.assertEquals(PASSWORD, result);
    }

    @Test
    public void shouldMatchNone() {
        String result = ByteMatcher.match(PASSWORD, 0);
        Assert.assertEquals("????????????", result);
    }

    @Test
    public void shouldAddPadding() {
        String result = ByteMatcher.match(PASSWORD, PASSWORD.length() - 1);
        Assert.assertEquals("the_passwor?", result);
    }
}

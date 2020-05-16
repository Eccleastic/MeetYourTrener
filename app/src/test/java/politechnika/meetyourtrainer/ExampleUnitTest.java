package politechnika.meetyourtrainer;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import politechnika.meetyourtrainer.api.APIHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
//    @Test
//    public void wrongUserNameAndPasswordShouldReturnLoginUnsucessful(){
//        boolean response = APIHandler.confirUserNameAndUserPasswordWithDatabase("abcd", "xyz");
//        assertThat(response, is(false));
//    }
}
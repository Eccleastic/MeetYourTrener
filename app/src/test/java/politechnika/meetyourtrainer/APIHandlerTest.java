package politechnika.meetyourtrainer;

import org.junit.Test;

import politechnika.meetyourtrainer.api.APIHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class APIHandlerTest {
    @Test
    public void wrongUserNameAndPasswordShouldReturnLoginUnsucessful(){
        boolean response = APIHandler.confirUserNameAndUserPasswordWithDatabase("abcd", "xyz");
        assertThat(response, is(false));
    }
}

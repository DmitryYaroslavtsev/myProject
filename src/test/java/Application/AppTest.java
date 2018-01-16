package Application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void isPhoneValid() {
        //App test = new App();
        boolean actual = App.isPhoneValid("+79112345678");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void nonSqlInjection() {
        String str1 = "test;select * from jc_contact";
        String str2 = "test'test";
        String str3 = "test";
        boolean actual1 = App.nonSqlInjection(str1);
        boolean actual2 = App.nonSqlInjection(str2);
        boolean actual3 = App.nonSqlInjection(str3);

        assertEquals(false, actual1);
    }
}
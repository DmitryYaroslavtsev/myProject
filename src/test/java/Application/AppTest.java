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
    void nonSqlInjection1() {
        String str = "test;select * from jc_contact";
        boolean actual = App.nonSqlInjection(str);
        assertEquals(false, actual);
    }

    @Test
    void nonSqlInjection2() {
        String str = "test'test";
        boolean actual = App.nonSqlInjection(str);
        assertEquals(false, actual);
    }

    @Test
    void nonSqlInjection3() {
        String str = "testString";
        boolean actual = App.nonSqlInjection(str);
        assertEquals(true, actual);
    }

    @Test
    void isNameValid() {
        String str1 = "TestTest";
        String str2 = "1TestTest";
        boolean actual1 = App.isNameValid(str1);
        boolean actual2 = App.isNameValid(str2);

        assertEquals(true, actual1);
        assertEquals(false, actual2);
    }
}
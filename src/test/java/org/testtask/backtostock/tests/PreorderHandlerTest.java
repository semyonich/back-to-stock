package org.testtask.backtostock.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testtask.backtostock.exception.UserNotFoundException;
import org.testtask.backtostock.model.Product;
import org.testtask.backtostock.model.ProductCategory;
import org.testtask.backtostock.model.User;
import org.testtask.backtostock.service.PreorderHandler;
import org.testtask.backtostock.service.impl.PreorderHandlerImpl;

public class PreorderHandlerTest {
    private static PreorderHandler handler;
    private static User userBob;
    private static User userAlice;
    private static User userBruce;
    private static User userJohn;
    private static Product thonometer;
    private static Product piano;


    @BeforeAll
    static void beforeAll() {
        handler = new PreorderHandlerImpl().init();
        userJohn = new User("john", User.UserStatus.PREMIUM, 90);
        userBob = new User("bob", User.UserStatus.PREMIUM, 50);
        userAlice = new User("alice", User.UserStatus.COMMON, 71);
        userBruce = new User("bruce", User.UserStatus.COMMON, 20);
        thonometer = new Product("thonometer", ProductCategory.MEDICAL);
        piano = new Product("piano", ProductCategory.OTHER);
    }

    @Test
    void getUserTest_ok() {
        handler.addPreorder(userJohn, piano);
        handler.addPreorder(userBob, piano);
        handler.getUser(piano);
        Assertions.assertEquals(userJohn, handler.getUser(piano));
    }

    @Test
    void getUserRemovePreorderTest_ok() {
        handler.addPreorder(userJohn, piano);
        handler.addPreorder(userBob, piano);
        handler.getUserRemovePreorder(piano);
        Assertions.assertEquals(userBob, handler.getUserRemovePreorder(piano));
    }

    @Test
    void premiumUsersSameProductTest_ok() {
        handler.addPreorder(userJohn, thonometer);
        handler.addPreorder(userBob, thonometer);
        Assertions.assertEquals(userJohn, handler.getUserRemovePreorder(thonometer));
        Assertions.assertEquals(userBob, handler.getUserRemovePreorder(thonometer));
    }

    @Test
    void premiumUserOldUserCommonUserSameProductTest_ok() {
        handler.addPreorder(userBruce, piano);
        handler.addPreorder(userBob, piano);
        handler.addPreorder(userAlice, piano);
        Assertions.assertEquals(userBob, handler.getUserRemovePreorder(piano));
        Assertions.assertEquals(userAlice, handler.getUserRemovePreorder(piano));
        Assertions.assertEquals(userBruce, handler.getUserRemovePreorder(piano));
    }

    @Test
    void oldUserCommonUserSameProduct_ok() {
        handler.addPreorder(userBruce, piano);
        handler.addPreorder(userAlice, piano);
        Assertions.assertEquals(userAlice, handler.getUserRemovePreorder(piano));
        Assertions.assertEquals(userBruce, handler.getUserRemovePreorder(piano));
    }

    @Test
    void oldUsersSameProductTest_ok() {
        handler.addPreorder(userJohn, thonometer);
        handler.addPreorder(userAlice, thonometer);
        handler.addPreorder(userJohn, piano);
        handler.addPreorder(userAlice, piano);
        Assertions.assertEquals(userJohn, handler.getUserRemovePreorder(thonometer));
        Assertions.assertEquals(userAlice, handler.getUserRemovePreorder(thonometer));
        Assertions.assertEquals(userJohn, handler.getUserRemovePreorder(piano));
        Assertions.assertEquals(userAlice, handler.getUserRemovePreorder(piano));
    }

    @Test()
    void userNotFoundTestGetUser() {
        handler.addPreorder(userBruce, piano);
        Assertions.assertThrows(UserNotFoundException.class,
                () -> handler.getUser(thonometer));
    }

    @Test()
    void userNotFoundTestGetUserRemovePreorder() {
        handler.addPreorder(userBruce, piano);
        Assertions.assertThrows(UserNotFoundException.class,
                () -> handler.getUserRemovePreorder(thonometer));
    }
}

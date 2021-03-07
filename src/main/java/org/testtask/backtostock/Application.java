package org.testtask.backtostock;

import org.testtask.backtostock.model.Product;
import org.testtask.backtostock.model.ProductCategory;
import org.testtask.backtostock.model.User;
import org.testtask.backtostock.model.User.UserStatus;
import org.testtask.backtostock.service.PreorderHandler;
import org.testtask.backtostock.service.impl.PreorderHandlerImpl;

public class Application {
    public static void main(String[] args) {
        PreorderHandler handler = new PreorderHandlerImpl().init();
        User userBob = new User("bob", UserStatus.PREMIUM, 50);
        User userAlice = new User("alice",UserStatus.COMMON, 71);
        User userBruce = new User("bruce", UserStatus.COMMON, 20);
        User userJohn = new User("john", UserStatus.PREMIUM, 90);
        Product thonometer = new Product("thonometer", ProductCategory.MEDICAL);
        Product glucometer = new Product("glucometer", ProductCategory.MEDICAL);
        Product fridge = new Product("fridge", ProductCategory.OTHER);
        Product car = new Product("car", ProductCategory.OTHER);
        Product piano = new Product("piano", ProductCategory.OTHER);
        handler.addPreorder(userAlice, fridge);
        handler.addPreorder(userBob, fridge);
        handler.addPreorder(userBruce, fridge);
        System.out.println(handler.getUser(fridge));
        System.out.println(handler.getUserRemovePreorder(fridge));
        System.out.println(handler.getUser(fridge));
        System.out.println(handler.getUserRemovePreorder(fridge));
        System.out.println(handler.getUser(fridge));
        handler.addPreorder(userAlice, thonometer);
        handler.addPreorder(userBruce, thonometer);
        handler.addPreorder(userBob, thonometer);
        System.out.println(handler.getUser(thonometer));
        System.out.println(handler.getUser(thonometer));
        System.out.println(handler.getUser(thonometer));
        System.out.println("====================");

        handler.addPreorder(userJohn, car);
        handler.addPreorder(userAlice, car);
        handler.addPreorder(userBob, car);
        System.out.println(handler.getUser(car));
        System.out.println(handler.getUser(car));
        System.out.println(handler.getUser(car));



    }
}

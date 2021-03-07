package org.testtask.backtostock.service;

import org.testtask.backtostock.model.Product;
import org.testtask.backtostock.model.User;

public interface PreorderHandler {
    void addPreorder(User user, Product product);

    User getUser(Product product);

    User getUserRemovePreorder(Product product);
}

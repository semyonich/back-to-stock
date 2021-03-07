package org.testtask.backtostock.model;

import org.testtask.backtostock.model.User.UserStatus;

public class PriorityCalculationContainer {
    public enum PriorityType {
        HIGH,
        MEDIUM,
        LOW
    }

    private PriorityType priority;
    private User user;
    private Product product;

    public PriorityCalculationContainer(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public PriorityCalculationContainer init() {
        if (user.getStatus().equals(UserStatus.PREMIUM)) {
            priority = PriorityType.HIGH;
            return this;
        }
        if (user.getAge() > 70) {
            if (product.getCategory().equals(ProductCategory.MEDICAL)) {
                priority = PriorityType.HIGH;
                return this;
            }
            priority = PriorityType.MEDIUM;
            return this;
        }
        priority = PriorityType.LOW;
        return this;
    }
}

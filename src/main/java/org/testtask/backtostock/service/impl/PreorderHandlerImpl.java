package org.testtask.backtostock.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.testtask.backtostock.exception.UserNotFoundException;
import org.testtask.backtostock.model.PriorityCalculationContainer;
import org.testtask.backtostock.model.PriorityCalculationContainer.PriorityType;
import org.testtask.backtostock.model.Product;
import org.testtask.backtostock.model.User;
import org.testtask.backtostock.service.PreorderHandler;

public class PreorderHandlerImpl implements PreorderHandler {
    private final Map<PriorityType, Map<String, List<User>>> priorityMap;

    public PreorderHandlerImpl() {
        priorityMap = new HashMap<>();
    }

    public PreorderHandler init() {
        for (PriorityType value : PriorityType.values()) {
            priorityMap.put(value, new HashMap<>());
        }
        return this;
    }

    public void addPreorder(User user, Product product) {
        PriorityCalculationContainer container = new PriorityCalculationContainer(user, product)
                .init();
        if (priorityMap.get(container.getPriority()).containsKey(product.getName())) {
            priorityMap.get(container.getPriority()).get(product.getName()).add(user);
            return;
        }
        List<User> queueList = new LinkedList<>();
        queueList.add(user);
        priorityMap.get(container.getPriority()).put(product.getName(), queueList);
    }

    @Override
    public User getUser(Product product) {
        String productName = product.getName();
        for (PriorityType priorityType : PriorityType.values()) {
            if (priorityMap.get(priorityType).containsKey(productName)
                    && priorityMap.get(priorityType).get(productName).size() != 0) {
                return priorityMap.get(priorityType).get(productName).get(0);
            }
        }
        throw new UserNotFoundException("Nobody has preordered this product yet!");
    }

    public User getUserRemovePreorder(Product product) {
        String productName = product.getName();
        for (PriorityType priorityType : PriorityType.values()) {
            if (priorityMap.get(priorityType).containsKey(productName)
                    && priorityMap.get(priorityType).get(productName).size() != 0) {
                return priorityMap.get(priorityType).get(productName).remove(0);
            }
        }
        throw new UserNotFoundException("Nobody has preordered this product yet!");
    }
}

##Back-To-Stock <br>
This project is came to solve next real business task:

Our customer AmazingCo asked to let users to subscribe products that are out of stock.
The idea is to notify customers when the product back in stock.
At the same time they have already in place premium users and also some users may have
priority for particular product categories.
Rules:
- the premium user has a high priority
- users elder than 70 years old has:
- high priority for medical products
- medium priority for all categories
- FIFO for all the rest

##How to run<br>
Open Application class, create _User_ and _Product_ objects with correct values,
make an instance of _PreorderHandlerImpl_ class and call method _init()_ on this instance.<br>
_PreorderHandler handler = new PreorderHandlerImpl().init();_<br>
Now you can call his method _addPreorder(User user, Product product)_ to make preorder of product for user.
Calling method _getUser(Product product)_ only returns user, who made preorder of this product.
Calling method _getUserRemovePreorder(Product product)_ returns user, who made preorder 
of this product and removes preorder for this user.

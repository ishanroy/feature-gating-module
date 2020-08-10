# feature-gating-module
## LLD of a Feature Gating Module

A feature gating module which decides whether the user is allowed to access a particular feature or not depending on conditional expression evaluated against user attributes.

The system takes a conditional Expression and user attributes defined as
complex map and returns a boolean.
### User Attributes

The following user attributes are supported (case sensitive) by the system in the conditional expression and in the user attribute map:
* name
* age
* gender
* past_order_amount
* address.city
* address.country

### Conditional Expression

The List of operators supported by the system are :

**Conditional Operators**
* greater than (>)
* less than (<)
* greater than or equal (>=)
* less than or equal (<=)
* equals (==)
* not equals (!=)
* between (a between b,c)

**Logical Operators**
* and
* or

### Limitations/Assumptions

* All the operators, attributes and operands are separated by spaces in the conditional expression.
* The supported datatypes for user attributes and operands in the conditional expression are int, double, boolean and String. 
* All strings in the conditional expression are specified within double quotes
* Any words in the conditional expression without quotes is assumed to be a user attribute or an operator. 
* The system suppports only the already defined user attributes, operators and datatypes mentioned above. 

### Execution

* The program can be run as a console application by running the [App](https://github.com/ishanroy/feature-gating-module/blob/feature-gating/src/main/java/com/isroy/dev/App.java) class which has the *main()* method
* You can check the tests for different conditional expressions in [FeatureGatingValidatorTest](https://github.com/ishanroy/feature-gating-module/blob/feature-gating/src/test/java/com/isroy/dev/feature/gate/FeatureGatingValidatorTest.java) class







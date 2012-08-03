package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
@DiscriminatorValue("T")
public class Truck extends Vehicle {

    int capacity;
}
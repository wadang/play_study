package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
@DiscriminatorValue("C")
public class Car extends Vehicle {

    String driver;
}

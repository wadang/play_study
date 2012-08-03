package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.annotation.EnumMapping;
import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;

/**
 * Customer entity bean.
 */
@Entity
@Table(name = "o_customer")
public class Customer extends Domain {

    private static final long serialVersionUID = 1L;

    /**
     * EnumMapping is an Ebean specific mapping for enums.
     */
    @EnumMapping(nameValuePairs = "NEW=N,ACTIVE=A,INACTIVE=I")
    public enum Status {
        NEW,
        ACTIVE,
        INACTIVE
    }


    Status status;

    @NotNull //也是一个检验注解
    @Length(max = 40)
    String name;


    @ManyToOne(cascade = CascadeType.ALL)
    Address billingAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    Address shippingAddress;

    @OneToMany(mappedBy = "customer")
    List<Order> orders;
}

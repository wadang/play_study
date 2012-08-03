package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.annotation.Sql;
import com.avaje.ebean.annotation.SqlSelect;
import com.avaje.ebean.validation.NotNull;

/**
 * Order entity bean.
 */
@Entity
@Table(name = "o_order")
@Sql(select = {
        @SqlSelect(name = "test", query = "select id, status from o_customer u", tableAlias = "u"),
        @SqlSelect(name = "test2", extend = "test", where = "u.status = :status")
})
public class Order extends Domain {

    private static final long serialVersionUID = 1L;

    //@EnumMapping(nameValuePairs="APPROVED=A, COMPLETE=C, NEW=N, SHIPPED=S")
    public enum Status {
        NEW,
        APPROVED,
        SHIPPED,
        COMPLETE
    }

    public Order() {

    }

//
//	@Formula(select="c.name",join="join o_customer c on c.id = ${ta}.kcustomer_id")
//	String custName;


    @Enumerated(value = EnumType.STRING)
    Status status = Status.NEW;

    Date orderDate = new Date(System.currentTimeMillis());

    Date shipDate;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "kcustomer_id")
    Customer customer;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    List<OrderDetail> details;

}

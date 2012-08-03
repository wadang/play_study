package models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "o_order_detail")
public class OrderDetail {

    @Id
    Integer id;

    Integer orderQty;

    Integer shipQty;

    Timestamp cretime;

    @Version
    Timestamp updtime;

    @ManyToOne(cascade = CascadeType.ALL)
    Order order;

    @ManyToOne
    Product product;
}

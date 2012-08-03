package models;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.Sql;
import com.avaje.ebean.annotation.SqlSelect;
import com.avaje.ebean.validation.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "o_product")
@Sql(select = {
        @SqlSelect(name = "test", query = "select id, name from o_product")
}
)
/*  ebean的注解
    List<Product> list =Ebean.find(Product.class, "test").findList();
 */
public class Product {

    @Id
    public Integer id;

    @Length(max = 20) //ebean的校验注解
    public String sku;

    public String name;

    @CreatedTimestamp  //ebean注解
    public Timestamp cretime;

    @Version
    public Timestamp updtime;

}
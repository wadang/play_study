package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:33
 * To change this template use File | Settings | File Templates.
 */

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.Length;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Address entity bean.
 */
@Entity
@Table(name = "o_address")
public class Address extends Model {

    @Id
    public Short id;

    @Constraints.Required  //play.vilidata
    @Length(max = 100)
    @Column(name = "line_1")
    public String line1;

    @Length(max = 100)
    @Column(name = "line_2")
    public String line2;

    @Length(max = 100)
    public String city;

    @CreatedTimestamp
    public Timestamp cretime;

    @Version
    public Timestamp updtime;

    @ManyToOne
    public Country country;

}
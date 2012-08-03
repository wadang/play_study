package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.Length;
import play.db.ebean.Model;

/**
 * Country entity bean.
 */
@Entity
@Table(name = "o_country")
public class Country  extends Model {

    @Id
    @Length(max = 2)
    public   String code;

    @Length(max = 60)
    public  String name;
}
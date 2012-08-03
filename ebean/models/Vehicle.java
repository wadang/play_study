package models;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 上午11:24
 * To change this template use File | Settings | File Templates.
 */


@Entity   //是一个实体
@Inheritance                      //继承策略在继承结构的根初定义，可以指定strategy,默认是单表
@DiscriminatorColumn(length = 14) //继承鉴别字段
public abstract class Vehicle extends Model {

    @Id                    //id 可以指定产生方式
    public Integer id;

    public String license;

    @Version            //设置乐观锁
    public Integer version;
}





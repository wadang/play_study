package models;

/**
 * Created by IntelliJ IDEA.
 * User: wadang
 * Date: 12-8-2
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.avaje.ebean.annotation.CreatedTimestamp;

@MappedSuperclass //父类，不会为他专门分配表
public class Domain {

    @Id
    public Integer id;

    @CreatedTimestamp
    public Timestamp cretime;

    @Version
    public Timestamp updtime;


}
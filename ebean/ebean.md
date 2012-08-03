**ebean特点**

* 概念上接近hibernate，但是更简洁
* 没有Session的概念。
* fecth比懒加载控制更灵活
* 比ibatis更好用。
* 支持复杂的功能，cache TX事务等。

**demo**





      +------------+              +------------+
      |    Country |<-------------+  Address   |
      |------------|              |------------|
      |            |              |            |
      +------------+              +------------+
                                         ^
                                         |
                                         +
                                  +------------+         +-------------+
                                  |  Coustem   |         |  product    |
      +-----------+               |------------|         |-------------|
      |           |               |            |         |             |
      |  vehicle  |               +------------+         +-------------+
      |           |                      ^                      ^
      +-----------+                      |                      |
        ^        ^                       |                      |
        |        |                +------+-----+         +------+------+
        |        |                |    Order   |<-------+|  OrderDatail|
        |        +                |------------|         |-------------|
  +-----+----+  +---------+       |            |         |             |
  | Truck    |  |  Car    |       +------------+         +-------------+
  |----------|  |---------|
  |          |  |         |
  +----------+  +---------+
  
  
  




**基本**

Order order = Ebean.find(Order.class, 12);  

List<Order> list = Ebean.find(Order.class).findList(); 

List<Order> list = Ebean.find(Order.class)  
    .where()  
      .eq("status", Order.Status.SHIPPED)  
      .gt("shipDate", lastWeek)  
    .findList();

String query = "find order where status.code=:status and shipDate > :shipped";  
List<Order> list = Ebean.find(Order.class)  
    .setQuery(query)  
    .setParameter("status", Order.Status.SHIPPED)  
    .setParameter("shipped", lastWeek)  
    .findList(); 
 
 **Ebean**
 
  Customer customer = Ebean.find(Customer.class, 4); 
  等价于
  EbeanServer defaultServer = Ebean.getServer(null);
  Customer customer = defaultServer.find(Customer.class,4)
  
  **Fetch**
  
  List<Order> orders =   
    Ebean.find(Order.class)  
        .fetch("details")  
        .findList();  
  刚才detail里并没有值
  
 List<Order> orders =   
    Ebean.find(Order.class)  
        .fetch("details")  
        .fetch("details.product")  
        .fetch("customer")  
        .fetch("customer.billingAddress")  
        .fetch("customer.shippingAddress")  
        .findList();
        
 比hibernate懒加载一刀切灵活 
 
 根据条件自动fetch 
 List<Order> orders =   
    Ebean.find(Order.class)  
        .where().ilike("customer.name", "rob%")  
        .findList();  
  这里自动fetch了 customer.name
 
 **Query Joins 通过多次查询代替joins**
 
 List<Order> orders =   
    Ebean.find(Order.class)  
        .fetch("customer", new FetchConfig().query())  
        .findList();  
第一次取出Order，第二次取出Customer
一般两次查询会比一次join效率高些。

List<Order> orders =  
        Ebean.find(Order.class)  
                .fetch("details", new FetchConfig().queryFirst(20).lazy(20))  
                .setMaxRows(100)  
                .where().eq("status",Order.Status.NEW)  
                .order().desc("id")  
                .findList();  
  最大取出100个，每次20
  
  **部分对象**
  
  
  List<Order> orders =   
    Ebean.find(Order.class)  
        .select("orderDate, shipDate")  
        .findList();  
        
   select o.id, o.order_date, o.ship_date   
from or_order o  

List<Order> orders =   
    Ebean.find(Order.class)  
        .select("orderDate, shipDate")  
        .fetch("customer","name")  
        .findList();  
        
        select o.id, o.order_date, o.ship_date  
        , c.id, c.name   
from or_order o  
join or_customer c on o.customer_id = c.id   

**Autofetch**

看了这个感觉可以放弃手动fetch了，可以根据你的代码重新决定sql。
所以推荐使用AutoFetch，不用手动指定fetch

**异步执行**

扩展自java.util.concurrent.Future

**分页**

int pageSize = 10;  
  
PagingList<TOne> pagingList =   
    Ebean.find(TOne.class)  
        .where().gt("name", "b")  
        .findPagingList(pageSize);  
pagingList.getFutureRowCount();  
Page<TOne> page = pagingList.getPage(0);  
// get the beans from the page as a list  
List<TOne> list = page.getList();  
int totalRows = page.getTotalRowCount();  
if (page.hasNext()) {  
    Page<TOne> nextPage = page.next();  
    ...  
}

*另一个例子*


            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("company")
                .findPagingList(pageSize)
                .getPage(page);
                
**RawSql** 

类似于ibatis

**事务**
默认情况下是隐含事务，一个执行一个事务

注解方法：
@Transactional  (type=TxType.REQUIRES_NEW)
 public void runFirst() throws IOException {  
  
        // run in a Transaction (REQUIRED is the default)  
  
        // find a customer  
        Customer customer = Ebean.find(Customer.class, 1);  
  
        // call another "transactional" method  
        runInTrans();  
    }
  传统模式：
  Ebean.beginTransaction();  
try {  
    // fetch some stuff...  
    User u = Ebean.find(User.class, 1);  
    ...  
  
    // save or delete stuff...  
    Ebean.save(u);  
    ...  
  
    Ebean.commitTransaction();  
      
} finally {  
    Ebean.endTransaction();  
}  


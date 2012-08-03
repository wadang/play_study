**ebean�ص�**

* �����Ͻӽ�hibernate�����Ǹ����
* û��Session�ĸ��
* fecth�������ؿ��Ƹ����
* ��ibatis�����á�
* ֧�ָ��ӵĹ��ܣ�cache TX����ȡ�

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
  
  
  




**����**

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
  �ȼ���
  EbeanServer defaultServer = Ebean.getServer(null);
  Customer customer = defaultServer.find(Customer.class,4)
  
  **Fetch**
  
  List<Order> orders =   
    Ebean.find(Order.class)  
        .fetch("details")  
        .findList();  
  �ղ�detail�ﲢû��ֵ
  
 List<Order> orders =   
    Ebean.find(Order.class)  
        .fetch("details")  
        .fetch("details.product")  
        .fetch("customer")  
        .fetch("customer.billingAddress")  
        .fetch("customer.shippingAddress")  
        .findList();
        
 ��hibernate������һ������� 
 
 ���������Զ�fetch 
 List<Order> orders =   
    Ebean.find(Order.class)  
        .where().ilike("customer.name", "rob%")  
        .findList();  
  �����Զ�fetch�� customer.name
 
 **Query Joins ͨ����β�ѯ����joins**
 
 List<Order> orders =   
    Ebean.find(Order.class)  
        .fetch("customer", new FetchConfig().query())  
        .findList();  
��һ��ȡ��Order���ڶ���ȡ��Customer
һ�����β�ѯ���һ��joinЧ�ʸ�Щ��

List<Order> orders =  
        Ebean.find(Order.class)  
                .fetch("details", new FetchConfig().queryFirst(20).lazy(20))  
                .setMaxRows(100)  
                .where().eq("status",Order.Status.NEW)  
                .order().desc("id")  
                .findList();  
  ���ȡ��100����ÿ��20
  
  **���ֶ���**
  
  
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

��������о����Է����ֶ�fetch�ˣ����Ը�����Ĵ������¾���sql��
�����Ƽ�ʹ��AutoFetch�������ֶ�ָ��fetch

**�첽ִ��**

��չ��java.util.concurrent.Future

**��ҳ**

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

*��һ������*


            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("company")
                .findPagingList(pageSize)
                .getPage(page);
                
**RawSql** 

������ibatis

**����**
Ĭ�����������������һ��ִ��һ������

ע�ⷽ����
@Transactional  (type=TxType.REQUIRES_NEW)
 public void runFirst() throws IOException {  
  
        // run in a Transaction (REQUIRED is the default)  
  
        // find a customer  
        Customer customer = Ebean.find(Customer.class, 1);  
  
        // call another "transactional" method  
        runInTrans();  
    }
  ��ͳģʽ��
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


# Particle Cloud Java API

This library makes easier to run the particle clould API using Java language. 

### Maven Dependency
```sh
<parent>
    <groupId>np.com.ngopal</groupId>
    <artifactId>particle-cloud</artifactId>
    <version>1.0.0</version>
</parent>
```
Currently it support for the Particle Cloud API of v1. 



#### Creating A Customer By using Client Name and Secret 
```sh
Customer customer = new Customer();
customer.setEmail("text@xyz.com");
customer.setPassword("p@$$");
try {
    Customer customerRet = Particle.client("client-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b")
    .api().customers().createCustomer("product-v100", customer);
    System.out.println(customerRet);
} catch (APIException ex) {
    Logger.getLogger(SimpleTest.class.getName()).log(Level.SEVERE, null, ex);
}
```

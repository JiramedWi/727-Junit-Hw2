#จงเขียน Testcase เพื่อทดสอบ Source code ที่กำหนด

โดยมี method ต่างๆ ดังนี้

###คลาส `ProductServiceImpl`
1. `getProductByPartialName(String name)` คืนค่า `List` ของ `Product` ที่มีชื่อเป็นส่วนใดส่วนหนึ่งของ `name` ที่รับมา
2. `getProductByPriceLessThan(Double price)` คืนค่า `List` ของ `Product` ที่มี `standPrice` น้อยกว่าค่าที่ระบุ

ทั้งนี้ถ้าทั้งสองคลาสไม่มีข้อมูลใดที่ถูกส่งคืนมาให้โยน Exception `NoProductException`

###คลาส `TransactionServiceImpl`
1. `getAverageTransactionSellPriceByCustomerName(String customerName)` ให้คืนค่าเฉลี่ยของราคาขายของ `Transaction` ที่มีผู้ซื้อชื่อเดียวกับค่าที่รับเข้ามา
2. `getAverageTransactionSellPriceByProduct(String productId)` ให้คืนค่าเฉลี่ยของราคาขายของ `Transaction` ที่ขาย `Product` เดียวกันกับ `productId` ที่รับเข้ามา
3. `getMaximumSellPriceTransaction()` คืนค่า `Transaction` ที่มีราคาขายสูงที่สุดอออกมา

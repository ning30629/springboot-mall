# Spring Boot - mall 專案介紹
使用Spring Boot製作的電商網站後端，包括以下CRUD功能(均帶入token驗證)
* 商品功能
  1. 新增商品
  2. 修改商品
  3. 刪除商品
  4. 設置查詢商品條件
  5. 查詢結果分頁
  
* 會員功能
  1. 新增會員 (會員密碼加密)
  2. 修改會員資料
  3. 刪除會員
  4. 會員登入 (回傳token)
  
* 訂單功能
  1. 新增訂單 (折扣優惠活動)
    * 購買三種類別商品，打8折
    * 購買兩種類別商品，打9折
    * 購買一種類別商品，原價
  2. 修改訂單
  3. 刪除訂單
  4. 查詢訂單列表

## Api 文件
文件網址:http://localhost:8080/swagger-ui.html#/
### 使用者 controller層
![image](https://github.com/ning30629/springboot-mall/blob/main/User%20Controller.png?raw=true)
  
### 商品 controller層
  ![image](https://github.com/ning30629/springboot-mall/blob/main/Product%20Cintroller.png?raw=true)
  
### 訂單 controller層
![image](https://github.com/ning30629/springboot-mall/blob/main/Order%20Controller.png?raw=true)

## 開發環境
|   工具   |   版本   |
|:--------|:--------|
|SDK | 17 |
|Spring Boot | 2.7.4|
|MySQL| 8.0.30 |
|Swagger 2 | 2.9.2 |
|Swagger UI | 2.9.2 |







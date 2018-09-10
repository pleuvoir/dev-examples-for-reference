# easy-authent

:smiling_imp:  方便的使用Google Authenticator密码验证

### 获取二维码

```java
String googleQRcode = Authenticator.generateQRcode("user");
```

会得到形如`otpauth://totp/user@easy-authentication?secret=ORHTXAXKQKKSL7BS`的链接，用此链接生成二维码使用google身份验证器扫描进行绑定，即可生成动态（一次性）密码

### 验证密码

```java
boolean authorize = Authenticator.authorize("ORHTXAXKQKKSL7BS", "005987");
```

服务端与客户端进行加密运算，一致则认为是认证通过


### 其他帮助

`QRBarcodeUtil`提供了向浏览器输出二维码的方法



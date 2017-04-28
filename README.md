## sendotp-java

Instructions to integrate SendOtp Java API.

Installation
-------------

* Maven installation:

```xml
<dependencies>
	..
    <dependency>
        <groupId>sendotp-java</groupId>
        <artifactId>sendotp-java</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

Usage
-------

* Create SendOtp object with your authKey:

```java
SendOtp sendOtp = new SendOtp(authKey);
```

* Generate otp:

```java
String otp = sendOtp.generateOtp(6);
```

* Send otp to a number:

```java
String response = sendOtp.send(contactNumber, senderId);
```

* Verify otp:

```java
String response = sendOtp.verify(contactNumber, otp);
```

* Retry:

```java
String response = sendOtp.retry(contactNumber, false); //send true is retrying for voice
```
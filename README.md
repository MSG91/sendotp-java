## sendotp-java

Instructions to integrate SendOtp Java API.

Installation
-------------

* Maven installation:

    <dependency>
        <groupId>sendotp-java</groupId>
        <artifactId>sendotp-java</artifactId>
        <version>1.0.0</version>
    </dependency>

Usage
-------

* Create SendOtp object with your authKey:

    SendOtp sendOtp = new SendOtp(authKey);

* Generate otp:

    String otp = sendOtp.generateOtp(6);

* Send otp to a number:

    String response = sendOtp.send(contactNumber, senderId);

* Verify otp:

    String response = sendOtp.verify(contactNumber, otp);

* Retry:

    String response = sendOtp.retry(contactNumber, retryVoice);
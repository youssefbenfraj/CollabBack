package tn.esprit.espritcollabbackend.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message).create();
    }
}

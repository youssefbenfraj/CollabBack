package tn.esprit.espritcollabbackend.restController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import tn.esprit.espritcollabbackend.entities.Buys;
import tn.esprit.espritcollabbackend.entities.Document;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.services.IBuys;
import tn.esprit.espritcollabbackend.services.IUser;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")// ajoutineha bch el front najmt tchouf el back 5ater ma3ndhech nafs el port w zidna configuration fel security fel crosconfiguration
@RestController
public class PaymentController {
    @Autowired
    private IUser iUser;
    @Autowired
    private IBuys iBuys;
    private User uf;
    private Document d;

    @PostMapping("/create-checkout-session/{id}")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody Document data, @PathVariable Long id) throws StripeException {
        uf=iUser.retrieveById(id);
        d=data;
        Stripe.apiKey = "sk_test_51PD5sqP2NwQQlzpEUtCNyMQiQ23mIwDfwFIsJcQ4jZ0HjDiw7zutWaa4AIZf7jmVbaBjhrDPP0T3Jibiqj9yPUyQ00CkXYIIUz";

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/addb") // URL after successful payment
                .setCancelUrl("http://localhost:4200/documents") // URL after payment cancellation
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(data.getPrice() * 100) // Stripe counts in cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(data.getTitleDoc())
                                                                .build())
                                                .build())
                                .build())
                .build();

        Session session = Session.create(params);
        Map<String, String> response = new HashMap<>();
        response.put("url", session.getUrl());
        return ResponseEntity.ok(response); // Return payment session URL as JSON
    }
    @PostMapping("/addb")
    private void addbuy(){
        Buys b=new Buys();
        b.setDocument(d);
        b.setUser(uf);
        iBuys.addBuys(b);
    }
}


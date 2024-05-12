package tn.esprit.espritcollabbackend.services;
/*import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.TokenOptions;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class VideoCallService {
    @Value("${openvidu.url}")
    private String OPENVIDU_URL;

    @Value("${openvidu.secret}")
    private String SECRET;

    private OpenVidu openVidu;

    public VideoCallService() {
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }

    public String createSession() throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = this.openVidu.createSession();
        return session.getSessionId();
    }


}
*/
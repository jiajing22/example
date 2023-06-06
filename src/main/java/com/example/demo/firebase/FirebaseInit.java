package com.example.demo.firebase;

import com.example.demo.DemoApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

@Service
public class FirebaseInit {

    @PostConstruct
    public void initialize() throws Exception {

        ClassLoader classLoader = DemoApplication.class.getClassLoader();

//        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccount.json")).getFile());
        InputStream serviceAccountStream = classLoader.getResourceAsStream("serviceAccount.json");
        if( serviceAccountStream == null ){
            throw new Exception("serviceAccount.json file not found in resources directory");
        }
        try {
//            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package com.example.b4b;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class DrinksApplication {

    public static void main(String[] args) throws IOException {
        //This line may be different based on what your project is named. Use the appropriate class name appears above
        ClassLoader loader = DrinksApplication.class.getClassLoader();

//opens the file stored in resources
        File file = new File(loader.getResource("serviceAccountkey.json").getFile());
//reads the data from the file
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

//connect to Firebase
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
      SpringApplication.run(DrinksApplication.class, args);
    }

}

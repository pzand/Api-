package com.example.apiinterface;

import com.pzand.clinesdk.cline.ApiCline;
import com.pzand.clinesdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Autowired
    private ApiCline apiCline;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("pzand");
        String res = apiCline.getUsernameByPost(user);
        System.out.println(res);
    }
}

package com.paygo.paygo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.paygo.paygo.entity.AccountType;
import com.paygo.paygo.entity.User;
import com.paygo.paygo.entity.UserProfile;
import com.paygo.paygo.repository.AccountTypeRepository;
import com.paygo.paygo.repository.UserProfileRepository;
import com.paygo.paygo.repository.UserRepository;

@Component
public class DataInitializer implements ApplicationRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Value("${adminEmail}")
    private String adminEmail;

    @Value("${adminFirstName}")
    private String adminFirstName;

    @Value("${adminLastName}")
    private String adminLastName;

    @Value("${adminPass}")
    private String adminPass;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create Admin User at Code Initialization
        if(!userRepository.existsByEmail(adminEmail)){

            // Save User 
            User user = new User();
            user.setEmail(adminEmail);
            user.setPassword(passwordEncoder.encode(adminPass));
            user.setIsAdmin(true);
            userRepository.save(user);

            // Save User profile name
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName(adminFirstName);
            userProfile.setLastName(adminLastName);
            userProfile.setUser(user);

            userProfileRepository.save(userProfile);
        }

        // Create Default Account Type at Initialization
        List<Map<Object, String>> records = new ArrayList<>();

        Map<Object, String> savings = new HashMap<>();
        savings.put("code", "100");
        savings.put("name", "SAVINGS");
        records.add(savings);

        Map<Object, String> current = new HashMap<>();
        current.put("code", "200");
        current.put("name", "CURRENTS");
        records.add(current);

        
        Map<Object, String> loan = new HashMap<>();
        loan.put("code", "300");
        loan.put("name", "LOANS");
        records.add(loan);

        for(Map<Object, String> obj: records){
            if(!accountTypeRepository.existsByCode(obj.get("code"))){
                AccountType accountType = new AccountType();
                accountType.setCode(obj.get("code"));
                accountType.setName(obj.get("name"));
                accountTypeRepository.save(accountType);
            }
        }
    }
    
}

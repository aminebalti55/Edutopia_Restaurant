package com.example.edutopia_res.Services;

import com.example.edutopia_res.Iservices.IuserService;
import com.example.edutopia_res.Repository.UserRepository;
import com.example.edutopia_res.configuration.KeycloakProvider;
import com.example.edutopia_res.entities.Subscription;
import com.example.edutopia_res.entities.User;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class UserService implements IuserService {

 KeycloakRestTemplate keycloakRestTemplate;
 @Autowired
 UserRepository userRepository;
 Keycloak keycloak;
 PasswordEncoder passwordEncoder;


 @Override
 public User getUserById(int id) {

   return userRepository.findById(id).orElse(null);
  }
 public List<User> getSubscribedUsers() {
  List<User> allUsers = userRepository.findAll();
  List<User> subscribedUsers = new ArrayList<>();
  for (User user : allUsers) {
   if (user.isSubscribedToRss()) {
    subscribedUsers.add(user);
   }
  }
  return subscribedUsers;
 }
 }







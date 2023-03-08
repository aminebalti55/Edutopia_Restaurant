package com.example.edutopia_res.Iservices;

import com.example.edutopia_res.entities.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IuserService {

    User getUserById(int id);
    List<User> getSubscribedUsers();
}

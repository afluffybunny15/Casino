package usermanager.service;

import usermanager.entity.User;

public interface UserService {

    public boolean userExists ( String username );

    public User getCurrentUser ();

}

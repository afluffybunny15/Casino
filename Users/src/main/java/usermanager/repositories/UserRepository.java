package usermanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usermanager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Returns true if a user exists with the given username.
     *
     * @param username
     *            username to search
     * @return true if username exists for a user
     */
    Boolean existsByUsername ( String username );

    /**
     * Finds the user based off of the given username
     *
     * @param username
     *            Username of the user we want to find
     *
     * @return The user if exists, null if no user with username found
     */
    Optional<User> findByUsername ( String username );

    Optional<User> findByUsernameOrEmail ( String usernameOrEmail, String usernameOrEmail2 );

}

package repository.impl;

import model.User;
import repository.DataStore;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) {
        DataStore.USERS.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(DataStore.USERS.get(email));
    }

    @Override
    public Collection<User> findAll() {
        return new ArrayList<>(DataStore.USERS.values());
    }
}
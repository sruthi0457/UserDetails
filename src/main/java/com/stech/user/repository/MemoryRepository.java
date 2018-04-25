package com.stech.user.repository;

import com.stech.user.domain.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryRepository<T> {
    private HashMap<Long, UserDetails> hs = new HashMap<>();

    @Autowired
    private IdGenerator idGenerator;

    public UserDetails create(UserDetails element) {
        element.setId(idGenerator.getNextId());
        Long id = element.getId();
        hs.put(id, element);
        return element;
    }

    public boolean delete(Long userId) {
        if (hs.containsKey(userId)) {
            hs.remove(userId);
            return true;
        } else
            return false;
    }

    public List<UserDetails> findAll() {
        ArrayList<UserDetails> al = new ArrayList<>();
        al.addAll(hs.values());
        return al;
    }

    public Optional<UserDetails> find(Long id) {
        return Optional.ofNullable(hs.get(id));

    }

    public boolean updateUser(Long id, UserDetails updateDetails) {
        Optional<UserDetails> original = find(id);
        original.ifPresent(x -> updateIfExists(x, updateDetails));
        return original.isPresent();
    }

    public void updateIfExists(UserDetails origin, UserDetails updated) {
        origin.setFirstName(updated.getFirstName());
        origin.setLastName(updated.getLastName());
        origin.setEmail(updated.getEmail());
    }

}

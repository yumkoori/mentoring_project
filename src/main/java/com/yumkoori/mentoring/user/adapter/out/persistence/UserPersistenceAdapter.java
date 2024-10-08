package com.yumkoori.mentoring.user.adapter.out.persistence;

import com.yumkoori.mentoring.common.PersistenceAdapter;
import com.yumkoori.mentoring.user.adapter.out.persistence.entity.UserJpaEntity;
import com.yumkoori.mentoring.user.adapter.out.persistence.mapper.PersistenceUserMapper;
import com.yumkoori.mentoring.user.application.port.out.LoadUserPort;
import com.yumkoori.mentoring.user.application.port.out.SaveUserPort;
import com.yumkoori.mentoring.user.domain.CustomUserDetails;
import com.yumkoori.mentoring.user.domain.User;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {

    private final SpringDataUserRepository repository;

    @Override
    public User getByUserId(Long userId) {
        UserJpaEntity userJpaEntity = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        return PersistenceUserMapper.mapToUser(userJpaEntity);
    }

    @Override
    public boolean existsByUserEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserJpaEntity> userEntity = repository.findByEmail(email);

        if (userEntity.isPresent()) {
            return PersistenceUserMapper.mapToUser(userEntity.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void saveUser(User user) {
        UserJpaEntity userJpaEntity = PersistenceUserMapper.mapToUserJpaEntity(user);
        repository.save(userJpaEntity);
    }
}

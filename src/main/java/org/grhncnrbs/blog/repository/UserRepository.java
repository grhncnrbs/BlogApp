package org.grhncnrbs.blog.repository;


import org.grhncnrbs.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {

    User findByUserNameAndPassword(String userName, String password);

}
package spring.io.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.io.projects.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByMobile(String mobile);

}

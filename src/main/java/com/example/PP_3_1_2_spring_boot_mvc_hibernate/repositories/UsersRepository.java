package com.example.PP_3_1_2_spring_boot_mvc_hibernate.repositories;


import com.example.PP_3_1_2_spring_boot_mvc_hibernate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}

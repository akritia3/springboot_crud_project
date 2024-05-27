package com.example.crud.repository;

import com.example.crud.entity.KYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface KYCRepository extends JpaRepository<KYC, Long> {

    @Query(value="select k from KYC k where k.name like :letter%")
    public List<KYC> getKYCwithFirstLetter(@Param("letter") String letter);

    @Query("select u, k from User u join KYC k on u.userID = k.userID")
    List<Object[]> getAllUsersAndKYCs();

}

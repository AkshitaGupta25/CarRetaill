package com.example.carRetail.repository;

import com.example.carRetail.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findAllByDeleteStatus(boolean b);
    Car findByRegNumber(String regNumber);

    boolean existsByRegNumberAndDeleteStatus(String regNumber, boolean b);

    @Modifying
    @Transactional
    @Query(value = "update car set delete_status=false where reg_number=:regNumber",nativeQuery = true)
    void updateIfCarExist(String regNumber);

    boolean existsByRegNumber(String regNumber);
}

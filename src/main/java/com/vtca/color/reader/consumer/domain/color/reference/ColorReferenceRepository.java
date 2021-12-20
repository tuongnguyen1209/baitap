package com.vtca.color.reader.consumer.domain.color.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColorReferenceRepository extends JpaRepository<ColorReference, Long> {

    @Query("select p from ColorReference p where p.colorId=?1")
    List<ColorReference> findBy(Long colorId);
}

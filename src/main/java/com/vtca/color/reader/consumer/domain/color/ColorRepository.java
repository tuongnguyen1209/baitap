package com.vtca.color.reader.consumer.domain.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query("select p from Color p where p.price>=?1")
    Color findBy(double price);

    @Query("select p from Color p where p.name like ?1 and LENGTH(p.name) = LENGTH(?1)")
    List<Color> findBy(String name);

/*    @Query("select p from Color p where p.m_rgb_r >= FLOOR(?1) and p.m_rgb_r <= CEIL(?1) " +
            "and p.m_rgb_g >= FLOOR(?2) and p.m_rgb_g <= CEIL(?2) " +
            "and p.m_rgb_b >= FLOOR(?3) and p.m_rgb_b <= CEIL(?3)")
    List<Color> findBy(double red, double green, double blue);*/

    @Query("select p from Color p where p.m_rgb_r >= ?1 and p.m_rgb_r <= ?2 " +
            "and p.m_rgb_g >= ?3 and p.m_rgb_g <= ?4 " +
            "and p.m_rgb_b >= ?5 and p.m_rgb_b <= ?6")
    List<Color> findBy(double fRed, double tRed, double fGreen, double tGreen, double fBlue, double tBlue);
}

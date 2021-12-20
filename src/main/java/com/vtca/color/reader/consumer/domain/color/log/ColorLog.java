package com.vtca.color.reader.consumer.domain.color.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Table(name = "`color_log`")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "m_rgb_r")
    private double m_rgb_r;
    @Column(name = "m_rgb_g")
    private double m_rgb_g;
    @Column(name = "m_rgb_b")
    private double m_rgb_b;
    @Column(name = "query_counter")
    private int queryCounter;
    @Column(name = "response")
    private String response;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "last_update_by")
    private String lastUpdateBy;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

}

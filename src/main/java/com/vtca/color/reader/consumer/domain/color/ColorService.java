package com.vtca.color.reader.consumer.domain.color;

import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import com.vtca.color.reader.consumer.domain.color.log.ColorLog;
import com.vtca.color.reader.consumer.domain.color.log.ColorLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ColorService {

    @Autowired
    private ColorRepository repo;

    @Autowired
    private ColorLogService colorLogService;

    /**
     * Get a color by name, this method support to search like
     * @param
     * @return
     */
    public List<Color> getColorBy(String name) {
        LoggerUtils.info("Execute getColorByName method");
        if (StringUtils.isEmpty(name)) {
            LoggerUtils.info("Execute getColorByName method got error due to ",
                    ColorError.COLOR_NAME.toString());
            throw new BusinessException(ColorError.COLOR_NAME);
        }

        List<Color> colors = repo.findBy(name);
        if (CollectionUtils.isEmpty(colors)) {
            LoggerUtils.info("Execute getColorByName method got error due to not existing color", name);
            return Collections.emptyList();
        }

        return colors;
    }

    /**
     * Get a color by r,g,b, this method support to search exactly
     * @param {m_rgb_r, m_rgb_g, m_rgb_b}
     * @return
     */
    public Color getColorBy(Color color) {
        LoggerUtils.info("Execute getColorBy{m_rgb_r, m_rgb_g, m_rgb_b} method");
        if (Objects.isNull(color)) {
            LoggerUtils.info("Execute getColorBy(Color color) method got error due to color is empty");
            throw new BusinessException(ColorError.COLOR_INVALID);
        }

        if (color.getM_rgb_r() < 0 || color.getM_rgb_g() < 0 || color.getM_rgb_b() < 0) {
            LoggerUtils.info("Execute getColorBy(Color color) method got error due to color is INVALID");
            throw new BusinessException(ColorError.COLOR_INVALID);
        }
        int looping = 20;
        double step = 0.5f;
        int queryCounter = 0;
        List<Color> colors = new ArrayList<>();

        for (double pos = 0.0; pos <= 20.0f; pos = pos + step) {
            queryCounter ++;
            double fRed = color.getM_rgb_r() - pos;
            double tRed = color.getM_rgb_r() + pos;
            double fGreen = color.getM_rgb_g() - pos;
            double tGreen = color.getM_rgb_g() + pos;
            double fBlue = color.getM_rgb_b() - pos;
            double tBlue = color.getM_rgb_b() + pos;
            colors = repo.findBy(fRed, tRed, fGreen, tGreen, fBlue, tBlue);
            if (!colors.isEmpty()) {
                break;
            }
        }

        try {
            //insert query log from DB
            ColorLog colorLog = ColorLog.builder()
                    .queryCounter(queryCounter)
                    .m_rgb_r(color.getM_rgb_r())
                    .m_rgb_g(color.getM_rgb_g())
                    .m_rgb_b(color.getM_rgb_b())
                    .response(CollectionUtils.isEmpty(colors) ? null : colors.toString())
                    .createdBy("SYSTEM")
                    .createdDate(LocalDateTime.now())
                    .lastUpdateBy("SYSTEM")
                    .lastUpdateDate(LocalDateTime.now())
                    .build();

            colorLogService.save(colorLog);
        } catch (Exception e) {
            LoggerUtils.severe("There is an issue with storing data into color_log table. Please help to take look");
        }

        if (CollectionUtils.isEmpty(colors)) {
            LoggerUtils.info("Execute getColorBy(Color color) method got error due to not existing color", color);
            return null;
        }

        return colors.get(0);
    }

    /**
     * Get all colors in DB
     * @return
     */
    public List<Color> listAll() {
        LoggerUtils.info("Execute listAll method");
        return repo.findAll();
    }

    /**
     * Get a color by price
     * @param
     * @return
     */
    public Color getColorByPrice(Double price) {
        LoggerUtils.info("Execute getColorByPrice method");
        if (Objects.isNull(price) || price <= 0L) {
            LoggerUtils.info("Execute getColorByPrice method got error due to ",
                    ColorError.COLOR_PRICE.toString());
            throw new BusinessException(ColorError.COLOR_PRICE);
        }

        Color color = repo.findBy(price);
        if (Objects.isNull(color)) {
            LoggerUtils.info("Execute getColorByPrice method got error due to not existing color", price);
        }

        return color;
    }

    /**
     * Insert new color into DB
     * @param color
     */
    public void save(Color color) {
        LoggerUtils.info("Execute save method", color.toString());
        if (Objects.isNull(color)) {
            throw new BusinessException(ColorError.COLOR_INVALID);
        }
        repo.save(color);
    }

    /**
     * Update existing color
     * @param color
     * @param id
     */
    public void save(Color color, Long id) {
        LoggerUtils.info("Execute save method", color.toString(), id);
        if (Objects.isNull(id)) {
            throw new BusinessException(ColorError.COLOR_ID);
        }

        //get update color from DB
        Color existColor = this.get(id);

        //check whether updating color is existed or not
        if (Objects.isNull(existColor)) {
            throw new BusinessException(ColorError.COLOR_NOT_EXIST);
        }

        this.save(color);
    }

    /**
     * Get color info by id
     * @param id
     * @return
     */
    public Color get(Long id) {
        LoggerUtils.info("Execute get color method", id);
        if (Objects.isNull(id)) {
            throw new BusinessException(ColorError.COLOR_ID);
        }
        return repo.findById(id).get();
    }

    /**
     * Delete color by id
     * @param id
     */
    public void delete(Long id) {
        LoggerUtils.info("Execute delete method", id);
        if (Objects.isNull(id)) {
            throw new BusinessException(ColorError.COLOR_ID);
        }
        repo.deleteById(id);
    }
}

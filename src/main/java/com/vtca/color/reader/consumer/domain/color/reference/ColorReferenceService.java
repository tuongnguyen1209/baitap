package com.vtca.color.reader.consumer.domain.color.reference;

import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ColorReferenceService {

    @Autowired
    private ColorReferenceRepository repo;

    /**
     * Get a color reference by color id, this method support to search exactly
     * @param
     * @return
     */
    public List<ColorReference> getColorReferenceBy(Long colorId) {
        LoggerUtils.info("Execute getColorReferenceBy(Long colorId) method");
        if (Objects.isNull(colorId) || colorId <= 0) {
            LoggerUtils.info("Execute getColorReferenceBy(Long colorId) method got error due to colorId is invalid");
            throw new BusinessException(ColorReferenceError.COLOR_REFERENCE_INVALID);
        }

        List<ColorReference> colorReferences = repo.findBy(colorId);
        if (CollectionUtils.isEmpty(colorReferences)) {
            LoggerUtils.info("Execute getColorReferenceBy(Long colorId) method got error due to not existing colorId", colorId);
            return Collections.emptyList();
        }

        return colorReferences;
    }
}

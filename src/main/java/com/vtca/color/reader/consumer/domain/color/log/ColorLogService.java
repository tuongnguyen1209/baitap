package com.vtca.color.reader.consumer.domain.color.log;

import com.vtca.color.reader.common.logger.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ColorLogService {

    @Autowired
    private ColorLogRepository repo;


    /**
     * Insert new color log into DB
     * @param colorLog
     */
    public void save(ColorLog colorLog) {
        LoggerUtils.info("Execute save method", colorLog.toString());
        if (Objects.isNull(colorLog)) {
            LoggerUtils.warning("Can't store color_log due to it's empty");
        }
        repo.save(colorLog);
    }
}

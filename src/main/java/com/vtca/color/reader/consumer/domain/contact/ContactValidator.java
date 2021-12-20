package com.vtca.color.reader.consumer.domain.contact;

import com.vtca.color.reader.common.constant.CommonConstant;
import com.vtca.color.reader.common.exception.BusinessException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ContactValidator {

    public void validate (Contact contact) {

        if (Objects.isNull(contact)) {
            throw new BusinessException(ContactError.CONTACT_EMPTY);
        }

        if (Objects.isNull(contact.getEmail())) {
            throw new BusinessException(ContactError.EMAIL_EMPTY);
        }

        if (StringUtils.isEmpty(contact.getSubject())) {
            throw new BusinessException(ContactError.SUBJECT_EMPTY);
        }

        if (StringUtils.isEmpty(contact.getMessage())) {
            throw new BusinessException(ContactError.MESSAGE_EMPTY);
        }

        Pattern pattern = Pattern.compile(CommonConstant.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(contact.getEmail());
        if (!matcher.matches()) {
            throw new BusinessException(ContactError.EMAIL_PATTERN);
        }
    }
}

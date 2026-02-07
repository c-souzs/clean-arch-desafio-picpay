package com.souzs.usecase;

import com.souzs.core.domain.Transactional;

public interface UserNotificationUseCase {
    Boolean notificated(Transactional transactional);
}

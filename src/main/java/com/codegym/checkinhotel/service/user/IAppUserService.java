package com.codegym.checkinhotel.service.user;

import com.codegym.checkinhotel.model.AppUser;

public interface IAppUserService {
    AppUser getUserByUserName(String username);
}

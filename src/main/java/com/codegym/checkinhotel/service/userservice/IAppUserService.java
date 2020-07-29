package com.codegym.checkinhotel.service.userservice;

import com.codegym.checkinhotel.model.AppUser;

public interface IAppUserService {
    AppUser getUserByUserName(String username);
}

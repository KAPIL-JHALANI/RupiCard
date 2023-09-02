package com.example.TestProjectg.service;

import com.example.TestProjectg.model.UserData;
import org.springframework.stereotype.Service;

@Service
public interface GoogleSheetsService {
    void saveUserData(UserData userData) throws Exception;
}
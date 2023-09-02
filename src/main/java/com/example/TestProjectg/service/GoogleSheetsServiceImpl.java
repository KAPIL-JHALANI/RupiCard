package com.example.TestProjectg.service;

import com.example.TestProjectg.model.UserData;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {
    private final Sheets sheetsService;
    private final String spreadsheetId;

    public GoogleSheetsServiceImpl(
            @Value("${google.sheets.api.credentials-location}") Resource credentialsResource,
            @Value("${google.sheets.api.spreadsheet-id}") String spreadsheetId)
            throws IOException, GeneralSecurityException {
        this.sheetsService = createSheetsService(credentialsResource.getInputStream());
        this.spreadsheetId = spreadsheetId;
    }

    private Sheets createSheetsService(java.io.InputStream credentialsStream)
            throws IOException, GeneralSecurityException {
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                GoogleCredential.fromStream(credentialsStream)
                        .createScoped(Collections.singletonList(SheetsScopes.SPREADSHEETS))
        ).setApplicationName("Google Sheets Demo").build();
    }

    @Override
    public void saveUserData(UserData userData) throws IOException {
        List<Object> rowData = Arrays.asList(userData.getEmail(), userData.getPhoneNumber());

        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(rowData.get(0), rowData.get(1))));

        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(spreadsheetId, "Sheet1", body)
                .setValueInputOption("RAW")
                .execute();
    }
}

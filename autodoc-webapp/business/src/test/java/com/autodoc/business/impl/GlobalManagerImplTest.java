package com.autodoc.business.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.contract.EnumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class GlobalManagerImplTest {
    String feedback = "400 / For input string: \"that client already exist: ROGER MOORE\"";
    ClientService service;
    EnumService enumService;
    private GlobalManagerImpl manager;

    @BeforeEach
    void init() {
        service = mock(ClientService.class);
        enumService = mock(EnumService.class);
        manager = new ClientManagerImpl(service, enumService);
    }

    @Test
    void getFeedbackDetails() {
        assertEquals("that client already exist: ROGER MOORE", manager.getFeedbackDetails(feedback));
    }

    @Test
    void getRequestCode() {

        assertEquals(400, manager.getRequestCode(feedback));
    }
}
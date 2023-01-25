package ru.gothmog.ws.auth.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "ApiController", description = "Сервис с api")
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
public class ApiController {
    protected static final String DEFAULT_PAGE_NUMBER = "0";
    protected static final String DEFAULT_PAGE_SIZE = "30";
    protected static final String MAX_PAGE_SIZE = "50";
    protected static final String SORT_ASC = "ASC";
    protected static final String SORT_DESC = "DESC";
}

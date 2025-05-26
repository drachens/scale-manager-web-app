package com.marsol.balanzaappweb.controller;

import com.marsol.balanzaappweb.model.Scale;
import com.marsol.balanzaappweb.service.ScaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scales")
public class ScaleController {
    private final ScaleService scaleService;

    public ScaleController(ScaleService scaleService) {
        this.scaleService = scaleService;
    }

    @PostMapping
    public ResponseEntity<Scale> crear(@RequestBody @Validated Scale scale) {
        return ResponseEntity.ok(scaleService.createScale(scale));
    }
}

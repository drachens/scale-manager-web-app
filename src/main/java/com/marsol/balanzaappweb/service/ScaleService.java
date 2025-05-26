package com.marsol.balanzaappweb.service;

import com.marsol.balanzaappweb.model.Scale;

import java.util.List;

public interface ScaleService {
    Scale createScale(Scale scale);
    Scale getScaleById(Long id);
    Scale getScaleByIp(String stringIp);
    List<Scale> getAllScales();
    Scale updateScale(Long id,Scale scale);
    void deleteScale(Long id);
}

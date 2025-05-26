package com.marsol.balanzaappweb.service;

import com.marsol.balanzaappweb.model.Scale;
import com.marsol.balanzaappweb.repository.ScaleRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class ScaleServiceImpl implements ScaleService {

    private static final Logger logger = LoggerFactory.getLogger(ScaleServiceImpl.class);
    private final ScaleRepository scaleRepository;
    private final Validator validator;


    @Autowired
    public ScaleServiceImpl(ScaleRepository scaleRepository,
                            Validator validator) {
        this.scaleRepository = scaleRepository;
        this.validator = validator;
    }

    @Override
    public Scale createScale(Scale scale) {
        Set<ConstraintViolation<Scale>> violations = validator.validate(scale);
        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<Scale> violation : violations) {
                errors.append("[").append(violation.getPropertyPath()).append("] ").append(violation.getMessage()).append(";\n");
            }
            logger.error("Error al crear balanza {}: {}",scale.getId(),errors);
            throw new IllegalArgumentException("Errores de la validación: "+errors);
        }
        logger.info("Balanza IP: {}, ID: {} - creada exitosamente!",scale.getIpBalanza(),scale.getId());
        return scaleRepository.save(scale);
    }

    @Override
    public Scale getScaleById(Long id) {
        return scaleRepository.findById(id).orElseThrow(()-> new RuntimeException("Balanza "+id+" no encontrada"));
    }

    @Override
    public Scale getScaleByIp(String stringIp) {
        try{
            return scaleRepository.findByIpBalanza(stringIp).orElseThrow(() -> new RuntimeException("Balanza "+stringIp+" no encontrada"));
        } catch (Exception e) {
            logger.error("Error al obtener balanza {}: {}",stringIp,e.getMessage(),e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Scale> getAllScales() {
        try{
            return scaleRepository.findAll();
        }catch (Exception e){
            logger.error("Error obteniendo lista de balanzas: {}",e.getMessage(),e);
            throw new RuntimeException("Error obteniendo lista de balanzas");
        }
    }

    @Override
    public Scale updateScale(Long id, Scale scale) {
        try{
            Scale existingScale = getScaleById(id);
            existingScale.setDepartamento(scale.getDepartamento());
            existingScale.setNombre(scale.getNombre());
            existingScale.setFormato(scale.getFormato());
            existingScale.setMarca(scale.getMarca());
            existingScale.setModelo(scale.getModelo());
            existingScale.setStore(scale.getStore());
            existingScale.setEsDual(scale.isEsDual());
            existingScale.setEsAutoservicio(scale.isEsAutoservicio());
            return scaleRepository.save(existingScale);
        }catch (Exception e){
            logger.error("Error al actualizar balanza {}: {}",id,e.getMessage(),e);
            throw new RuntimeException("Error al actualizar balanza "+id);
        }
    }

    @Override
    public void deleteScale(Long id) {
        try{
            scaleRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Error al eliminar balanza {} : {}",id,e.getMessage(),e);
            throw new RuntimeException("Error al eliminar balanza "+id);
        }
    }
}

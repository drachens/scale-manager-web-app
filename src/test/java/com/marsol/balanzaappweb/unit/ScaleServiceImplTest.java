package com.marsol.balanzaappweb.unit;

import com.marsol.balanzaappweb.model.Scale;
import com.marsol.balanzaappweb.repository.ScaleRepository;
import com.marsol.balanzaappweb.service.ScaleServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.Validator;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ScaleServiceImplTest {

    @Mock
    private ScaleRepository scaleRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private ScaleServiceImpl service;

    private Scale testScale;

    @BeforeEach
    public void setUp() {
        testScale = Scale.builder()
                .id(1L)
                .formato("")
                .departamento(1)
                .marca("HPRT")
                .store(1)
                .ipBalanza("10.0.0.0")
                .modelo("")
                .nombre("")
                .lastUpdate("")
                .build();
    }

    @Test
    void testCreateScale_success(){
        when(scaleRepository.save(any(Scale.class))).thenReturn(testScale);
        when(validator.validate(any(Scale.class))).thenReturn(Set.of());

        Scale created = service.createScale(testScale);

        assertNotNull(created);
        assertEquals("HPRT",created.getMarca());
        assertEquals("10.0.0.0",created.getIpBalanza());
        verify(scaleRepository, times(1)).save(testScale);
    }

    @Test
    void testCreateScale_fail(){
        Scale scale = Scale.builder()
                        .id(2L)
                        .build();

        ConstraintViolation<Scale> violation = mock(ConstraintViolation.class);
        Path propertyPath = mock(Path.class);

        when(propertyPath.toString()).thenReturn("ipBalanza");
        when(violation.getPropertyPath()).thenReturn(propertyPath);
        when(violation.getMessage()).thenReturn("La IP no puede ser nula!");

        Set<ConstraintViolation<Scale>> violations = Set.of(violation);

        //Mock Validator: devuelve el erro de validacion
        when(validator.validate(scale)).thenReturn(violations);

        //Ejecuta la prueba: esperamos excepcion
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.createScale(scale));

        //Verifica el mensaje de la excepcion
        assertTrue(exception.getMessage().contains("La IP no puede ser nula!"));

        //Verifica que no se llamo la funcion save
        verify(scaleRepository, never()).save(scale);
    }
}

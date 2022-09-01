package com.screamer.resume.config.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith({SpringExtension.class})
class AudienceValidatorTest {
    private String audience;

    private AudienceValidator validator;

    @BeforeEach
    void setUp() {
        this.audience = "audience";
        this.validator = new AudienceValidator(audience);
    }

    @Test
    void validate() {
        Jwt mockJwt = mock(Jwt.class);
        List<String> audienceList = new ArrayList<>();
        audienceList.add(audience);

        when(mockJwt.getAudience()).thenReturn(audienceList);

        OAuth2TokenValidatorResult result = validator.validate(mockJwt);

        assertFalse(result.hasErrors(), "Validate correct jwt return failure");
        verify(mockJwt, atLeastOnce()).getAudience();
    }

    @Test
    void validate_withoutAudience() {
        Jwt mockJwt = mock(Jwt.class);
        List<String> audienceList = new ArrayList<>();

        when(mockJwt.getAudience()).thenReturn(audienceList);

        OAuth2TokenValidatorResult result = validator.validate(mockJwt);

        assertTrue(result.hasErrors(), "Validate incorrect jwt return success");
        verify(mockJwt, atLeastOnce()).getAudience();
    }
}
package es.laspalmeras.padel.presentation.config.exception.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
	private String timestamp;
    private String message;
    private String details;

    public ErrorDetails(String message, String details) {
        this.timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.message = message;
        this.details = details;
    }

    // Getters y setters
}

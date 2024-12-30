package es.laspalmeras.padel.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<>();
		map.put("status", request.getAttribute("jakarta.servlet.error.status_code"));
		map.put("reason", request.getAttribute("jakarta.servlet.error.message"));
    	return ResponseEntity.status(500).body(map);
    }

    public String getErrorPath() {
        return "/error";
    }
  
}

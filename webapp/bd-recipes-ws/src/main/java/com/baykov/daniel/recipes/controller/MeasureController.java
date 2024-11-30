package com.baykov.daniel.recipes.controller;

import com.baykov.daniel.recipes.entity.Measure;
import com.baykov.daniel.recipes.payload.GenericPageableResponse;
import com.baykov.daniel.recipes.payload.response.ResponseMessage;
import com.baykov.daniel.recipes.service.MeasureService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/measures")
@RequiredArgsConstructor
public class MeasureController {

    private final MeasureService measureService;

    @GetMapping("/{measureId}")
    public ResponseEntity<Measure> getMeasureById(@PathVariable Long measureId) {
        Measure measure = measureService.findMeasureById(measureId);
        return ResponseEntity.ok(measure);
    }

    @GetMapping
    public ResponseEntity<GenericPageableResponse<Measure>> getAllMeasures(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        GenericPageableResponse<Measure> measuresResponse = measureService.findAllMeasuresPaged(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(measuresResponse);
    }

//    @PostMapping
//    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
//        log.info("Correlation ID: {}. Received request to create a new author: {}", RequestData.getCorrelationId(), authorRequestDTO);
//
//        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);
//
//        log.info("Correlation ID: {}. Author created successfully: {}", RequestData.getCorrelationId(), createdAuthor);
//        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
//    }

    @Operation(hidden = true)
    @PutMapping("/{measureId}")
    public ResponseEntity<ResponseMessage> updateMeasureById(
            @PathVariable Long measureId,
            @Valid @RequestBody Measure measure) {
        ResponseMessage responseMessage = measureService.updateMeasureById(measureId, measure);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/{measureId}")
    public ResponseEntity<String> deleteMeasureById(@PathVariable Long measureId) {
        measureService.deleteMeasureById(measureId);
        return ResponseEntity.ok("");
    }
}

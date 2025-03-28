package swe304.swe304_1.controller;

import swe304.swe304_1.dto.PatentDTO;
import swe304.swe304_1.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patents")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @PostMapping
    public ResponseEntity<PatentDTO> createPatent(@RequestBody PatentDTO patentDTO) {
        PatentDTO createdPatent = patentService.createPatent(patentDTO);
        return new ResponseEntity<>(createdPatent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatentDTO> updatePatent(@PathVariable Integer id, @RequestBody PatentDTO patentDTO) {
        PatentDTO updatedPatent = patentService.updatePatent(id, patentDTO);
        return updatedPatent != null
                ? new ResponseEntity<>(updatedPatent, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatentDTO> getPatentById(@PathVariable Integer id) {
        PatentDTO patentDTO = patentService.getPatentById(id);
        return patentDTO != null
                ? new ResponseEntity<>(patentDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<PatentDTO>> getAllPatents() {
        List<PatentDTO> patents = patentService.getAllPatents();
        return new ResponseEntity<>(patents, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatent(@PathVariable Integer id) {
        patentService.deletePatent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

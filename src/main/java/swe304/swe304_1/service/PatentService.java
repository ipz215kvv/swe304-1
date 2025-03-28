package swe304.swe304_1.service;

import swe304.swe304_1.dto.PatentDTO;
import swe304.swe304_1.entity.Patent;
import swe304.swe304_1.repository.PatentRepository;
import swe304.swe304_1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatentService {

    @Autowired
    private PatentRepository patentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<PatentDTO> getAllPatents() {
        return patentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PatentDTO getPatentById(Integer id) {
        return patentRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PatentDTO createPatent(PatentDTO patentDTO) {
        Patent patent = new Patent();
        patent.setTitle(patentDTO.getTitle());
        patent.setDescription(patentDTO.getDescription());
        patent.setAuthor(authorRepository.findById(patentDTO.getAuthorId()).orElse(null));

        Patent savedPatent = patentRepository.save(patent);
        return convertToDTO(savedPatent);
    }

    public PatentDTO updatePatent(Integer id, PatentDTO patentDTO) {
        Patent patent = patentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patent not found with ID: " + id));

        patent.setTitle(patentDTO.getTitle());
        patent.setDescription(patentDTO.getDescription());
        patent.setAuthor(authorRepository.findById(patentDTO.getAuthorId()).orElse(null));

        Patent updatedPatent = patentRepository.save(patent);
        return convertToDTO(updatedPatent);
    }

    public void deletePatent(Integer id) {
        patentRepository.deleteById(id);
    }

    private PatentDTO convertToDTO(Patent patent) {
        PatentDTO dto = new PatentDTO();
        dto.setId(patent.getId());
        dto.setTitle(patent.getTitle());
        dto.setDescription(patent.getDescription());
        dto.setAuthorId(patent.getAuthor().getId());
        return dto;
    }
}

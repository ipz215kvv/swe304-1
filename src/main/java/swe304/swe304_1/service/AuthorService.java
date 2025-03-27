package swe304.swe304_1.service;

import swe304.swe304_1.dto.AuthorDTO;
import swe304.swe304_1.entity.Author;
import swe304.swe304_1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setAddress(authorDTO.getAddress());
        author.setImgUrl(authorDTO.getImgUrl());

        Author savedAuthor = authorRepository.save(author);
        return convertToDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(Integer id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));

        author.setName(authorDTO.getName());
        author.setAddress(authorDTO.getAddress());
        author.setImgUrl(authorDTO.getImgUrl());

        Author updatedAuthor = authorRepository.save(author);
        return convertToDTO(updatedAuthor);
    }

    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setAddress(author.getAddress());
        dto.setImgUrl(author.getImgUrl());
        return dto;
    }
}

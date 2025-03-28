package swe304.swe304_1.controller;

import swe304.swe304_1.dto.AuthorDTO;
import swe304.swe304_1.service.AuthorService;
import swe304.swe304_1.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private StorageService storageService;

    @PostMapping
    public AuthorDTO createAuthor(@ModelAttribute("author") AuthorDTO authorDTO,
                                   @RequestParam("image") MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = storageService.storeFile(imageFile);
            authorDTO.setImgUrl(imageUrl);
        }
        return authorService.createAuthor(authorDTO);
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Integer id,
                                   @ModelAttribute("author") AuthorDTO authorDTO,
                                   @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = storageService.storeFile(imageFile);
            authorDTO.setImgUrl(imageUrl);
        }
        return authorService.updateAuthor(id, authorDTO);
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        AuthorDTO author = authorService.getAuthorById(id);
        if (author != null && author.getImgUrl() != null) {
            String fileName = author.getImgUrl().substring(author.getImgUrl().lastIndexOf("/") + 1);
            storageService.deleteFile(fileName);
        }
        authorService.deleteAuthor(id);
    }
}

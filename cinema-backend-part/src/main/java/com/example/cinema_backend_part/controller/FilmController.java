package com.example.cinema_backend_part.controller;

import com.example.cinema_backend_part.model.Film;
import com.example.cinema_backend_part.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@CrossOrigin(origins = "http://localhost:3000")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film updatedFilm) {
        return filmService.getFilmById(id)
                .map(film -> {
                    film.setTitle(updatedFilm.getTitle());
                    film.setGenre(updatedFilm.getGenre());
                    film.setDescription(updatedFilm.getDescription());
                    film.setReleaseDate(updatedFilm.getReleaseDate());
                    film.setRating(updatedFilm.getRating());
                    Film savedFilm = filmService.saveFilm(film);
                    return ResponseEntity.ok(savedFilm);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}
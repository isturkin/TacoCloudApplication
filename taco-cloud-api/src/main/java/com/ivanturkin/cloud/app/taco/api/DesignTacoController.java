package com.ivanturkin.cloud.app.taco.api;

import com.ivanturkin.cloud.app.taco.domain.Taco;
import com.ivanturkin.cloud.app.taco.repository.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/design", produces = "application/json")
@RestController
public class DesignTacoController {

    private final TacoRepository tacoRepository;

    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("recent")
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12,
                Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> taco = tacoRepository.findById(id);
        if (taco.isPresent()) {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Taco save(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }
}

package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.CinemaHallRequestDto;
import mate.academy.spring.dto.CinemaHallResponseDto;
import mate.academy.spring.service.CinemaHallService;
import mate.academy.spring.service.mapper.CinemaHallDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CinemaHallController {
    private final CinemaHallDtoMapper cinemaHallDtoMapper;
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallDtoMapper cinemaHallDtoMapper,
                                CinemaHallService cinemaHallService) {
        this.cinemaHallDtoMapper = cinemaHallDtoMapper;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping("/cinema-halls")
    public CinemaHallResponseDto create(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        return cinemaHallDtoMapper.parser(cinemaHallService
                .add(cinemaHallDtoMapper.toModel(cinemaHallRequestDto)));
    }

    @GetMapping("/cinema-halls")
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallDtoMapper::parser)
                .collect(Collectors.toList());
    }
}
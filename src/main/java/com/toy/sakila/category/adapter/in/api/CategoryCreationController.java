package com.toy.sakila.category.adapter.in.api;

import com.toy.sakila.category.application.port.in.CategoryCreationCommand;
import com.toy.sakila.category.application.port.in.CategoryCreationUseCase;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/film/category")
public class CategoryCreationController {

    private final CategoryCreationUseCase categoryCreationUseCase;

    @PostMapping
    public ResponseEntity<ResponseBody<Object>> categoryCreation(
            @RequestBody CategoryCreationCommand command
    ) {

        Category.CategoryId id = categoryCreationUseCase.create(command);

        OutputDTO outputDto = OutputDTO.builder()
                .id(id.getValue())
                .build();

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(outputDto)
                .message("Category 생성을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Long id;
    }
}
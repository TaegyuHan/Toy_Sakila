package com.toy.sakila.category.adapter.in.api;

import com.toy.sakila.category.application.port.in.CategoryUpdateCommand;
import com.toy.sakila.category.application.port.in.CategoryUpdateUseCase;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.common.WebAdapter;
import com.toy.sakila.common.adapter.in.web.ResponseBody;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@WebAdapter
@RestController
@RequestMapping("/film/category")
public class CategoryUpdateController {

    private final CategoryUpdateUseCase categoryUpdateUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBody<Object>> categoryUpdate(
            @PathVariable Long id,
            @RequestBody CategoryUpdateCommand command
    ) {
        Category domain = categoryUpdateUseCase.update(Category.CategoryId.of(id), command);

        ResponseBody<Object> body = ResponseBody.builder()
                .status(HttpStatus.OK.value())
                .data(OutputDTO.of(domain))
                .message("Category 수정을 완료했습니다.")
                .build();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Value
    @Getter
    @Setter
    @Builder
    public static class OutputDTO {
        Long id;
        String name;
        LocalDateTime lastUpdate;
        LocalDateTime createdDate;

        public static OutputDTO of(Category domain) {
            return OutputDTO.builder()
                    .id(domain.getId().getValue())
                    .name(domain.getName())
                    .lastUpdate(domain.getLastUpdate())
                    .createdDate(domain.getCreatedDate())
                    .build();
        }
    }
}
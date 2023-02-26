package ru.gothmog.ws.files.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "content")
@JsonInclude(Include.NON_NULL)
public class ContentPageLimitResponse<T> {

  private int totalPages;
  private long totalElements;
  private int currentPage;
  private List<T> content;
}

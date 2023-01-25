package ru.gothmog.ws.auth.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

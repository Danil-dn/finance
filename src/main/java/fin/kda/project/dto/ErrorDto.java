package fin.kda.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

	private String message;

	private List<String> details;

}

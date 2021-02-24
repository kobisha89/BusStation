package busStationApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import busStationApp.dto.LineDTO;
import busStationApp.model.Line;
import busStationApp.service.LineService;
import busStationApp.support.LineDtoToLine;
import busStationApp.support.LineToLineDto;

@RestController
@RequestMapping(value = "/api/lines", produces = MediaType.APPLICATION_JSON_VALUE)
public class LineController {
	
	@Autowired
	private LineService lineService;
	
	@Autowired
	private LineToLineDto toLineDto;
	
	@Autowired
	private LineDtoToLine toLine;
	
	@GetMapping
	public ResponseEntity<List<LineDTO>> getAll(@RequestParam(required=false) String destination,
			@RequestParam(required=false) Long companyId,
			@RequestParam(required=false) Double maximumPrice,
			@RequestParam(defaultValue="0") int pageNo){
		
        Page<Line> linePage = lineService.search(destination, companyId, maximumPrice, pageNo);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Total-Pages", linePage.getTotalPages() + "");
        
        return new ResponseEntity<>(toLineDto.convert(linePage.getContent()), responseHeaders, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity <LineDTO> get(@PathVariable Long id) {
		Line line = lineService.findOne(id);
		
		if(line != null) {
            return new ResponseEntity<>(toLineDto.convert(line), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <LineDTO> create (@Valid @RequestBody LineDTO lineDTO) {
		
		Line line = toLine.convert(lineDTO);
		Line savedLine = lineService.save(line);
		
		return new ResponseEntity<>(toLineDto.convert(savedLine), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineDTO> update(@PathVariable Long id, @Valid @RequestBody LineDTO lineDTO){

        if(!id.equals(lineDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

		Line line = toLine.convert(lineDTO);
		Line savedLine = lineService.save(line);

        return new ResponseEntity<>(toLineDto.convert(savedLine),HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
		Line deleteLine = lineService.delete(id);

        if(deleteLine != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
